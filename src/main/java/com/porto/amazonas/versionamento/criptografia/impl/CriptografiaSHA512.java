package com.porto.amazonas.versionamento.criptografia.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.porto.amazonas.versionamento.criptografia.Criptografia;
import com.porto.amazonas.versionamento.exceptions.VersionamentoCriptografiaException;

/**
 * Componente de criptografia que utiliza o algoritmo SHA-512.
 * @author BRUNO VIANA
 */
@Component
@Qualifier("criptografiaSHA512")
public class CriptografiaSHA512 extends CriptografiaGenerica implements Criptografia {

	private static final String ALGORITMO = "SHA-512";
	
	public CriptografiaSHA512() {
	} // método construtor
	
	@Override
	public String criptografar(String valor) throws VersionamentoCriptografiaException {
		return criptografarComAlgoritmo(ALGORITMO, valor);
	} // fim dp

} // fim da classe CriptografiaSHA512