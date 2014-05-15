package com.porto.amazonas.versionamento.security;

import com.porto.amazonas.versionamento.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Classe que faz o papel de gerenciador de autenticacao dentro da aplicacao.
 * Nessa classe fazemos o registro do usuario no contexto de seguranca do
 * Spring.
 *
 * @author BRUNO VIANA
 */
@Component
@Qualifier("gerenciadorAutenticacao")
public class GerenciadorAutenticacao {

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    /**
     * Metodo responsavel por validar e colocar usuario no contexto de segurança
     * do Spring.
     *
     * @param matricula do usuario.
     * @param senha     senha do usuario.
     * @return boolean true para login com sucesso e false para insucesso.
     */
    public boolean executarLogin(final String matricula, final String senha) {
        boolean retorno = false;
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(matricula, senha);
        Authentication authentication = authenticationManager.authenticate(token);
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            retorno = true;
        } // fim do bloco if
        return retorno;
    } // fim do metodo executarLogin

    /**
     * Metodo utilizado para retornar o usuario logado no contexto.
     *
     * @return Usuario logado o contexto.
     */
    public Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } // fim do metodo getUsuarioLogado

    /**
     * Metodo utilizado para deslogar usuario e encerrar a sesseo do mesmo.
     */
    public void executarLogout(HttpSession session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        invalidarSessao(session);
    } // fim do método executarLogout

    /*
     * Metodo utilizado para invalidar a sesseo do usuario.
     */
    private void invalidarSessao(HttpSession session) {
        session.invalidate();
    } // fim do metodo invalidarSessao

} // fim da classe GerenciadorAutenticacao