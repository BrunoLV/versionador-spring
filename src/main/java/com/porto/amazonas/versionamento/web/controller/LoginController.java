package com.porto.amazonas.versionamento.web.controller;

import com.porto.amazonas.versionamento.criptografia.Criptografia;
import com.porto.amazonas.versionamento.exceptions.VersionamentoCriptografiaException;
import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.EnumPapel;
import com.porto.amazonas.versionamento.model.Papel;
import com.porto.amazonas.versionamento.model.Usuario;
import com.porto.amazonas.versionamento.security.GerenciadorAutenticacao;
import com.porto.amazonas.versionamento.service.UsuarioService;
import com.porto.amazonas.versionamento.web.util.JSFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.HashSet;

/**
 * Classe Controller que faz a ponte entre visualizacao e classes service para Login na aplicacao.
 *
 * @author BRUNO VIANA
 */
@Controller
@Qualifier("loginController")
@Scope("request")
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String OUTCOME_INDEX = "/paginas/public/index.xhtml?faces-redirect=true";
    private static final String OUTCOME_SUCESSO = "/paginas/private/listagens/listagemSistemas.xhtml?faces-redirect=true";
    private static final String OUTCOME_FALHA = "/paginas/public/loginErro.xhtml?faces-redirect=true";
    private static final String OUTCOME_SUCESSO_CADASTRO = "/paginas/public/cadastroSucesso.xhtml?faces-redirect=true";
    private String matricula;
    private String senha;
    private String novaSenha;
    private String confirmaSenha;
    private Usuario usuario;
    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioService;
    @Autowired
    @Qualifier("gerenciadorAutenticacao")
    private GerenciadorAutenticacao gerenciadorAutenticacao;
    @Autowired
    @Qualifier("jsfUtil")
    private JSFUtil jsfUtil;
    @Autowired
    @Qualifier("criptografiaSHA512")
    private Criptografia criptografia;

    @PostConstruct
    public void inicializarBean() {
        this.inicializarUsuario();
    }

    public void inicializarUsuario() {
        this.usuario = new Usuario();
        this.usuario.setPapeis(new HashSet<Papel>());
    }

    /**
     * metodo utilizado para execucao a acao de login na aplicacao.
     *
     * @return outcome representando o fluxo que a aplicacao deve seguir.
     */
    public String logar() {
        String outcome = null;
        try {
            this.criptografarSenhas();
            if (this.gerenciadorAutenticacao.executarLogin(this.matricula, this.senha) == true) {
                outcome = OUTCOME_SUCESSO;
            } // fim do bloco if
        } catch (Exception e) {
            outcome = OUTCOME_FALHA;
        } // fim do bloco try/catch
        return outcome;
    } // fim do metodo logar

    /**
     * metodo utilizado para execucao da acao de cadastro inicial de usuarios na aplicacao.
     *
     * @return outcome representando o fluxo que a aplicacao deve seguir.
     */
    public String efetuarCadastroInicial() {
        String outcome = null;
        try {
            this.criptografarSenhas();
            this.compararSenhas(this.senha, this.confirmaSenha);
            usuario.setSenha(this.senha);
            usuario.setAtivo(Boolean.TRUE);
            usuarioService.cadastrarUsuarioComDeterminadoPapel(usuario, EnumPapel.ROLE_CONVIDADO);
            outcome = OUTCOME_SUCESSO_CADASTRO;
        } catch (Exception e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } // fim do bloco try/catch
        return outcome;
    } // fim do metodo efetuarCadastroInicial

    /**
     * metodo utilizado para execucao da acao de atualizacao de senha de usuarios na aplicacao.
     *
     * @param event passado pelo JSF
     */
    public void atualizarSenhaDeAcesso(ActionEvent event) {
        try {
            validarEConferirSenhas(this.getUsuarioLogado());
            this.getUsuarioLogado().setSenha(this.novaSenha);
            this.usuarioService.editar(this.getUsuarioLogado());
            this.jsfUtil.inserirMensagemInformativa(this.jsfUtil.getMessageSource().getMessage("mensagem.atualizacaosenha.sucesso", null, this.jsfUtil.obterLocalCorrente()));
        } catch (Exception e) {
            this.jsfUtil.inserirMensagemDeErro(e.getMessage());
        } // fim do bloco try/catch
    } // fim do metodo atualizarSenhaDeAcesso

    /**
     * metodo utilizado para execucao a acao de deslogar na aplicacao.
     *
     * @return outcome representando o fluxo que a aplicacao deve seguir.
     */
    public String deslogar() {
        this.gerenciadorAutenticacao.executarLogout(this.jsfUtil.obterSessionScope());
        return OUTCOME_INDEX;
    } // fim do metodo deslogar

    /*
     * metodo utilizado para validar as senhas informadas na tela.
     */
    private void validarEConferirSenhas(Usuario usuario) throws VersionamentoCriptografiaException, VersionamentoException {
        criptografarSenhas();
        compararSenhas(this.novaSenha, this.confirmaSenha);
        if (!usuario.getPassword().equals(this.senha)) {
            throw new VersionamentoException(this.jsfUtil.getMessageSource().getMessage("mensagem.erro.confirmasenha", null, this.jsfUtil.obterLocalCorrente()));
        } // fim do bloco if
    } // fim do metodo validarEConferirSenhas

    /*
     * metodo utilizado para validar as senhas inseridas nos formularios de cadastro.
     */
    private void compararSenhas(String senha1, String senha2) throws VersionamentoException {
        if (!senha1.equals(senha2)) {
            throw new VersionamentoException(this.jsfUtil.getMessageSource().getMessage("mensagem.erro.confirmasenha", null, this.jsfUtil.obterLocalCorrente()));
        } // fim do bloco if
    } // fim do metodo validarSenhas

    /*
     * metodo utilizado para criptografar as senhas.
     */
    private void criptografarSenhas() throws VersionamentoCriptografiaException {
        if (senha != null && !senha.isEmpty()) {
            senha = this.criptografarDado(senha);
        } // fim do bloco if
        if (novaSenha != null && !novaSenha.isEmpty()) {
            novaSenha = this.criptografarDado(novaSenha);
        } // fim do bloco if
        if (confirmaSenha != null && !confirmaSenha.isEmpty()) {
            confirmaSenha = this.criptografarDado(confirmaSenha);
        } // fim do bloco if
    } // fim do metodo criptografarSenhas

    /*
     * metodo utilizado para criptografar uma informacao.
     */
    private String criptografarDado(String dado) throws VersionamentoCriptografiaException {
        return this.criptografia.criptografar(dado);
    } // fim do metodo criptografaDado

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioLogado() {
        return this.gerenciadorAutenticacao.getUsuarioLogado();
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

} // fim da classe LoginController