package com.porto.amazonas.versionamento.criptografia.impl;

import com.porto.amazonas.versionamento.criptografia.Criptografia;
import com.porto.amazonas.versionamento.exceptions.VersionamentoCriptografiaException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Componente de criptografia que utiliza o algoritmo SHA-512.
 *
 * @author BRUNO VIANA
 */
@Component
@Qualifier("criptografiaSHA512")
public class CriptografiaSHA512 extends CriptografiaGenerica implements Criptografia {

    private static final String ALGORITMO = "SHA-512";

    public CriptografiaSHA512() {
    } // metodo construtor

    @Override
    public String criptografar(String valor) throws VersionamentoCriptografiaException {
        return criptografarComAlgoritmo(ALGORITMO, valor);
    } // fim do metodo criptografar

} // fim da classe CriptografiaSHA512