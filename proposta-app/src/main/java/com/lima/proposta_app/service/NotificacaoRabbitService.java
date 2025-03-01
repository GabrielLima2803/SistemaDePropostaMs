package com.lima.proposta_app.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.lima.proposta_app.entity.Proposta;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NotificacaoRabbitService {
    private RabbitTemplate rabbitTemplate;

    public void notificar(Proposta proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }
}
