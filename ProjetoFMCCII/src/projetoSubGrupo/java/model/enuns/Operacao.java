package model.enuns;

/**
 * ENUM responsável por armazenar os tipos de operações permitidas.
 * Além disso, contém a lógica de cálculo de cada tipo de operação.
 */
public enum Operacao {
    ADICAO {
        public int operar (int a, int b) {
            return a + b;
        }
    },

    MULTIPLICACAO {
        public int operar (int a, int b) {
            return a*b;
        }
    };

    public abstract int operar(int a, int b);
}
