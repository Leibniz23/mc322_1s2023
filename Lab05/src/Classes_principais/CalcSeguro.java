package Classes_principais;

public enum CalcSeguro {
    VALOR_BASE(100.0),
    FATOR_ATE_30(1.25),
    FATOR_30_60(1.0),
    FATOR_ACIMA_60(1.5);

    private final double fator;

    CalcSeguro(double fator) {
        this.fator = fator;
    }

    public double getFator() {
        return this.fator;
    }
}