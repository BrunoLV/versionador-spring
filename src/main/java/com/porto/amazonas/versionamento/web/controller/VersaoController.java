package com.porto.amazonas.versionamento.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.Sistema;
import com.porto.amazonas.versionamento.model.Versao;
import com.porto.amazonas.versionamento.service.VersaoService;
import com.porto.amazonas.versionamento.web.datamodel.VersaoDataModel;
import com.porto.amazonas.versionamento.web.util.JSFUtil;

/**
 * Classe Controller que faz a ponte entre visualização e classes de serviço na aplicação
 * para ações com Versões.
 * @author BRUNO VIANA
 */
@Controller
@Qualifier("versaoController")
@Scope("request")
public class VersaoController implements Serializable {
	private static final long serialVersionUID = 1L;

	private Versao versao;
	private VersaoDataModel versoes;

	@Autowired
	@Qualifier("versaoService")
	private VersaoService versaoService;

	@Autowired
	@Qualifier("jsfUtil")
	private JSFUtil jsfUtil;

	private static final String OUTCOME_LISTAR = "/paginas/private/listagens/listagemVersoes.xhtml?faces-redirect=true";
	private static final String OUTCOME_CADASTRAR = "/paginas/private/cadastros/cadastroVersao.xhtml?faces-redirect=true";

	@PostConstruct
	public void inicializarBean() {
		inicializarVersao();
		inicializarVersoes();
	} // fim do método inicializarBean

	public void inicializarVersao() {
		this.versao = new Versao();
	} // fim do método inicializarVersao

	public void inicializarVersoes() {
		try {
			this.versoes = new VersaoDataModel(this.versaoService.listarTodasVersoesDeUmSistema((Sistema)this.jsfUtil.obterSessionScope().getAttribute("sistema")));
		} catch (VersionamentoException e) {
			this.versoes = new VersaoDataModel();
		} // fim do bloco try/catch
	} // fim do método inicializarVersoes

	/**
	 * Método utilizado para acionar o serviço de remoção de versão da aplicação.
	 * @param evento disparado pelo componente faces da página.
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
	} // fim do método deletarVersao

	/**
	 * Método utilizado para acionar o serviço de adição/editação de versões na aplicação.
	 * @return outcome representando o fluxo que a aplicação irá seguir.
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
	} // fim do método salvarVersao
	
	/**
	 * Método utilizado para direcionar o sistema para a ação de edição de 
	 * versão.
	 * @return outcome representando qual fluxo a aplicação deve seguir.
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
	} // fim do mÃ©todo editarVersaoSelecionada

	/**
	 * Método utilizado para direcionar o sistema para a tela de cadastro de versões
	 * e preparar o sistema para a ação.
	 * @return outcome representando qual fluxo a aplicação deve seguir.
	 */
	public String cadastrarVersao() {
		this.jsfUtil.obterSessionScope().setAttribute("versao", new Versao());
		return OUTCOME_CADASTRAR;
	} // fim do método cadastrarVersao
	
	/*
	 * Método utilizado para verificar se existe versão selecionada para operações.
	 */
	private void verificarSeExisteVersaoSelecionada() throws VersionamentoException {
		if(this.versao != null) {
			if(this.versao.getId() == null || this.versao.getId().equals(Integer.valueOf(0))) {
				throw new VersionamentoException(this.jsfUtil.getMessageSource().getMessage("mensagem.erro.selecao", null, this.jsfUtil.obterLocalCorrente()));
			} // fim do bloco if
		} else {
			throw new VersionamentoException(this.jsfUtil.getMessageSource().getMessage("mensagem.erro.selecao", null, this.jsfUtil.obterLocalCorrente()));
		} // fim do bloco if/else
	} // fim do método verificarSeExisteVersaoSelecionada

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