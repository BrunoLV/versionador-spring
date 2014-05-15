package com.porto.amazonas.versionamento.web.controller;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.Dependencia;
import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.service.DependenciaService;
import com.porto.amazonas.versionamento.service.SistemaService;
import com.porto.amazonas.versionamento.web.datamodel.DependenciaDataModel;
import com.porto.amazonas.versionamento.web.datamodel.SistemaDataModel;
import com.porto.amazonas.versionamento.web.util.JSFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Controller que faz a ponte entre visualizacao e classes de servico na
 * aplicacao para acoes com Dependencias.
 *
 * @author BRUNO VIANA
 */
@Controller
@Qualifier("dependenciaController")
@Scope("request")
public class DependenciaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String OUTCOME_LISTAR = "/paginas/private/listagens/listagemDependencia.xhtml?faces-redirect=true";
    private static final String OUTCOME_CADASTRAR = "/paginas/private/cadastros/cadastroDependencia.xhtml?faces-redirect=true";
    private static final String OUTCOME_SISTEMAS = "/paginas/private/listagens/listagemSistemaDependencia.xhtml?faces-redirect=true";
    private Dependencia dependencia;
    private DependenciaDataModel dependencias;
    private SistemaDataModel sistemas;
    private List<Sistema> sistemasSelecionados;
    @Autowired
    @Qualifier("dependenciaService")
    private DependenciaService dependenciaService;
    @Autowired
    @Qualifier("sistemaService")
    private SistemaService sistemaService;
    @Autowired
    @Qualifier("jsfUtil")
    private JSFUtil jsfUtil;

    @PostConstruct
    public void inicializarBean() {
        inicializarDependencia();
        inicializarDependencias();
        inicializarSistemas();
    } // fim do metodo inicializarBean

    public void inicializarDependencia() {
        this.dependencia = new Dependencia();
        this.dependencia.setSistemas(new ArrayList<Sistema>());
    } // fim do metodo inicializarDependencia

    public void inicializarDependencias() {
        try {
            this.dependencias = new DependenciaDataModel(this.dependenciaService.listarTodos());
        } catch (VersionamentoException e) {
            this.dependencias = new DependenciaDataModel();
        } // fim do bloco try/catch
    } // fim do metodo inicializarDependencias

    public void inicializarSistemas() {
        try {
            this.sistemas = new SistemaDataModel(this.sistemaService.listarTodos());
        } catch (VersionamentoException e) {
            this.sistemas = new SistemaDataModel();
        } // fim do bloco try/catch
    } // fim do metodo inicializarSistemas

    /**
     * metodo utilizado para direcionar o sistema para a tela de cadastro de
     * dependencias e praparar o sistema para o cadastro.
     *
     * @return outcome representando qual fluxo a aplicacao deve seguir.
     */
    public String cadastrarDependencia() {
        this.jsfUtil.obterSessionScope().setAttribute("dependencia", new Dependencia());
        return OUTCOME_CADASTRAR;
    } // fim do metodo cadastrarDependencia

    /**
     * metodo utilizado para acionar o serviço de insercao/atualizacao de sistemas.
     *
     * @return outcome representando qual fluxo a aplicacao deve seguir.
     */
    public String salvarDependencia() {
        String outcome = null;
        if (this.dependencia.getId() != null && this.dependencia.getId().equals(Integer.valueOf(0))) {
            this.dependencia.setId(null);
        } // fim do bloco if
        try {
            this.dependenciaService.salvar(this.dependencia);
            inicializarBean();
            this.jsfUtil.retirarAtributoDoSessionScope("dependencia");
            this.jsfUtil.inserirMensagemInformativa(this.jsfUtil.getMessageSource().getMessage("mensagem.operacao.sucesso", null, this.jsfUtil.obterLocalCorrente()));
            outcome = OUTCOME_LISTAR;
        } catch (VersionamentoException e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } // fim do bloco try/catch
        return outcome;
    } // fim do metodo salvarDependencia

    /**
     * metodo utilizado para acionar o servico de remocao de dependencias.
     *
     * @param evento disparado pelo componente faces da pagina.
     */
    public void deletarDependencia(ActionEvent evento) {
        try {
            verificarSeExisteDependenciaSelecionada();
            this.dependenciaService.deletar(this.dependencia);
            inicializarBean();
            this.jsfUtil.inserirMensagemInformativa(this.jsfUtil.getMessageSource().getMessage("mensagem.operacao.sucesso", null, this.jsfUtil.obterLocalCorrente()));
        } catch (VersionamentoException e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } // fim do bloco try/catch
    } // fim do metodo deletarDependencia

    /**
     * metodo utiliza para direcionar o sistema para a acao de edicao de
     * dependencias.
     *
     * @return outcome representando o fluxo que a aplicacao deve seguir.
     */
    public String editarDependenciaSelecionada() {
        String outcome = null;
        try {
            verificarSeExisteDependenciaSelecionada();
            dependencia.setSistemas(this.dependenciaService.listarSistemasRelacionadosAUmaDependencia(this.dependencia));
            this.jsfUtil.obterSessionScope().setAttribute("dependencia", this.dependencia);
            outcome = OUTCOME_CADASTRAR;
        } catch (VersionamentoException e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } // fim do bloco try/catch
        return outcome;
    } // fim do metodo editarDependenciaSelecionada

    /*
     * metodo utilizado para verificar se existe dependencia selecionada para
     * operacoes.
     */
    private void verificarSeExisteDependenciaSelecionada() throws VersionamentoException {
        if (this.dependencia != null) {
            if (this.dependencia.getId() == null || this.dependencia.getId().equals(Integer.valueOf(0))) {
                throw new VersionamentoException(this.jsfUtil.getMessageSource().getMessage("mensagem.erro.selecao", null, this.jsfUtil.obterLocalCorrente()));
            } // fim do bloco if
        } else {
            throw new VersionamentoException(this.jsfUtil.getMessageSource().getMessage("mensagem.erro.selecao", null, this.jsfUtil.obterLocalCorrente()));
        } // fim do bloco if/else
    } // fim do metodo verificarSeExisteDependenciaSelecionada

    /**
     * metodo utilizado para listar todos os sistemas relacionados a uma determinada dependencia.
     *
     * @return outcome que representa o destino a ser seguido pela aplicacao.
     */
    public String listarSistemasRelacionados() {
        String outcome = null;
        try {
            verificarSeExisteDependenciaSelecionada();
            dependencia.setSistemas(this.dependenciaService.listarSistemasRelacionadosAUmaDependencia(this.dependencia));
            this.jsfUtil.obterSessionScope().setAttribute("dependencia", this.dependencia);
            outcome = OUTCOME_SISTEMAS;
        } catch (VersionamentoException e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } // fim do bloco try/catch
        return outcome;
    } // fim do metodo listarSistemasRelacionados

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public DependenciaDataModel getDependencias() {
        return dependencias;
    }

    public void setDependencias(DependenciaDataModel dependencias) {
        this.dependencias = dependencias;
    }

    public SistemaDataModel getSistemas() {
        return sistemas;
    }

    public void setSistemas(SistemaDataModel sistemas) {
        this.sistemas = sistemas;
    }

    public List<Sistema> getSistemasSelecionados() {
        return sistemasSelecionados;
    }

    public void setSistemasSelecionados(List<Sistema> sistemasSelecionados) {
        this.sistemasSelecionados = sistemasSelecionados;
    }

} // fim da classe DependenciaController