package model.enuns;

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
