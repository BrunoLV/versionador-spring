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
 * Classe utilitária para lidar com os escopos web da aplicação.
 * @author BRUNO VIANA
 */
@Component
@Qualifier("jsfUtil")
public class JSFUtil {
	
	@Autowired
	@Qualifier("messageSource")
	private MessageSource messageSource;
	
	/**
	 * Método utilizado para obter o FacesContext da aplicação.
	 * @return context Faces da aplicação.
	 */
	public FacesContext obterFacesContext() {
		return FacesContext.getCurrentInstance();
	} // fim do método obterFacesContext

	/**
	 * Método utilizado para obter o SessionScope corrente.
	 * @return sessão do usuário.
	 */
	public HttpSession obterSessionScope() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	} // fim do método obterSessionScope

	/**
	 * Método utilizado para retirar um atributo do SessionScope.
	 * @param nomeAtributo que irá ser retirado do escopo.
	 */
	public void retirarAtributoDoSessionScope(String nomeAtributo) {
		obterSessionScope().removeAttribute(nomeAtributo);
	} // fim do método retirarAtributoDoSessionScope
	
	/**
	 * Método utilizado para obter a localização corrente da aplicação.
	 * @return local em que a aplicação esta rodando.
	 */
	public Locale obterLocalCorrente() {
		return obterFacesContext().getViewRoot().getLocale();
	} // fim do método obterLocalCorrente
	
	/**
	 * Método utilizado para inserir uma mensagem informativa no contexto.
	 * @param mensagem que será exibida na view.
	 */
	public void inserirMensagemInformativa(String mensagem) {
		FacesMessage facesMessage = this.preencherMensagem(FacesMessage.SEVERITY_INFO, 
				getMessageSource().getMessage("rotulo.mensagem.info", null, this.obterLocalCorrente()), 
				mensagem);
		this.obterFacesContext().addMessage(null, facesMessage);
	} // fim do método inserirMensagemInformativa
	
	/**
	 * Método utilizado para inserir uma mensagem de erro no contexto.
	 * @param mensagem que será exibida na view.
	 */
	public void inserirMensagemDeErro(String mensagem) {
		FacesMessage facesMessage = this.preencherMensagem(FacesMessage.SEVERITY_ERROR, 
				getMessageSource().getMessage("rotulo.mensagem.erro", null, this.obterLocalCorrente()), 
				mensagem);
		this.obterFacesContext().addMessage(null, facesMessage);
	} // fim do método inserirMensagemDeErro
	
	/**
	 * Método utilizado para preencher um FacesMessage.
	 * @param severidade da mensagem
	 * @param mensagemRotulo mensagem do rotulo.
	 * @param mensagem que será exibida.
	 * @return FacesMessage preenchido.
	 */
	private FacesMessage preencherMensagem(Severity severidade, String mensagemRotulo, String mensagem) {
		FacesMessage facesMessage = new FacesMessage(severidade, mensagemRotulo, mensagem);
		return facesMessage;
	} // fim do méotodo preencherMensagem
	
	public MessageSource getMessageSource() {
		return messageSource;
	}

} // fim da classe JSFUtil