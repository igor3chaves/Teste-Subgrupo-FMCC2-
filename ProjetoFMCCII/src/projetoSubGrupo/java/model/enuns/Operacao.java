package model.enuns;

/**
 * ENUM responsável por armazenar os tipos de operações permitidas.
 * Além disso, contém a lógica de cálculo de cada tipo de operação.
 */
public enum Operacao {
    ADICAO {
        public int operar (int a, int b, int modulo) {
            int resultado = a+b;
            return aplicarModulo(resultado, modulo);
        }
    },

    MULTIPLICACAO {
        public int operar (int a, int b, int modulo) {
            int resultado =  a*b;
            return aplicarModulo (resultado, modulo);
        }
    };

    private static int aplicarModulo (int resultado, int modulo) {
        return Math.floorMod(resultado, modulo);
    }

    public abstract int operar(int a, int b, int modulo);
}
