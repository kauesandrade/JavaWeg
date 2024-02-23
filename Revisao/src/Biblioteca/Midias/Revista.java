package Biblioteca.Midias;

import Biblioteca.Midia;

public class Revista extends Midia {

    public Revista(int codigo, String nome) {
        super(codigo, nome);
    }
    @Override
    public String toString() {
        return "\nRevista: " +
                "\n- Código: " + codigo +
                "\n- Nome: " + nome +
                "\n- Emprestado: " + emprestado;
    }
}
