package com.porto.amazonas.versionamento.criptografia.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

import com.porto.amazonas.versionamento.exceptions.VersionamentoCriptografiaException;

/**
 * Classe gen�rica de criptografia.
 * @author BRUNO VIANA
 */
public abstract class CriptografiaGenerica {
	
	private MessageDigest messageDigest;
	private Base64 encoder;
	
	/**
	 * M�todo utilizado para definir o algoritmo de criptografia.
	 * @param algoritmo de criptografia que ser� utilizado.
	 * @throws VersionamentoCriptografiaException
	 */
	protected void usarAlgoritmo(String algoritmo) throws VersionamentoCriptografiaException {
		if (messageDigest == null || messageDigest.getAlgorithm() != algoritmo) {
			try {
				messageDigest = MessageDigest.getInstance(algoritmo);
			} catch (NoSuchAlgorithmException e) {
				throw new VersionamentoCriptografiaException(e.getMessage(), e.getCause());
			} // fim do bloco try/catch
		} // fim do bloco if
		if (encoder == null) {
			encoder = new Base64();
		} // fim od bloco if
	} // fim do m�todo usarAlgoritmo
	
	protected String criptografarComAlgoritmo(String algoritmo, String valor) throws VersionamentoCriptografiaException {
		if (valor == null) {
			throw new IllegalArgumentException("O valor � nulo");
		} // fim do bloco if
		usarAlgoritmo(algoritmo);
		byte[] hash = messageDigest.digest(valor.getBytes());
		return encoder.encodeToString(hash);
	} // fim do m�todo criptografarComAlgoritmo

} // fim da classe CriptografiaGenerica
