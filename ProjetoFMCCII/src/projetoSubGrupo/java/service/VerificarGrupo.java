package service;

import model.ResultadoVerificacaoGrupo;
import model.enuns.Operacao;

import java.util.Set;

/**
 * Classe responsável por realizar a verificação dos critérios de grupo.
 * Assim, ela avalia se o conjunto + operação forma um grupo.
 */

public class VerificarGrupo {

    /**
     * Método que centraliza os resultados dos critérios de grupo.
     * @param conjunto
     * @param operacao
     * @return Um objeto ResultadoVerificacaoGrupo com as informações do resultado.
     */
    public static ResultadoVerificacaoGrupo verificarGrupo (Set<Integer> conjunto, Operacao operacao) {

        if (conjunto.isEmpty()) {
            return new ResultadoVerificacaoGrupo(false, null);
        }

        if (!verificarFechamento (conjunto, operacao)) {
            return new ResultadoVerificacaoGrupo(false, null);
        }

        if (!verificarAssociatividade(conjunto, operacao)) {
            return new ResultadoVerificacaoGrupo(false, null);
        }

        Integer elementoNeutro = verificarElementoNeutro(conjunto, operacao);
        if (elementoNeutro == null) {
            return new ResultadoVerificacaoGrupo(false, null);
        }

        if (!verificarInverso(conjunto, operacao, elementoNeutro)) {
            return new ResultadoVerificacaoGrupo(false, null);
        }

        return new ResultadoVerificacaoGrupo(true, elementoNeutro);
    }


    /**
     * Método para verificar se um conjunto é fechado ou não.
     * @param conjunto
     * @param operacao
     * @return true (Se for fechado) | False (Se não for fechado)
     */
    private static boolean verificarFechamento (Set <Integer> conjunto, Operacao operacao) {

        for (int a : conjunto) {
            for (int b : conjunto) {
                int resultado = operacao.operar(a, b);
                if (!conjunto.contains(resultado)) return false;
            }
        }
        return true;
    }

    /**
     * Método para verificar se o conjunto com a operação é associativo ou não.
     * @param conjunto
     * @param operacao
     * @return true (Se for associativo) | false (Se não for associativo).
     */
    private static boolean verificarAssociatividade(Set <Integer> conjunto, Operacao operacao) {

        Integer[] elementos = conjunto.toArray(new Integer[0]);
        int n = elementos.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    int a = elementos[i];
                    int b = elementos[j];
                    int c = elementos[k];

                    if (operacao.operar(operacao.operar(a, b), c) !=
                            operacao.operar(a, operacao.operar(b, c))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Método que avalia se o conjunto tem um elemento neutro
     * Elemento neutro: Todo a pertencente ao conjunto deve ter um e tal que a (operacão) e = e (operação) a = a
     * @param conjunto
     * @param operacao
     * @return true (Se tiver elemento neutro) | False (Se não tiver elemento neutro)
     */
    private static Integer verificarElementoNeutro (Set <Integer> conjunto, Operacao operacao) {

        for (int candidato: conjunto) {

            if (operacao.operar(candidato, candidato) == candidato) {

                boolean ehNeutro = true;

                for (int a: conjunto) {
                    if ((operacao.operar(candidato, a) != a) ||
                    (operacao.operar(a, candidato) != a)) {
                        ehNeutro = false;
                        break;
                    }
                }
                if (ehNeutro) return candidato;
            }
        }

        return null;
    }

    /**
     * Método que avalia se cada elemento do conjunto + operação tem um inverso no conjunto.
     * Inverso: Todo a pertencente ao conjunto deve ter um x tal que a (operacão) x = x (operação) a = e
     * @param conjunto
     * @param operacao
     * @param elementoNeutro
     * @return true (Se cada elemento tiver um inverso) | false (Se pelo menos um elemento não tiver inverso).
     */
    private static boolean verificarInverso (Set <Integer> conjunto, Operacao operacao, int elementoNeutro) {

        for (int a: conjunto) {

            boolean temInverso = false;

            for (int b: conjunto) {

                if (operacao.operar(a,b) == elementoNeutro &&
                    operacao.operar(b,a) == elementoNeutro) {
                    temInverso = true;
                    break;
                }
            }

            if (!temInverso) return false;
        }

        return true;
    }
}