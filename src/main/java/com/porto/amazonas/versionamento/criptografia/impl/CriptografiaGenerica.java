package com.porto.amazonas.versionamento.criptografia.impl;

import com.porto.amazonas.versionamento.exceptions.VersionamentoCriptografiaException;
import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe generica de criptografia.
 *
 * @author BRUNO VIANA
 */
public abstract class CriptografiaGenerica {

    private MessageDigest messageDigest;
    private Base64 encoder;

    /**
     * Metodo utilizado para execucao de criptografia de uma String utilizando um algoritmo especifico.
     *
     * @param algoritmo de que sera utilizado.
     * @param valor     que sera criptografado.
     * @return
     * @throws VersionamentoCriptografiaException
     */
    protected String criptografarComAlgoritmo(String algoritmo, String valor) throws VersionamentoCriptografiaException {
        String stringHash = null;
        try {
            messageDigest = MessageDigest.getInstance(algoritmo);
            encoder = new Base64();
            if (valor == null) {
                throw new IllegalArgumentException("O valor e nulo");
            } // fim do bloco if
            byte[] hash = messageDigest.digest(valor.getBytes());
            stringHash = encoder.encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new VersionamentoCriptografiaException(e.getMessage(), e);
        } // fim do bloco try/catch
        return stringHash;
    } // fim do metodo criptografarComAlgoritmo

} // fim da classe CriptografiaGenerica
