package com.lima.proposta_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.lima.proposta_app.dto.PropostaResponseDto;

@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate template;
    public void notificar(PropostaResponseDto proposta){
        template.convertAndSend("/propostas", proposta);

    }
}
