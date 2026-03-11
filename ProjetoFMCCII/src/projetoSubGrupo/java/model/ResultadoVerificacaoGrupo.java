package model;

public class ResultadoVerificacaoGrupo {

    private final boolean resultado;
    private final Integer elementoNeutro;

    public ResultadoVerificacaoGrupo (boolean resultado, Integer elementoNeutro) {
        this.resultado = resultado;
        this.elementoNeutro = elementoNeutro;
    }


    public boolean isResultado() {
        return resultado;
    }
    public Integer getElementoNeutro() {
        return elementoNeutro;
    }
}
