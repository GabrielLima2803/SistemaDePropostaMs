package com.lima.notificao_app.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lima.notificao_app.constante.MensagemConstante;
import com.lima.notificao_app.domain.Proposta;
import com.lima.notificao_app.service.NotificacaoSnsService;

@Component
public class PropostaPendenteListener {

    private NotificacaoSnsService notificacaoSnsService;


    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta){
        String mensagem = String.format(MensagemConstante.PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
        notificacaoSnsService.notificar(proposta.getUsuario().getTelefone(), mensagem);
    }
}
