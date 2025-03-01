package com.lima.proposta_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lima.proposta_app.dto.PropostaRequestDto;
import com.lima.proposta_app.dto.PropostaResponseDto;
import com.lima.proposta_app.entity.Proposta;
import com.lima.proposta_app.mapper.PropostaMapper;
import com.lima.proposta_app.repository.PropostaRepository;


@Service
public class PropostaService {
    private PropostaRepository propostaRepository;
    private NotificacaoRabbitService notificacaoService;

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;

    public PropostaService(PropostaRepository propostaRepository, NotificacaoRabbitService notificacaoService, @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }
    public PropostaResponseDto criarProposta(PropostaRequestDto requestDto) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(proposta);
        notificarRabbitMq(proposta);
       return PropostaMapper.INSTANCE.convertEntityToDto(proposta);        
    }
    private void notificarRabbitMq(Proposta proposta) {
        try {
            notificacaoService.notificar(proposta, exchange);
        } catch (RuntimeException e) {
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }
    public List<PropostaResponseDto> obterProposta() {
        Iterable<Proposta> propostas = propostaRepository.findAll(); 
        return PropostaMapper.INSTANCE.convertListEntityToDto(propostas);
    }

}
