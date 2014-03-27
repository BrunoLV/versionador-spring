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
 * Aspecto responsavel pela funcionalidade de log dentro da aplicação. 
 * @author BRUNO VIANA
 */
@Aspect
@Component
@Qualifier("aspectoDeLog")
public class AspectoDeLog {

	/**
	 * Pointcut para execução da funcionalidade de log. Apenas para as classes
	 * Service.
	 */
	@Pointcut("execution(* com.porto.amazonas.versionamento.service.*.*(..))")
	public void execucaoService() {
	} // fim do método execuÃ§Ã£o Service

	/**
	 * JoinPoint que será acionado antes da execução dos métodos Service.
	 * @param joinPoint
	 */
	@Before("execucaoService()")
	public void logarAntesDaAcao(JoinPoint joinPoint) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		String nomeClasse = joinPoint.getSignature().getDeclaringTypeName();
		String nomeMetodo = joinPoint.getSignature().getName();
		Object[] argumentos = joinPoint.getArgs();
		logger.info("#---------------------------------------------------------------------------------------------------#");
		logger.info("Logando antes de executar o método " + nomeMetodo + "() da classe " + nomeClasse);
		logger.info("Argumentos recebidos: ");
		for (Object argumento : argumentos) {
			logger.info("Tipo: " + argumento.getClass().getName());
		} // fim do bloco for
		logger.info("#---------------------------------------------------------------------------------------------------#");
	} // fim do método logarAntesDaAcao

	/**
	 * JointPoint que será acionado após a execução dos métodos Service.
	 * @param joinPoint
	 */
	@After("execucaoService()")
	public void logarDepoisDaAcao(JoinPoint joinPoint) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		String nomeClasse = joinPoint.getSignature().getDeclaringTypeName();
		String nomeMetodo = joinPoint.getSignature().getName();
		logger.info("#---------------------------------------------------------------------------------------------------#");
		logger.info("Logando depois de executar o método " + nomeMetodo + "() da classe " + nomeClasse);
		logger.info("#---------------------------------------------------------------------------------------------------#");
	} // fim do método logarDepoisDaAcao

	/**
	 * Joint Point que será acionado após o retorno dos mÃ©todos service.
	 * @param joinPoint
	 * @param retorno
	 */
	@AfterReturning(pointcut = "execucaoService()", returning = "retorno")
	public void logarDepoisDoRetornoDaAcao(JoinPoint joinPoint, Object retorno) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		String nomeClasse = joinPoint.getSignature().getDeclaringTypeName();
		String nomeMetodo = joinPoint.getSignature().getName();
		logger.info("#---------------------------------------------------------------------------------------------------#");
		logger.info("Logando depois do retorno da execução do método " + nomeMetodo + "() da classe " + nomeClasse);
		logger.info("Método " + nomeClasse + "." + nomeMetodo + "() executado com sucesso.");
		if (retorno != null) {
			logger.info("Objeto retornado no método: " + retorno.getClass().getName());
		} // fim do bloco if
		logger.info("#---------------------------------------------------------------------------------------------------#");
	} // fim do método logarDepoisDoRetornoDaAcao

	/**
	 * Joint Point que será acionado quando o método lançar uma exception
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(pointcut = "execucaoService()", throwing = "exception")
	public void logarDepoisDeUmDisparoDeException(JoinPoint joinPoint, Throwable exception) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		String nomeClasse = joinPoint.getSignature().getDeclaringTypeName();
		String nomeMetodo = joinPoint.getSignature().getName();
		logger.error("#---------------------------------------------------------------------------------------------------#");
		logger.error("Logando depois do disparo de um exception na execução do método " + nomeMetodo + "() da classe " + nomeClasse);
		logger.error("Método " + nomeClasse + "." + nomeMetodo + "() executado com falha.");
		logger.error("Mensagem da exception: " + exception.getMessage());
		logger.error("#---------------------------------------------------------------------------------------------------#");
	} // fim do método logarDepoisDeUmDisparoDeException

} // fim da classe AspectoDeLog
