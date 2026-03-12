package model;

import model.enuns.Operacao;
import service.VerificarGrupo;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe que representa um objeto de grupo (Conjunto de números + Operação)
 */
public class Grupo {

    private Set<Integer> conjunto;
    private Operacao operacao;
    private Integer elementoNeutro;

    /**
     * Classe responsável pela criação do objeto
     * Faz a chamada do Servece responsável por verificar se esse conjunto de números
     * E essa operação formam um grupo.
     * @param conjunto
     * @param operacao
     */
    public Grupo (Set<Integer> conjunto, Operacao operacao) {

        ResultadoVerificacaoGrupo resultado = VerificarGrupo.verificarGrupo(conjunto, operacao);

        if (!resultado.isResultado()) {
            throw new IllegalArgumentException ("O conjunto de números com essa operação não formamm um grupo");
        }

        this.conjunto = conjunto;
        this.operacao = operacao;
        this.elementoNeutro = resultado.getElementoNeutro();
    }

    public Set<Integer> getConjunto() {
        return conjunto;
    }

    public Integer getElementNeutro() {
        return elementoNeutro;
    }

    public Operacao getOperacao() {
        return operacao;
    }
}
