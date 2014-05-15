package com.porto.amazonas.versionamento.web.listener;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Classe que trata das mensagens na aplicacao para que as mensagens sejam apresentadas corretamente entre as
 * requests no fluxo da aplicacao.
 *
 * @author BRUNO VIANA
 */
public class MessagesListener implements PhaseListener {

    private static final long serialVersionUID = 1L;
    private static final String tokenSessao = "MESSAGES_LISTENER";

    @Override
    public void afterPhase(PhaseEvent event) {
        if (!PhaseId.RENDER_RESPONSE.equals(event.getPhaseId())) {
            FacesContext facesContext = event.getFacesContext();
            this.salvarMensagens(facesContext);
        } // fim do bloco if
    } // fim do metodo afterPhase

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        this.salvarMensagens(facesContext);

        if (PhaseId.RENDER_RESPONSE.equals(event.getPhaseId())) {
            if (!facesContext.getResponseComplete()) {
                this.restaurarMensagens(facesContext);
            } // fim do bloco if
        } // fim do bloco if
    } // fim do metodo beforePhase

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    } // fim do metodo getPhaseId

    @SuppressWarnings("unchecked")
    private int salvarMensagens(final FacesContext facesContext) {
        List<FacesMessage> mensagens = new ArrayList<FacesMessage>();
        for (Iterator<FacesMessage> iter = facesContext.getMessages(null); iter.hasNext(); ) {
            mensagens.add(iter.next());
            iter.remove();
        } // fim do bloco for

        if (mensagens.size() == 0) {
            return 0;
        } // fim do bloco if

        Map<String, Object> mapaSessao = facesContext.getExternalContext().getSessionMap();
        List<FacesMessage> mensagensExistentes = (List<FacesMessage>) mapaSessao.get(tokenSessao);
        if (mensagensExistentes != null) {
            mensagensExistentes.addAll(mensagens);
        } else {
            mapaSessao.put(tokenSessao, mensagens);
        } // fim do bloco if/else
        return mensagens.size();
    } // fim do metodo salvarMensagens

    @SuppressWarnings("unchecked")
    private int restaurarMensagens(final FacesContext facesContext) {
        Map<String, Object> mapaSessao = facesContext.getExternalContext().getSessionMap();
        List<FacesMessage> mensagens = (List<FacesMessage>) mapaSessao.remove(tokenSessao);

        if (mensagens == null) {
            return 0;
        } // fim do bloco if

        int quantidadeMensagensRestauradas = mensagens.size();
        for (Object elemento : mensagens) {
            facesContext.addMessage(null, (FacesMessage) elemento);
        } // fim do bloco for
        return quantidadeMensagensRestauradas;
    } // fim do metodo restaurarMensagens

} // fim da classe MessagesListener