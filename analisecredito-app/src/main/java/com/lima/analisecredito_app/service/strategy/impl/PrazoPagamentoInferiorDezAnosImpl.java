package com.lima.analisecredito_app.service.strategy.impl;

import org.springframework.stereotype.Component;

import com.lima.analisecredito_app.domain.Proposta;
import com.lima.analisecredito_app.service.strategy.CalculoPonto;

@Component
public class PrazoPagamentoInferiorDezAnosImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return proposta.getPrazoPagamento() < 120 ? 80 : 0;
    }

}
