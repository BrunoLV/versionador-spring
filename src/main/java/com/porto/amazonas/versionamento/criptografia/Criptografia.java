package com.porto.amazonas.versionamento.criptografia;

import com.porto.amazonas.versionamento.exceptions.VersionamentoCriptografiaException;

/**
 * Interface que determina o comportamento dos componentes de criptografia.
 *
 * @author BRUNO VIANA
 */
public interface Criptografia {

    String criptografar(String value) throws VersionamentoCriptografiaException;

} // fim da interface Criptografia
