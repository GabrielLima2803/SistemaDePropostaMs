package com.lima.analisecredito_app.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.lima.analisecredito_app.domain.Proposta;
import com.lima.analisecredito_app.service.AnaliseCreditoService;

@Component
public class PropostaEmAnaliseListener {

    private AnaliseCreditoService analiseCreditoService;

    public PropostaEmAnaliseListener(AnaliseCreditoService analiseCreditoService) {
        this.analiseCreditoService = analiseCreditoService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaEEmAnalise(Proposta proposta) {
        analiseCreditoService.analisar(proposta);
    }
}
