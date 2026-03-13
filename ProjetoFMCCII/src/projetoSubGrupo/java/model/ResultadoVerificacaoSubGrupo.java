package model;

/**
 * Classe que armazena os resultados da verificação de subGrupo.
 */
public class ResultadoVerificacaoSubGrupo {

    private final boolean naoVazio;
    private final boolean subConjunto;
    private final boolean fechamento;
    private final boolean elementoNeutro;
    private final boolean resultado;

    /**
     * Construtor da classe de resultados.
     * @param naoVazio
     * @param subConjunto
     * @param fechamento
     * @param elementoNeutro
     * @param resultado
     */
    public ResultadoVerificacaoSubGrupo(boolean naoVazio, boolean subConjunto, boolean fechamento, boolean elementoNeutro, boolean resultado) {

        this.naoVazio = naoVazio;
        this.subConjunto = subConjunto;
        this.fechamento = fechamento;
        this.elementoNeutro = elementoNeutro;
        this.resultado = resultado;
    }

    public boolean isNaoVazio() {
        return naoVazio;
    }
    public boolean isSubConjunto() {
        return subConjunto;
    }
    public boolean isFechamento() {
        return fechamento;
    }
    public boolean isElementoNeutro() {
        return elementoNeutro;
    }
    public boolean isResultado() {return resultado; }
}
