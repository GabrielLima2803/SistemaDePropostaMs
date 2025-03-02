package com.lima.analisecredito_app.service.strategy.impl;

import org.springframework.stereotype.Component;

import com.lima.analisecredito_app.domain.Proposta;
import com.lima.analisecredito_app.service.strategy.CalculoPonto;

@Component
public class RendaMaiorValorSolicitadoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return rendaMaiorValorSolicitado(proposta) ? 100 : 0;
    }

    private boolean rendaMaiorValorSolicitado(Proposta proposta) {
       return proposta.getUsuario().getRenda() > proposta.getValorSolicitado();
    }

}
