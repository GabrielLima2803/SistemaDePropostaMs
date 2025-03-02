package com.lima.analisecredito_app.service.strategy;

import com.lima.analisecredito_app.domain.Proposta;

public interface CalculoPonto {
    int calcular(Proposta proposta);
}
