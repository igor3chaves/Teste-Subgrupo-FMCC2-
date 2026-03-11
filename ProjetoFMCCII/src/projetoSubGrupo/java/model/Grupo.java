package model;

import model.enuns.Operacao;
import service.VerificarGrupo;

import java.util.HashSet;

public class Grupo {

    HashSet<Integer> conjunto;
    Operacao operacao;
    int elementoIdentidade;

    public Grupo (HashSet <Integer> conjunto, Operacao operacao) {

        ResultadoVerificacaoGrupo resultado = VerificarGrupo.verificarGrupo(conjunto, operacao);

        if (!resultado.isResultado()) {
            throw new IllegalArgumentException ("Os conjunto com essa operação não forma um grupo");
        }

        this.conjunto = conjunto;
        this.operacao = operacao;
        this.elementoIdentidade = resultado.getElementoNeutro();
    }

    public HashSet<Integer> getConjunto() {
        return conjunto;
    }

    public int getElementoIdentidade() {
        return elementoIdentidade;
    }

    public Operacao getOperacao() {
        return operacao;
    }
}
