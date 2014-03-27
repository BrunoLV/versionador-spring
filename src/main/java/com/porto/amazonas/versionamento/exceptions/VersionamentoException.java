package com.porto.amazonas.versionamento.exceptions;

/**
 * Exception personalizada para encapsular as execeções lançadas durante a
 * execuçÃ£o da aplicaçÃ£o.
 * @author BRUNO VIANA
 */
public class VersionamentoException extends Exception {

	private static final long serialVersionUID = 1L;

	public VersionamentoException() {
	}

	public VersionamentoException(String mensagem) {
		super(mensagem);
	}

	public VersionamentoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

} // fim do da classe VersionamentoException