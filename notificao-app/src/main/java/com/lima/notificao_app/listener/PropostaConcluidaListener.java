package com.lima.notificao_app.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lima.notificao_app.constante.MensagemConstante;
import com.lima.notificao_app.domain.Proposta;
import com.lima.notificao_app.service.NotificacaoSnsService;

@Component
public class PropostaConcluidaListener {

    @Autowired
    private NotificacaoSnsService notificacaoSnsService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        if (proposta.getAprovada()) {
            String mensagem = String.format(MensagemConstante.PROPOSTA_APROVADA, proposta.getUsuario().getNome());
            notificacaoSnsService.notificar(proposta.getUsuario().getTelefone(), mensagem);
        } else {
            String mensagem = String.format(MensagemConstante.PROPOSTA_NEGADA, proposta.getUsuario().getNome());
            notificacaoSnsService.notificar(proposta.getUsuario().getTelefone(), mensagem);
        }
    }
}
