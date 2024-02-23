package Biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Midia {

    private static final ArrayList<Midia> midias = new ArrayList<>();

    protected boolean emprestado;
    protected int codigo;
    protected String nome;

    public Midia(int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    public static Midia procurarMidia(int codigo) {

        for (Midia midia:midias) {
            if (midia.codigo == codigo) {
                return midia;
            }
        }
        return null;

    }

    public static List<Midia> getMidias() {
        return Collections.unmodifiableList(midias);
    }

    public static void addMidia(Midia midia){
        midias.add(midia);
    }

    public static void removeMidia(int codigo){
        for (Midia midiaRemove : midias){
            if(midiaRemove.codigo == codigo){
                midias.remove(midiaRemove);
                return;
            }
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void alterarEmprestimo () {
        this.emprestado = !this.emprestado;
    }
}
