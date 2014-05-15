package com.porto.amazonas.versionamento.web.controller;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.model.Versao;
import com.porto.amazonas.versionamento.service.VersaoService;
import com.porto.amazonas.versionamento.web.datamodel.VersaoDataModel;
import com.porto.amazonas.versionamento.web.util.JSFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import java.io.Serializable;

/**
 * Classe Controller que faz a ponte entre visualizacao e classes de servico na aplicacao
 * para acoes com Versoes.
 *
 * @author BRUNO VIANA
 */
@Controller
@Qualifier("versaoController")
@Scope("request")
public class VersaoController implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String OUTCOME_LISTAR = "/paginas/private/listagens/listagemVersoes.xhtml?faces-redirect=true";
    private static final String OUTCOME_CADASTRAR = "/paginas/private/cadastros/cadastroVersao.xhtml?faces-redirect=true";
    private Versao versao;
    private VersaoDataModel versoes;
    @Autowired
    @Qualifier("versaoService")
    private VersaoService versaoService;
    @Autowired
    @Qualifier("jsfUtil")
    private JSFUtil jsfUtil;

    @PostConstruct
    public void inicializarBean() {
        inicializarVersao();
        inicializarVersoes();
    } // fim do metodo inicializarBean

    public void inicializarVersao() {
        this.versao = new Versao();
    } // fim do metodo inicializarVersao

    public void inicializarVersoes() {
        try {
            this.versoes = new VersaoDataModel(this.versaoService.listarTodasVersoesDeUmSistema((Sistema) this.jsfUtil.obterSessionScope().getAttribute("sistema")));
        } catch (VersionamentoException e) {
            this.versoes = new VersaoDataModel();
        } // fim do bloco try/catch
    } // fim do metodo inicializarVersoes

    /**
     * metodo utilizado para acionar o servico de remocao de versao da aplicacao.
     *
     * @param evento disparado pelo componente faces da pagina.
     */
    public void deletarVersao(ActionEvent evento) {
        try {
            verificarSeExisteVersaoSelecionada();
            this.versaoService.deletar(this.versao);
            inicializarBean();
            this.jsfUtil.inserirMensagemInformativa(this.jsfUtil.getMessageSource().getMessage("mensagem.operacao.sucesso", null, this.jsfUtil.obterLocalCorrente()));
        } catch (VersionamentoException e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } // fim do bloco try/catch
    } // fim do metodo deletarVersao

    /**
     * metodo utilizado para acionar o servico de insercao/atualizacao de versoes na aplicacao.
     *
     * @return outcome representando o fluxo que a aplicacao ira seguir.
     */
    public String salvarVersao() {
        String outcome = null;
        if ((this.versao.getId() != null) && (this.versao.getId().equals(Integer.valueOf(0)))) {
            this.versao.setId(null);
        } // fim do bloco if
        try {
            this.versao.setSistema((Sistema) this.jsfUtil.obterSessionScope().getAttribute("sistema"));
            this.versaoService.salvar(this.versao);
            inicializarBean();
            this.jsfUtil.retirarAtributoDoSessionScope("versao");
            this.jsfUtil.inserirMensagemInformativa(this.jsfUtil.getMessageSource().getMessage("mensagem.operacao.sucesso", null, this.jsfUtil.obterLocalCorrente()));
            outcome = OUTCOME_LISTAR;
        } catch (VersionamentoException e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } // fim do bloco try/catch
        return outcome;
    } // fim do metodo salvarVersao

    /**
     * metodo utilizado para direcionar o sistema para a acao de edicao de
     * versao.
     *
     * @return outcome representando qual fluxo a aplicacao deve seguir.
     */
    public String editarVersaoSelecionada() {
        String outcome = null;
        try {
            verificarSeExisteVersaoSelecionada();
            this.jsfUtil.obterSessionScope().setAttribute("versao", this.versao);
            outcome = OUTCOME_CADASTRAR;
        } catch (VersionamentoException e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } // fim do bloco try/catch
        return outcome;
    } // fim do método editarVersaoSelecionada

    /**
     * metodo utilizado para direcionar o sistema para a tela de cadastro de versoes
     * e preparar o sistema para a acao.
     *
     * @return outcome representando qual fluxo a aplicacao deve seguir.
     */
    public String cadastrarVersao() {
        this.jsfUtil.obterSessionScope().setAttribute("versao", new Versao());
        return OUTCOME_CADASTRAR;
    } // fim do metodo cadastrarVersao

    /*
     * metodo utilizado para verificar se existe versao selecionada para operacoes.
     */
    private void verificarSeExisteVersaoSelecionada() throws VersionamentoException {
        if (this.versao != null) {
            if (this.versao.getId() == null || this.versao.getId().equals(Integer.valueOf(0))) {
                throw new VersionamentoException(this.jsfUtil.getMessageSource().getMessage("mensagem.erro.selecao", null, this.jsfUtil.obterLocalCorrente()));
            } // fim do bloco if
        } else {
            throw new VersionamentoException(this.jsfUtil.getMessageSource().getMessage("mensagem.erro.selecao", null, this.jsfUtil.obterLocalCorrente()));
        } // fim do bloco if/else
    } // fim do metodo verificarSeExisteVersaoSelecionada

    public Versao getVersao() {
        return versao;
    }

    public void setVersao(Versao versao) {
        this.versao = versao;
    }

    public VersaoDataModel getVersoes() {
        return versoes;
    }

    public void setVersoes(VersaoDataModel versoes) {
        this.versoes = versoes;
    }

} // fim da classe VersaoController