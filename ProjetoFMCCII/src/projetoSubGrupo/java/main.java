import controller.ControllerGeral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {

    public static void main (String[] args) throws IOException {

        ControllerGeral CG = new ControllerGeral();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Digite o conjunto de números do grupo base:");
        String numerosGrupoBase = br.readLine();
        System.out.println("Digite a operação do grupo base:");
        String operacaoGrupoBase = br.readLine();
        System.out.println("Digite o modulo do grupo base:");
        Integer moduloGrupoBase = Integer.parseInt(br.readLine());

        System.out.println("Digite o conjunto de números do candidato a sub grupo:");
        String numerosCandidadoSubGrupo = br.readLine();
        System.out.println("Digite a operação do candidato a sub grupo:");
        String operacaoCandidatoSubGrupo = br.readLine();
        System.out.println("Digite o modulo do candidato a sub grupo:");
        Integer moduloCandidatoSubGrupo = Integer.parseInt(br.readLine());

        System.out.print(CG.resultadoSubGrupo(numerosGrupoBase, operacaoGrupoBase, moduloGrupoBase,
                numerosCandidadoSubGrupo, operacaoCandidatoSubGrupo, moduloCandidatoSubGrupo));

    }

}
