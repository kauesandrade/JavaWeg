package Biblioteca.Midias;

import Biblioteca.Midia;

public class Livro extends Midia {

    public Livro(int codigo, String nome) {
        super(codigo, nome);
    }
    @Override
    public String toString() {
        return "\nLivro: " +
                "\n- Código: " + codigo +
                "\n- Nome: " + nome +
                "\n- Emprestado: " + emprestado;
    }
}
