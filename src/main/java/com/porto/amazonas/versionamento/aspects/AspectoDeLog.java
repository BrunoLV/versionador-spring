package com.porto.amazonas.versionamento.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Aspecto responsavel pela funcionalidade de log dentro da aplica��o. 
 * @author BRUNO VIANA
 */
@Aspect
@Component
@Qualifier("aspectoDeLog")
public class AspectoDeLog {

	/**
	 * Pointcut para execu��o da funcionalidade de log. Apenas para as classes
	 * Service.
	 */
	@Pointcut("execution(* com.porto.amazonas.versionamento.service.*.*(..))")
	public void execucaoService() {
	} // fim do m�todo execução Service

	/**
	 * JoinPoint que ser� acionado antes da execu��o dos m�todos Service.
	 * @param joinPoint
	 */
	@Before("execucaoService()")
	public void logarAntesDaAcao(JoinPoint joinPoint) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		String nomeClasse = joinPoint.getSignature().getDeclaringTypeName();
		String nomeMetodo = joinPoint.getSignature().getName();
		Object[] argumentos = joinPoint.getArgs();
		logger.info("#---------------------------------------------------------------------------------------------------#");
		logger.info("Logando antes de executar o m�todo " + nomeMetodo + "() da classe " + nomeClasse);
		logger.info("Argumentos recebidos: ");
		for (Object argumento : argumentos) {
			logger.info("Tipo: " + argumento.getClass().getName());
		} // fim do bloco for
		logger.info("#---------------------------------------------------------------------------------------------------#");
	} // fim do m�todo logarAntesDaAcao

	/**
	 * JointPoint que ser� acionado ap�s a execu��o dos m�todos Service.
	 * @param joinPoint
	 */
	@After("execucaoService()")
	public void logarDepoisDaAcao(JoinPoint joinPoint) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		String nomeClasse = joinPoint.getSignature().getDeclaringTypeName();
		String nomeMetodo = joinPoint.getSignature().getName();
		logger.info("#---------------------------------------------------------------------------------------------------#");
		logger.info("Logando depois de executar o m�todo " + nomeMetodo + "() da classe " + nomeClasse);
		logger.info("#---------------------------------------------------------------------------------------------------#");
	} // fim do m�todo logarDepoisDaAcao

	/**
	 * Joint Point que ser� acionado ap�s o retorno dos métodos service.
	 * @param joinPoint
	 * @param retorno
	 */
	@AfterReturning(pointcut = "execucaoService()", returning = "retorno")
	public void logarDepoisDoRetornoDaAcao(JoinPoint joinPoint, Object retorno) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		String nomeClasse = joinPoint.getSignature().getDeclaringTypeName();
		String nomeMetodo = joinPoint.getSignature().getName();
		logger.info("#---------------------------------------------------------------------------------------------------#");
		logger.info("Logando depois do retorno da execu��o do m�todo " + nomeMetodo + "() da classe " + nomeClasse);
		logger.info("M�todo " + nomeClasse + "." + nomeMetodo + "() executado com sucesso.");
		if (retorno != null) {
			logger.info("Objeto retornado no m�todo: " + retorno.getClass().getName());
		} // fim do bloco if
		logger.info("#---------------------------------------------------------------------------------------------------#");
	} // fim do m�todo logarDepoisDoRetornoDaAcao

	/**
	 * Joint Point que ser� acionado quando o m�todo lan�ar uma exception
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(pointcut = "execucaoService()", throwing = "exception")
	public void logarDepoisDeUmDisparoDeException(JoinPoint joinPoint, Throwable exception) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		String nomeClasse = joinPoint.getSignature().getDeclaringTypeName();
		String nomeMetodo = joinPoint.getSignature().getName();
		logger.error("#---------------------------------------------------------------------------------------------------#");
		logger.error("Logando depois do disparo de um exception na execu��o do m�todo " + nomeMetodo + "() da classe " + nomeClasse);
		logger.error("M�todo " + nomeClasse + "." + nomeMetodo + "() executado com falha.");
		logger.error("Mensagem da exception: " + exception.getMessage());
		logger.error("#---------------------------------------------------------------------------------------------------#");
	} // fim do m�todo logarDepoisDeUmDisparoDeException

} // fim da classe AspectoDeLog
