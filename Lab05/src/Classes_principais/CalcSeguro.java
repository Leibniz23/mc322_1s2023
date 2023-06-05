package Classes_principais;

public enum CalcSeguro {
    VALOR_BASE(10.0),
    FATOR_ATE_30(1.25),
    FATOR_30_60(1.0),
    FATOR_ACIMA_60(1.5);

    private final double fator;

    /* Construtor */
    CalcSeguro(double fator) {
        this.fator = fator;
    }

    /* Getters e Setters */
    public double getFator() {
        return this.fator;
    }
}