package com.porto.amazonas.versionamento.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.Dependencia;
import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.service.DependenciaService;
import com.porto.amazonas.versionamento.service.SistemaService;
import com.porto.amazonas.versionamento.web.datamodel.DependenciaDataModel;
import com.porto.amazonas.versionamento.web.datamodel.SistemaDataModel;
import com.porto.amazonas.versionamento.web.util.JSFUtil;

/**
 * Classe Controller que faz a ponte entre visualiza��o e classes de servi�o na
 * aplica��o para a��es com Dependencias.
 * @author BRUNO VIANA
 */
@Controller
@Qualifier("dependenciaController")
@Scope("request")
public class DependenciaController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
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
	
	private static final String OUTCOME_LISTAR = "/paginas/private/listagens/listagemDependencia.xhtml?faces-redirect=true";
	private static final String OUTCOME_CADASTRAR = "/paginas/private/cadastros/cadastroDependencia.xhtml?faces-redirect=true";
	private static final String OUTCOME_SISTEMAS = "/paginas/private/listagens/listagemSistemaDependencia.xhtml?faces-redirect=true";
	
	@PostConstruct
	public void inicializarBean() {
		inicializarDependencia();
		inicializarDependencias();
		inicializarSistemas();
	} // fim do m�todo inicializarBean
	
	public void inicializarDependencia() {
		this.dependencia = new Dependencia();
		this.dependencia.setSistemas(new ArrayList<Sistema>());
	} // fim do m�todo inicializarDependencia
	
	public void inicializarDependencias() {
		try {
			this.dependencias = new DependenciaDataModel(this.dependenciaService.listarTodos());
		} catch (VersionamentoException e) {
			this.dependencias = new DependenciaDataModel();
		} // fim do bloco try/catch
	} // fim do m�todo inicializarDependencias
	
	public void inicializarSistemas() {
		try {
			this.sistemas = new SistemaDataModel(this.sistemaService.listarTodos());
		} catch (VersionamentoException e) {
			this.sistemas = new SistemaDataModel();
		} // fim do bloco try/catch
	} // fim do m�todo inicializarSistemas
	
	/**
	 * M�todo utilizado para direcionar o sistema para a tela de cadastro de
	 * dependencias e praparar o sistema para o cadastro.
	 * @return outcome representando qual fluxo a aplica��o deve seguir.
	 */
	public String cadastrarDependencia() {
		this.jsfUtil.obterSessionScope().setAttribute("dependencia", new Dependencia());
		return OUTCOME_CADASTRAR;
	} // fim do m�todo cadastrarDependencia
	
	/**
	 * M�todo utilizado para acionar o serviço de adi��o/edi��o de sistemas.
	 * @return outcome representando qual fluxo a aplica��o deve seguir.
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
	} // fim do m�todo salvarDependencia
	
	/**
	 * M�todo utilizado para acionar o servi�o de remo��o de dependencias.
	 * @param evento disparado pelo componente faces da p�gina.
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
	} // fim do m�todo deletarDependencia
	
	/**
	 * M�todo utiliza para direcionar o sistema para a a��o de edi��o de 
	 * dependencias.
	 * @return outcome representando o fluxo que a aplica��o deve seguir.
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
	} // fim do m�todo editarDependenciaSelecionada
	
	/*
	 * M�todo utilizado para verificar se existe dependencia selecionada para
	 * opera��es.
	 */
	private void verificarSeExisteDependenciaSelecionada() throws VersionamentoException {
		if (this.dependencia != null) {
			if (this.dependencia.getId() == null || this.dependencia.getId().equals(Integer.valueOf(0))) {
				throw new VersionamentoException(this.jsfUtil.getMessageSource().getMessage("mensagem.erro.selecao", null, this.jsfUtil.obterLocalCorrente()));
			} // fim do bloco if
		} else {
			throw new VersionamentoException(this.jsfUtil.getMessageSource().getMessage("mensagem.erro.selecao", null, this.jsfUtil.obterLocalCorrente()));
		} // fim do bloco if/else
	} // fim do m�todo verificarSeExisteDependenciaSelecionada
	
	/**
	 * M�todo utilizado para listar todos os sistemas relacionados a uma determinada dependencia.
	 * @return outcome que representa o destino a ser seguido pela aplica��o.
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
	} // fim do m�todo listarSistemasRelacionados
	
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