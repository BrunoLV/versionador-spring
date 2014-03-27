package com.porto.amazonas.versionamento.web.util;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * Classe utilit�ria para lidar com os escopos web da aplica��o.
 * @author BRUNO VIANA
 */
@Component
@Qualifier("jsfUtil")
public class JSFUtil {
	
	@Autowired
	@Qualifier("messageSource")
	private MessageSource messageSource;
	
	/**
	 * M�todo utilizado para obter o FacesContext da aplica��o.
	 * @return context Faces da aplica��o.
	 */
	public FacesContext obterFacesContext() {
		return FacesContext.getCurrentInstance();
	} // fim do m�todo obterFacesContext

	/**
	 * M�todo utilizado para obter o SessionScope corrente.
	 * @return sess�o do usu�rio.
	 */
	public HttpSession obterSessionScope() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	} // fim do m�todo obterSessionScope

	/**
	 * M�todo utilizado para retirar um atributo do SessionScope.
	 * @param nomeAtributo que ir� ser retirado do escopo.
	 */
	public void retirarAtributoDoSessionScope(String nomeAtributo) {
		obterSessionScope().removeAttribute(nomeAtributo);
	} // fim do m�todo retirarAtributoDoSessionScope
	
	/**
	 * M�todo utilizado para obter a localiza��o corrente da aplica��o.
	 * @return local em que a aplica��o esta rodando.
	 */
	public Locale obterLocalCorrente() {
		return obterFacesContext().getViewRoot().getLocale();
	} // fim do m�todo obterLocalCorrente
	
	/**
	 * M�todo utilizado para inserir uma mensagem informativa no contexto.
	 * @param mensagem que ser� exibida na view.
	 */
	public void inserirMensagemInformativa(String mensagem) {
		FacesMessage facesMessage = this.preencherMensagem(FacesMessage.SEVERITY_INFO, 
				getMessageSource().getMessage("rotulo.mensagem.info", null, this.obterLocalCorrente()), 
				mensagem);
		this.obterFacesContext().addMessage(null, facesMessage);
	} // fim do m�todo inserirMensagemInformativa
	
	/**
	 * M�todo utilizado para inserir uma mensagem de erro no contexto.
	 * @param mensagem que ser� exibida na view.
	 */
	public void inserirMensagemDeErro(String mensagem) {
		FacesMessage facesMessage = this.preencherMensagem(FacesMessage.SEVERITY_ERROR, 
				getMessageSource().getMessage("rotulo.mensagem.erro", null, this.obterLocalCorrente()), 
				mensagem);
		this.obterFacesContext().addMessage(null, facesMessage);
	} // fim do m�todo inserirMensagemDeErro
	
	/**
	 * M�todo utilizado para preencher um FacesMessage.
	 * @param severidade da mensagem
	 * @param mensagemRotulo mensagem do rotulo.
	 * @param mensagem que ser� exibida.
	 * @return FacesMessage preenchido.
	 */
	private FacesMessage preencherMensagem(Severity severidade, String mensagemRotulo, String mensagem) {
		FacesMessage facesMessage = new FacesMessage(severidade, mensagemRotulo, mensagem);
		return facesMessage;
	} // fim do m�otodo preencherMensagem
	
	public MessageSource getMessageSource() {
		return messageSource;
	}

} // fim da classe JSFUtil