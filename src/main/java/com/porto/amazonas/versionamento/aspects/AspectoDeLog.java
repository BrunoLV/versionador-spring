package com.porto.amazonas.versionamento.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Aspecto responsavel pela funcionalidade de log dentro da aplicacao.
 *
 * @author BRUNO VIANA
 */
@Aspect
@Component
@Qualifier("aspectoDeLog")
public class AspectoDeLog {

    /**
     * Pointcut para execucao da funcionalidade de log. Apenas para as classes
     * Service.
     */
    @Pointcut("execution(* com.porto.amazonas.versionamento.service.*.*(..))")
    public void execucaoService() {
    } // fim do metodo execução Service

    /**
     * JoinPoint que sere acionado antes da execucao dos metodos Service.
     *
     * @param joinPoint
     */
    @Before("execucaoService()")
    public void logarAntesDaAcao(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        String nomeClasse = joinPoint.getSignature().getDeclaringTypeName();
        String nomeMetodo = joinPoint.getSignature().getName();
        Object[] argumentos = joinPoint.getArgs();
        logger.info("#---------------------------------------------------------------------------------------------------#");
        logger.info("Logando antes de executar o metodo " + nomeMetodo + "() da classe " + nomeClasse);
        logger.info("Argumentos recebidos: ");
        for (Object argumento : argumentos) {
            logger.info("Tipo: " + argumento.getClass().getName());
        } // fim do bloco for
        logger.info("#---------------------------------------------------------------------------------------------------#");
    } // fim do metodo logarAntesDaAcao

    /**
     * JointPoint que sere acionado apes a execucao dos metodos Service.
     *
     * @param joinPoint
     */
    @After("execucaoService()")
    public void logarDepoisDaAcao(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        String nomeClasse = joinPoint.getSignature().getDeclaringTypeName();
        String nomeMetodo = joinPoint.getSignature().getName();
        logger.info("#---------------------------------------------------------------------------------------------------#");
        logger.info("Logando depois de executar o metodo " + nomeMetodo + "() da classe " + nomeClasse);
        logger.info("#---------------------------------------------------------------------------------------------------#");
    } // fim do metodo logarDepoisDaAcao

    /**
     * Joint Point que sere acionado apes o retorno dos métodos service.
     *
     * @param joinPoint
     * @param retorno
     */
    @AfterReturning(pointcut = "execucaoService()", returning = "retorno")
    public void logarDepoisDoRetornoDaAcao(JoinPoint joinPoint, Object retorno) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        String nomeClasse = joinPoint.getSignature().getDeclaringTypeName();
        String nomeMetodo = joinPoint.getSignature().getName();
        logger.info("#---------------------------------------------------------------------------------------------------#");
        logger.info("Logando depois do retorno da execucao do metodo " + nomeMetodo + "() da classe " + nomeClasse);
        logger.info("Metodo " + nomeClasse + "." + nomeMetodo + "() executado com sucesso.");
        if (retorno != null) {
            logger.info("Objeto retornado no metodo: " + retorno.getClass().getName());
        } // fim do bloco if
        logger.info("#---------------------------------------------------------------------------------------------------#");
    } // fim do metodo logarDepoisDoRetornoDaAcao

    /**
     * Joint Point que sere acionado quando o metodo lanear uma exception
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(pointcut = "execucaoService()", throwing = "exception")
    public void logarDepoisDeUmDisparoDeException(JoinPoint joinPoint, Throwable exception) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        String nomeClasse = joinPoint.getSignature().getDeclaringTypeName();
        String nomeMetodo = joinPoint.getSignature().getName();
        logger.error("#---------------------------------------------------------------------------------------------------#");
        logger.error("Logando depois do disparo de um exception na execucao do metodo " + nomeMetodo + "() da classe " + nomeClasse);
        logger.error("Metodo " + nomeClasse + "." + nomeMetodo + "() executado com falha.");
        logger.error("Mensagem da exception: " + exception.getMessage());
        logger.error("#---------------------------------------------------------------------------------------------------#");
    } // fim do metodo logarDepoisDeUmDisparoDeException

} // fim da classe AspectoDeLog
