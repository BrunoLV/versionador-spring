package com.porto.amazonas.versionamento.web.controller;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.EnumStatus;
import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.service.DependenciaService;
import com.porto.amazonas.versionamento.service.SistemaService;
import com.porto.amazonas.versionamento.web.datamodel.SistemaDataModel;
import com.porto.amazonas.versionamento.web.util.JSFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import java.io.Serializable;

/**
 * Classe Controller que faz a ponte entre visualização e classes de servico na
 * aplicacao para acaes com Sistemas.
 *
 * @author BRUNO VIANA
 */
@Controller
@Qualifier("sistemaController")
@Scope("request")
public class SistemaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String OUTCOME_LISTAR = "/paginas/private/listagens/listagemSistemas.xhtml?faces-redirect=true";
    private static final String OUTCOME_CADASTRAR = "/paginas/private/cadastros/cadastroSistema.xhtml?faces-redirect=true";
    private static final String OUTCOME_VERSOES = "/paginas/private/listagens/listagemVersoes.xhtml?faces-redirect=true";
    private static final String OUTCOME_DEPENDENCIAS = "/paginas/private/listagens/listagemDependenciaSistema.xhtml?faces-redirect=true";
    private Sistema sistema;
    private SistemaDataModel sistemas;
    @Autowired
    @Qualifier("sistemaService")
    private SistemaService sistemaService;
    @Autowired
    @Qualifier("dependenciaService")
    private DependenciaService dependenciaService;
    @Autowired
    @Qualifier("jsfUtil")
    private JSFUtil jsfUtil;

    @PostConstruct
    public void inicializarBean() {
        inicializarSistema();
        inicializarSistemas();
    } // fim do Metodo inicializarBean

    private void inicializarSistema() {
        this.sistema = new Sistema();
    } // fim do Metodo inicializarSistema

    private void inicializarSistemas() {
        try {
            this.sistemas = new SistemaDataModel(this.sistemaService.listarTodos());
        } catch (VersionamentoException e) {
            this.sistemas = new SistemaDataModel();
        } // fim do Metodo inicializarSistemas
    } // fim do Metodo inicializarSistemas

    /**
     * Metodo utilizado para acionar o servico de remocao de sistemas.
     *
     * @param evento disparado pelo componente faces da pagina.
     */
    public void deletarSistema(ActionEvent evento) {
        try {
            verificarSeExisteSistemaSelecionado();
            this.sistemaService.deletar(this.sistema);
            inicializarBean();
            this.jsfUtil.inserirMensagemInformativa(this.jsfUtil.getMessageSource().getMessage("mensagem.operacao.sucesso", null, this.jsfUtil.obterLocalCorrente()));
        } catch (VersionamentoException e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } catch (RuntimeException e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } // fim do bloco try/catch
    } // fim do Metodo deletarSistema

    /**
     * Metodo utilizado para acionar o servico de adicao/edicao de sistemas.
     *
     * @return outcome representando qual o fluxo a aplicacao ira seguir.
     */
    public String salvarSistema() {
        String outcome = null;
        if ((this.sistema.getId() != null) && (this.sistema.getId().equals(Integer.valueOf(0)))) {
            this.sistema.setId(null);
        } // fim do bloco if
        try {
            this.sistemaService.salvar(this.sistema);
            inicializarBean();
            this.jsfUtil.retirarAtributoDoSessionScope("sistema");
            this.jsfUtil.inserirMensagemInformativa(this.jsfUtil.getMessageSource().getMessage("mensagem.operacao.sucesso", null, this.jsfUtil.obterLocalCorrente()));
            outcome = OUTCOME_LISTAR;
        } catch (VersionamentoException e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } // fim do bloco try/catch
        return outcome;
    } // fim do Metodo salvarSistema

    /**
     * Metodo utilizado para direcionar o sistema para a acao de edicao de
     * sistemas.
     *
     * @return outcome representando qual fluxo a aplicacao deve seguir.
     */
    public String editarSistemaSelecionado() {
        String outcome = null;
        try {
            verificarSeExisteSistemaSelecionado();
            this.jsfUtil.obterSessionScope().setAttribute("sistema", this.sistema);
            outcome = OUTCOME_CADASTRAR;
        } catch (VersionamentoException e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } // fim do bloco try/catch
        return outcome;
    } // fim do Metodo editarSistemaSelecionado

    /**
     * Metodo utilizado para direcionar o sistema para a tela de cadastro de
     * aplicacaes e preparar o sistema para o cadastro.
     *
     * @return outcome representando qual fluxo a aplicacao deve seguir.
     */
    public String cadastrarSistema() {
        this.jsfUtil.obterSessionScope().setAttribute("sistema", new Sistema());
        return OUTCOME_CADASTRAR;
    } // fim do Metodo cadastrarSistema

    /**
     * Metodo utilizado para direcionar o sistema para a acao de visualizar de
     * versoes de um sistema selecionado na tela.
     *
     * @return outcome representando qual fluxo a aplicacao deve seguir.
     */
    public String listarVersoes() {
        String outcome = null;
        try {
            verificarSeExisteSistemaSelecionado();
            this.jsfUtil.obterSessionScope().setAttribute("sistema", this.sistema);
            outcome = OUTCOME_VERSOES;
        } catch (VersionamentoException e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } // fim do bloco try/catch
        return outcome;
    } // fim do Metodo listarVersoes

    public String listarDependencias() {
        String outcome = null;
        try {
            verificarSeExisteSistemaSelecionado();
            System.out.println(this.sistema.getId());
            this.jsfUtil.obterSessionScope().setAttribute("dependencias", this.dependenciaService.listarDependenciasDeUmSistema(this.sistema));
            outcome = OUTCOME_DEPENDENCIAS;
        } catch (VersionamentoException e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        }
        return outcome;
    }

    /*
     * Metodo utilizado para verificar se existe sistema selecionado para
     * operacaes.
     */
    private void verificarSeExisteSistemaSelecionado() throws VersionamentoException {
        if (this.sistema != null) {
            if (this.sistema.getId() == null || this.sistema.getId().equals(Integer.valueOf(0))) {
                throw new VersionamentoException(this.jsfUtil.getMessageSource().getMessage("mensagem.erro.selecao", null, this.jsfUtil.obterLocalCorrente()));
            } // fim do bloco if
        } else {
            throw new VersionamentoException(this.jsfUtil.getMessageSource().getMessage("mensagem.erro.selecao", null, this.jsfUtil.obterLocalCorrente()));
        } // fim do bloco if/else
    } // fim do Metodo verificarSeExisteSistemaSelecionado

    public Sistema getSistema() {
        return this.sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public SistemaDataModel getSistemas() {
        return this.sistemas;
    }

    public void setSistemas(SistemaDataModel sistemas) {
        this.sistemas = sistemas;
    }

    public EnumStatus[] getEnumStatus() {
        EnumStatus[] enumStatus = EnumStatus.values();
        return enumStatus;
    }

} // fim da classe SistemaController