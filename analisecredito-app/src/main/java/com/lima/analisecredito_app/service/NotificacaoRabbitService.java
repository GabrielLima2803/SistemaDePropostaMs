package com.lima.analisecredito_app.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.analisecredito_app.domain.Proposta;

@Service
public class NotificacaoRabbitService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void notificar(String exchange, Proposta proposta) {
        rabbitTemplate.convertAndSend(exchange,"" ,  proposta);
    }
}
