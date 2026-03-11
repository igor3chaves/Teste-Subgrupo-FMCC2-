package model;

/**
 * Classe que armazena os resultados da verificação de Grupo.
 */
public class ResultadoVerificacaoGrupo {

    private final boolean resultado;
    private final Integer elementoNeutro;

    /**
     * Construtor da classe de resultados.
     * @param resultado
     * @param elementoNeutro
     */
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
