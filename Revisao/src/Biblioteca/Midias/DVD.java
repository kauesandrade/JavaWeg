package Biblioteca.Midias;

import Biblioteca.Midia;

public class DVD extends Midia {

    public DVD(int codigo, String nome) {
        super(codigo, nome);
    }
    @Override
    public String toString() {
        return "\nDVD: " +
                "\n- Código: " + codigo +
                "\n- Nome: " + nome +
                "\n- Emprestado: " + emprestado;
    }
}
