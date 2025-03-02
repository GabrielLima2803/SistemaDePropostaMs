package com.lima.analisecredito_app.service.strategy.impl;

import java.util.Random;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lima.analisecredito_app.constante.MensagemConstante;
import com.lima.analisecredito_app.domain.Proposta;
import com.lima.analisecredito_app.exceptions.StrategyException;
import com.lima.analisecredito_app.service.strategy.CalculoPonto;

@Order(2)
@Component
public class PontuacaoScoreImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        int score = score();
        if (score < 200) {
            throw new StrategyException(String.format(MensagemConstante.PONTUACAO_SERASA_BAIXA, proposta.getUsuario().getNome()));
        } else if (score <= 400) {
            return 150;
        } else if (score <= 600) {
            return 180;
        } else {
            return 200;
        }
    }

    private int score() {
        return new Random().nextInt(0, 1000);
    }

}
