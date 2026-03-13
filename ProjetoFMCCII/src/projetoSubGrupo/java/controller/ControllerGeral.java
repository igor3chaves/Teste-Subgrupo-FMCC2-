package controller;

import model.Grupo;
import model.ResultadoVerificacaoSubGrupo;
import model.enuns.Operacao;
import service.VerificarSubGrupo;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;

public class ControllerGeral {

    private static String normalizarString(String texto) {
        if (texto == null) return null;

        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    }

    private Grupo criarGrupo (String numeros, String operacao, Integer modulo) {

        if (numeros == null || numeros.isBlank()) {
            throw new IllegalArgumentException("Conjunto de números vazio.");
        }
        if (operacao == null || operacao.isBlank()) {
            throw new IllegalArgumentException("Operação vazia.");
        }
        if (modulo == null) {
            throw new IllegalArgumentException("Modulo vazio.");
        }

        Set<Integer> conjunto = new HashSet<>();
        Operacao operacaoO;

        String[] tokens = numeros.trim().split("[^0-9-]+");

        for (String token: tokens) {

            if (token.isBlank()) continue;

            try {
                conjunto.add(Integer.parseInt(token.trim()));
            }
            catch (NumberFormatException e) {
                System.err.println(String.format("Elemento inválido (%s) identificado e ignorado.", token));
            }
        }

        if (conjunto.isEmpty()) {
            throw new IllegalArgumentException("Nenhum número válido identificado.");
        }

        try {
            operacaoO = Operacao.valueOf(normalizarString(operacao).toUpperCase());
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException ("Tipo de operação inválida. \nOperações possíveis = [ADIÇÃO / MULTIPLICAÇÃO]");
        }

        return  new Grupo(conjunto, operacaoO, modulo);
    }

    private ResultadoVerificacaoSubGrupo verificarSubGrupo (Grupo grupoBase, Grupo candidatoSubGrupo) {

        if (grupoBase == null) {
            throw new IllegalArgumentException("Grupo base inválido.");
        }
        if (candidatoSubGrupo == null) {
            throw new IllegalArgumentException("Candidato a sub grupo inválido.");
        }

         return VerificarSubGrupo.verificarSubGrupo(grupoBase, candidatoSubGrupo);
    }

    private String gerarRelatorio(ResultadoVerificacaoSubGrupo resultado) {

        StringBuilder sb = new StringBuilder();

        sb.append(resultado.isNaoVazio()
                ? "O candidato não é vazio ✅\n"
                : "O candidato é vazio ❌\n");

        sb.append(resultado.isSubConjunto()
                ? "O candidato é um subconjunto do grupo base ✅\n"
                : "O candidato não é um subconjunto do grupo base ❌\n");

        sb.append(resultado.isFechamento()
                ? "O candidato é fechado para a operação do grupo base ✅\n"
                : "O candidato não é fechado para a operação do grupo base ❌\n");

        sb.append(resultado.isElementoNeutro()
                ? "O elemento neutro está contido no grupo candidato ✅\n"
                : "O elemento neutro não está contido no grupo candidato ❌\n");

        sb.append(resultado.isResultado()
                ? "Logo, o conjunto candidato É um subgrupo do grupo base ✅\n"
                : "Logo, o conjunto candidato NÃO é um subgrupo do grupo base ❌\n");

        return sb.toString();
    }

    public String resultadoSubGrupo (String numerosGrupoBase, String operacaoGrupoBase, Integer moduloGrupoBase,
                                     String numerosCandidatoSubGrupo, String operacaoCandidatoSubGrupo,
                                     Integer moduloCandidatoSubGrupo) {

        Grupo grupoBase = criarGrupo(numerosGrupoBase, operacaoGrupoBase, moduloGrupoBase);
        Grupo candidatoSubGrupo = criarGrupo(numerosCandidatoSubGrupo, operacaoCandidatoSubGrupo, moduloCandidatoSubGrupo);

        ResultadoVerificacaoSubGrupo resultado = this.verificarSubGrupo(grupoBase, candidatoSubGrupo);

        return gerarRelatorio(resultado);
    }
}
