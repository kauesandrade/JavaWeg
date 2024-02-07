package Biblioteca.Usuarios;

import Biblioteca.Midia;

public abstract class Funcionario extends Usuario {

    private int matricula;
    private double salario;

    public Funcionario(String nome, String usuario, String senha) {
        super(nome, usuario, senha);
    }

    public abstract void cadastrarUsuario(Usuario usuario);
    public abstract void removerUsuario(Usuario usuario);

    public String consultaMidia(int codigo){
        Midia midia = Midia.procurarMidia(codigo);
        if(midia == null){
            return "Mídia não encontrada";
        }
        return midia.toString();
    }

    @Override
    protected boolean adicionarEmprestimo(Midia midia){
        int qtd = analiseDeMidiaEmprestadas(midia);
        if (qtd < 5){
            emprestimos.add(midia);
            return true;
        }
        return false;
    }

    public boolean emprestarMidia(Midia midia, Usuario usuario){
        if(!midia.isEmprestada()){
            if(usuario.adicionarEmprestimo(midia)){
                midia.alterarEmprestimo();
                return false;
            }
        }
        return false;
    }

    public boolean devolverMidia(Midia midia, Usuario usuario){
        if (usuario.emprestimos.contains(midia)){
            usuario.emprestimos.remove(midia);
            midia.alterarEmprestimo();
            return true;
        }
        return false;
    }

}
