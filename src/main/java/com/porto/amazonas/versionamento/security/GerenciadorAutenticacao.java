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
 * Classe que faz o papel de gerenciador de autentica��o dentro da aplica��o.
 * Nessa classe fazemos o registro do usu�rio no contexto de seguran�a do
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
	 * M�todo responsavel por validar e colocar usuario no contexto de segurança
	 * do Spring.
	 * @param matricula do usu�rio.
	 * @param senha senha do usu�rio.
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
	} // fim do m�todo executarLogin

	/**
	 * M�todo utilizado para retornar o usu�rio logado no contexto.
	 * @return Usuario logado o contexto.
	 */
	public Usuario getUsuarioLogado() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	} // fim do m�todo getUsuarioLogado

	/**
	 * M�todo utilizado para deslogar usu�rio e encerrar a sess�o do mesmo.
	 */
	public void executarLogout(HttpSession session) {
		SecurityContextHolder.getContext().setAuthentication(null);
		invalidarSessao(session);
	} // fim do método executarLogout

	/*
	 * M�todo utilizado para invalidar a sess�o do usu�rio.
	 */
	private void invalidarSessao(HttpSession session) {
		session.invalidate();
	} // fim do m�todo invalidarSessao

} // fim da classe GerenciadorAutenticacao