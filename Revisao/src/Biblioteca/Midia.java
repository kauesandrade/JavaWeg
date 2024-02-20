package Biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Midia {

    private boolean emprestado;

    private static final ArrayList<Midia> midias = new ArrayList<>();

    private int codigo;

    public static Midia procurarMidia(int codigo) {

        for (Midia midia:midias) {
            if (midia.codigo == codigo) {
                return midia;
            }
        }
        return null;

    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void alterarEmprestimo () {
        this.emprestado = !this.emprestado;
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

    @Override
    public String toString() {
        return "Midia{" +
                "codigo=" + codigo +
                ", emprestado=" + emprestado +

                '}';
    }
}
