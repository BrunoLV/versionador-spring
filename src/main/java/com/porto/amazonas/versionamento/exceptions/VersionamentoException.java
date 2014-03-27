package com.porto.amazonas.versionamento.exceptions;

/**
 * Exception personalizada para encapsular as exece��es lan�adas durante a
 * execu�ão da aplica�ão.
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