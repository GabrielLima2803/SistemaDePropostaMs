package com.lima.analisecredito_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lima.analisecredito_app.domain.Proposta;
import com.lima.analisecredito_app.exceptions.StrategyException;
import com.lima.analisecredito_app.service.strategy.CalculoPonto;

@Service
public class AnaliseCreditoService {

    @Autowired
    private List<CalculoPonto> calculoPontosList;

    @Autowired
    private NotificacaoRabbitService notificacaoRabbitService;

    @Value("${rabbitmq.exchange.proposta.concluida}")
    private String exchangePropostaConcluida;


    public void analisar(Proposta proposta){
        try {
            int pontos = calculoPontosList.stream()
            .mapToInt(impl -> impl.calcular(proposta)).sum();
            proposta.setAprovada(pontos > 350);
        } catch (StrategyException e) {
            proposta.setAprovada(false);
            proposta.setObservacao(e.getMessage());
        }
        notificacaoRabbitService.notificar(exchangePropostaConcluida, proposta);
    }
}
