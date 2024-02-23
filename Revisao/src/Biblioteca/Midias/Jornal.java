package Biblioteca.Midias;

import Biblioteca.Midia;

public class Jornal extends Midia {

    public Jornal(int codigo, String nome) {
        super(codigo, nome);
    }
    @Override
    public String toString() {
        return "\nJornal: " +
                "\n- Código: " + codigo +
                "\n- Nome: " + nome +
                "\n- Emprestado: " + emprestado;
    }
}
