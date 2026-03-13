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
     * @param modulo
     * @return Um objeto ResultadoVerificacaoGrupo com as informações do resultado.
     */
    public static ResultadoVerificacaoGrupo verificarGrupo (Set<Integer> conjunto, Operacao operacao, Integer modulo) {

        if (conjunto.isEmpty()) {
            return new ResultadoVerificacaoGrupo(false, null);
        }

        if (!verificarFechamento (conjunto, operacao, modulo)) {
            return new ResultadoVerificacaoGrupo(false, null);
        }

        if (!verificarAssociatividade(conjunto, operacao, modulo)) {
            return new ResultadoVerificacaoGrupo(false, null);
        }

        Integer elementoNeutro = verificarElementoNeutro(conjunto, operacao, modulo);
        if (elementoNeutro == null) {
            return new ResultadoVerificacaoGrupo(false, null);
        }

        if (!verificarInverso(conjunto, operacao, elementoNeutro, modulo)) {
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
    private static boolean verificarFechamento (Set <Integer> conjunto, Operacao operacao, Integer modulo) {

        for (int a : conjunto) {
            for (int b : conjunto) {
                int resultado = operacao.operar(a, b, modulo);
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
    private static boolean verificarAssociatividade(Set <Integer> conjunto, Operacao operacao, Integer modulo) {

        Integer[] elementos = conjunto.toArray(new Integer[0]);
        int n = elementos.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    int a = elementos[i];
                    int b = elementos[j];
                    int c = elementos[k];

                    if (operacao.operar(operacao.operar(a, b, modulo), c, modulo) !=
                            operacao.operar(a, operacao.operar(b, c, modulo), modulo)) {
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
    private static Integer verificarElementoNeutro (Set <Integer> conjunto, Operacao operacao, Integer modulo) {

        for (int candidato: conjunto) {

            if (operacao.operar(candidato, candidato, modulo) == candidato) {

                boolean ehNeutro = true;

                for (int a: conjunto) {
                    if ((operacao.operar(candidato, a, modulo) != a) ||
                    (operacao.operar(a, candidato, modulo) != a)) {
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
    private static boolean verificarInverso (Set <Integer> conjunto, Operacao operacao, int elementoNeutro,
                                             Integer modulo) {

        for (int a: conjunto) {

            boolean temInverso = false;

            for (int b: conjunto) {

                if (operacao.operar(a,b, modulo) == elementoNeutro &&
                    operacao.operar(b,a, modulo) == elementoNeutro) {
                    temInverso = true;
                    break;
                }
            }

            if (!temInverso) return false;
        }

        return true;
    }
}