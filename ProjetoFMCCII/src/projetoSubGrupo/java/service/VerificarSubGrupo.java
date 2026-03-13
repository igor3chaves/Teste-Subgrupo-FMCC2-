package service;

import model.Grupo;
import model.ResultadoVerificacaoSubGrupo;
import model.enuns.Operacao;

import java.util.Set;

/**
 * Classe responsável por realizar a verificação dos critérios de subGrupo.
 * O grupo candidato tem que ser:
 * --> Não vazio
 * --> Todos os seus elementos devem estar conditos no grupo base
 * --> O grupo deve ser fechado para a operação do grupo base
 * --> O elemento neutro do grupo base deve estar contido no grupo candidato
 */
public class VerificarSubGrupo {

    /**
     * Método que centraliza os resultados dos critérios da verificação de subGrupo.
     * @param grupoBase
     * @param candidatoSubGrupo
     * @return Um objeto ResultadoVerificacaoSubGrupo com as informações do resultado.
     */
    public static ResultadoVerificacaoSubGrupo verificarSubGrupo (Grupo grupoBase, Grupo candidatoSubGrupo) {

        boolean naoVazio = verificarConjuntoSubGrupoNaoVazio (candidatoSubGrupo);

        boolean subConjunto = verificarSubConjunto (grupoBase, candidatoSubGrupo);

        boolean fechamento = verificarFechamento(grupoBase, candidatoSubGrupo, candidatoSubGrupo.getModulo());

        boolean elementoNeutro = verificarElementoNeutro(grupoBase, candidatoSubGrupo);


        boolean resultado = naoVazio && subConjunto && fechamento && elementoNeutro;

        return new ResultadoVerificacaoSubGrupo(naoVazio, subConjunto, fechamento, elementoNeutro, resultado);

    }

    /**
     * Verifica se o grupo candidato é não vazio.
     * @param candidatoSubGrupo
     * @return true (Se for não vazio) | false (Se for vazio)
     */
    private static boolean verificarConjuntoSubGrupoNaoVazio (Grupo candidatoSubGrupo) {
        Set <Integer> conjuntoSubGrupo = candidatoSubGrupo.getConjunto();
        return (!conjuntoSubGrupo.isEmpty());
    }

    /**
     * Verifica se todos os elementos do grupo candidato estão contidos no grupo base.
     * @param candidatoSubGrupo
     * @return true (Se todos os elementos estiverem conditos) | false (Caso contrário)
     */
    private static boolean verificarSubConjunto (Grupo grupoBase, Grupo candidatoSubGrupo) {

        Set <Integer> conjuntoBase = grupoBase.getConjunto();
        Set <Integer> conjuntoCandidatoSubGrupo = candidatoSubGrupo.getConjunto();

        return conjuntoBase.containsAll(conjuntoCandidatoSubGrupo);
    }

    /**
     * Verifica se o conjunto candidato é fechado para a operação do grupo base.
     * @param grupoBase
     * @param candidatoSubGrupo
     * @return
     */
    private static boolean verificarFechamento (Grupo grupoBase, Grupo candidatoSubGrupo, Integer modulo) {

        Set <Integer> conjuntoCandidatoSubGrupo = candidatoSubGrupo.getConjunto();
        Operacao operacaoGrupoBase = grupoBase.getOperacao();

        for (int a: conjuntoCandidatoSubGrupo) {
            for (int b: conjuntoCandidatoSubGrupo) {

                int resultado = operacaoGrupoBase.operar(a,b, modulo);

                if (!conjuntoCandidatoSubGrupo.contains(resultado)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifica se o elemento neutro do conjunto base está condito no grupo candidato.
     * @param grupoBase
     * @param candidatoSubGrupo
     * @return true (Se o elemento neutro estiver contido) | false (Caso contrário)
     */
    private static boolean verificarElementoNeutro (Grupo grupoBase, Grupo candidatoSubGrupo) {

        Integer elementoNeutroGrupoBase = grupoBase.getElementNeutro();
        Set <Integer> conjuntoCandidatoSubGrupo = candidatoSubGrupo.getConjunto();

        return conjuntoCandidatoSubGrupo.contains(elementoNeutroGrupoBase);
    }
}
