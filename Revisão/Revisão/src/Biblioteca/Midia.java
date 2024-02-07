package Biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Midia {

    private static final ArrayList<Midia> midias = new ArrayList<>();

    private int codigo;
    private boolean emprestado;

    public static Midia procurarMidia(int codigo) {
        for (Midia midia : midias){
            if(midia.codigo == codigo){
                return midia;
            }
        }
        return null;
    }

    public boolean isEmprestada(){
        return this.emprestado;
    }

    public void alterarEmprestimo(){
        this.emprestado = !this.emprestado;
    }
}
