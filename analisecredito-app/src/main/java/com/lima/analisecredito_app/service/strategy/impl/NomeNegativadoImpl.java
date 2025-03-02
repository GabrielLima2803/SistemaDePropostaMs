package com.lima.analisecredito_app.service.strategy.impl;

import java.util.Random;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lima.analisecredito_app.constante.MensagemConstante;
import com.lima.analisecredito_app.domain.Proposta;
import com.lima.analisecredito_app.exceptions.StrategyException;
import com.lima.analisecredito_app.service.strategy.CalculoPonto;

@Order(1)
@Component
public class NomeNegativadoImpl implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        if (nomeNegativado()) {
            throw new StrategyException(String.format(MensagemConstante.CLIENTE_NEGATIVADO, proposta.getUsuario().getNome()));
        }
        return 100;
    }

    private boolean nomeNegativado(){
        return new Random().nextBoolean();
    }
}
