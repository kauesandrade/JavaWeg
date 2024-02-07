package Biblioteca.Usuarios;

import Biblioteca.Midia;
import Biblioteca.Midias.*;

import java.util.ArrayList;

public abstract class Usuario {

    //Armazernar todos os usuários do sistema, independente da sua tipagem específica
    private static final ArrayList<Usuario> usuarios = new ArrayList<>();

    private String nome;
    private String usuario;
    private String senha;

    protected ArrayList<Midia> emprestimos;

    public Usuario(String nome, String usuario, String senha){
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
    }

    public void alterarSenha(String senha){
        this.senha = senha;
    }

    public void alterarNome(String nome){
        this.nome = nome;
    }

    public static Usuario login(String username, String senha){
        for(Usuario usuarioAvaliado : usuarios ){
            if (usuarioAvaliado.usuario.equals(username) && usuarioAvaliado.senha.equals(senha)){
                return usuarioAvaliado;
            }
        }
        return null;
    }

    public String getNome() {
        return this.nome;
    }

    public static void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static void removeUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    protected int analiseDeMidiaEmprestadas(Midia midia){
        int qtd = 0;

        for(Midia midiaAnalise : emprestimos){
            if(midiaAnalise instanceof DVD && midia instanceof DVD){
                qtd++;
            } else if(midiaAnalise instanceof Livro && midia instanceof Livro){
                qtd++;
            } else if(midiaAnalise instanceof Revista && midia instanceof Revista){
                qtd++;
            } else if(midiaAnalise instanceof Jornal && midia instanceof Jornal) {
                qtd++;
            }
        }
        return qtd;
    }

    protected abstract boolean adicionarEmprestimo(Midia midia);

}
