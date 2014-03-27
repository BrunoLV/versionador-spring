package com.porto.amazonas.versionamento.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.porto.amazonas.versionamento.model.Usuario;

/**
 * Classe que faz o papel de gerenciador de autenticação dentro da aplicação.
 * Nessa classe fazemos o registro do usuário no contexto de segurança do
 * Spring.
 * @author BRUNO VIANA
 */
@Component
@Qualifier("gerenciadorAutenticacao")
public class GerenciadorAutenticacao {

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	/**
	 * Método responsavel por validar e colocar usuario no contexto de seguranÃ§a
	 * do Spring.
	 * @param matricula do usuário.
	 * @param senha senha do usuário.
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
	} // fim do mï¿½todo executarLogin

	/**
	 * Método utilizado para retornar o usuário logado no contexto.
	 * @return Usuario logado o contexto.
	 */
	public Usuario getUsuarioLogado() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	} // fim do método getUsuarioLogado

	/**
	 * Método utilizado para deslogar usuário e encerrar a sessão do mesmo.
	 */
	public void executarLogout(HttpSession session) {
		SecurityContextHolder.getContext().setAuthentication(null);
		invalidarSessao(session);
	} // fim do mÃ©todo executarLogout

	/*
	 * Método utilizado para invalidar a sessão do usuário.
	 */
	private void invalidarSessao(HttpSession session) {
		session.invalidate();
	} // fim do método invalidarSessao

} // fim da classe GerenciadorAutenticacao