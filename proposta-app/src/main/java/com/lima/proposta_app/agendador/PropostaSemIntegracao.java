package com.lima.proposta_app.agendador;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lima.proposta_app.entity.Proposta;
import com.lima.proposta_app.repository.PropostaRepository;
import com.lima.proposta_app.service.NotificacaoRabbitService;
@Component
public class PropostaSemIntegracao {
    private final PropostaRepository propostaRepository;
    private final NotificacaoRabbitService notificacaoRabbitService; 
    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(PropostaSemIntegracao.class);

    public PropostaSemIntegracao(PropostaRepository propostaRepository, NotificacaoRabbitService notificacaoRabbitService,  @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchange = exchange;
    }
    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostaSemIntegracao() {
        propostaRepository.findAllByIntegradaFalse().forEach(proposta -> {
            try {
                notificacaoRabbitService.notificar(proposta, exchange);
                atualizarProposta(proposta);
            } catch (RuntimeException ex) {
                logger.error("Erro ao notificar proposta: {}", proposta.getId(), ex);}
        });
    }

    public void atualizarProposta(Proposta proposta) {
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
    }
}
