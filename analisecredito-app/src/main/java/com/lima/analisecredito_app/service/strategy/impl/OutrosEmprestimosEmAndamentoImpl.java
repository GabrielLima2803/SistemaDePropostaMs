package com.lima.analisecredito_app.service.strategy.impl;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.lima.analisecredito_app.domain.Proposta;
import com.lima.analisecredito_app.service.strategy.CalculoPonto;

@Component
public class OutrosEmprestimosEmAndamentoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return outrosEmprestimosEmAndamento() ? 0 : 80;
    }

    private boolean outrosEmprestimosEmAndamento() {
        return new Random().nextBoolean();
    }

}
