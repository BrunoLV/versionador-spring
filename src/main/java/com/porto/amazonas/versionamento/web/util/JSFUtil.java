package com.porto.amazonas.versionamento.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Classe utilitaria para lidar com os escopos web da aplicacao.
 *
 * @author BRUNO VIANA
 */
@Component
@Qualifier("jsfUtil")
public class JSFUtil {

    @Autowired
    @Qualifier("messageSource")
    private MessageSource messageSource;

    /**
     * metodo utilizado para obter o FacesContext da aplicacao.
     *
     * @return context Faces da aplicacao.
     */
    public FacesContext obterFacesContext() {
        return FacesContext.getCurrentInstance();
    } // fim do metodo obterFacesContext

    /**
     * metodo utilizado para obter o SessionScope corrente.
     *
     * @return sessao do usuario.
     */
    public HttpSession obterSessionScope() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    } // fim do metodo obterSessionScope

    /**
     * metodo utilizado para retirar um atributo do SessionScope.
     *
     * @param nomeAtributo que ira ser retirado do escopo.
     */
    public void retirarAtributoDoSessionScope(String nomeAtributo) {
        obterSessionScope().removeAttribute(nomeAtributo);
    } // fim do metodo retirarAtributoDoSessionScope

    /**
     * metodo utilizado para obter a localizacao corrente da aplicacao.
     *
     * @return local em que a aplicacao esta rodando.
     */
    public Locale obterLocalCorrente() {
        return obterFacesContext().getViewRoot().getLocale();
    } // fim do metodo obterLocalCorrente

    /**
     * metodo utilizado para inserir uma mensagem informativa no contexto.
     *
     * @param mensagem que sera exibida na view.
     */
    public void inserirMensagemInformativa(String mensagem) {
        FacesMessage facesMessage = this.preencherMensagem(FacesMessage.SEVERITY_INFO,
                getMessageSource().getMessage("rotulo.mensagem.info", null, this.obterLocalCorrente()),
                mensagem);
        this.obterFacesContext().addMessage(null, facesMessage);
    } // fim do metodo inserirMensagemInformativa

    /**
     * metodo utilizado para inserir uma mensagem de erro no contexto.
     *
     * @param mensagem que sera exibida na view.
     */
    public void inserirMensagemDeErro(String mensagem) {
        FacesMessage facesMessage = this.preencherMensagem(FacesMessage.SEVERITY_ERROR,
                getMessageSource().getMessage("rotulo.mensagem.erro", null, this.obterLocalCorrente()),
                mensagem);
        this.obterFacesContext().addMessage(null, facesMessage);
    } // fim do metodo inserirMensagemDeErro

    /**
     * metodo utilizado para preencher um FacesMessage.
     *
     * @param severidade     da mensagem
     * @param mensagemRotulo mensagem do rotulo.
     * @param mensagem       que sera exibida.
     * @return FacesMessage preenchido.
     */
    private FacesMessage preencherMensagem(Severity severidade, String mensagemRotulo, String mensagem) {
        FacesMessage facesMessage = new FacesMessage(severidade, mensagemRotulo, mensagem);
        return facesMessage;
    } // fim do metodo preencherMensagem

    public MessageSource getMessageSource() {
        return messageSource;
    }

} // fim da classe JSFUtil