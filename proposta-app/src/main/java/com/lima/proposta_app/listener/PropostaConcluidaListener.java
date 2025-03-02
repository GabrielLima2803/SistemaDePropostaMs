package com.lima.proposta_app.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lima.proposta_app.dto.PropostaResponseDto;
import com.lima.proposta_app.entity.Proposta;
import com.lima.proposta_app.mapper.PropostaMapper;
import com.lima.proposta_app.repository.PropostaRepository;
import com.lima.proposta_app.service.WebSocketService;

@Component
public class PropostaConcluidaListener {
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private WebSocketService webSocketService;
    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConluida(Proposta proposta) {
        propostaRepository.save(proposta);
        PropostaResponseDto responseDto = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
        webSocketService.notificar(responseDto);
    }
}
