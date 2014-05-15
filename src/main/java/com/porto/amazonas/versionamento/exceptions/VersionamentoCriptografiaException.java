package com.porto.amazonas.versionamento.exceptions;

public class VersionamentoCriptografiaException extends Exception {

    private static final long serialVersionUID = 1L;

    public VersionamentoCriptografiaException() {
    }

    public VersionamentoCriptografiaException(Throwable causa) {
        super(causa);
    }

    public VersionamentoCriptografiaException(String mensagem) {
        super(mensagem);
    }

    public VersionamentoCriptografiaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}
