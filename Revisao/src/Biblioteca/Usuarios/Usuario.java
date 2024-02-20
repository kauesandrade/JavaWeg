package Biblioteca.Usuarios;

import Biblioteca.Midia;
import Biblioteca.Midias.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Usuario {

    // Armazenar todos os ususarios do sistema, independente da sua tipagem especifica.
    private final static ArrayList<Usuario> usuarios = new ArrayList<>();

    private String nome;
    private String usuario;
    private String senha;
    protected ArrayList<Midia> emprestimos;

    public Usuario (String nome, String usuario, String senha) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
    }

    public void alterarSenha (String senha) {
        this.senha = senha;
    }

    public void alterarNome (String nome) {
        this.nome = nome;
    }

    public String getNome () {
        return nome;
    }

    public static void addUsuario (Usuario usuario) {
        System.out.println(usuario.usuario);
        System.out.println(usuario.senha);

        usuarios.add(usuario);
    }

    public static void removeUsuario (Usuario usuario) {
        usuarios.remove(usuario);
    }

    public static Usuario login (String username, String senha) {
        for (Usuario usuarioAvaliado : usuarios) {
            if (usuarioAvaliado.usuario.equals(username) &&
                usuarioAvaliado.senha.equals(senha)){
                return usuarioAvaliado;
            }
        }
        return null;
    }

    protected abstract boolean adicionarEmprestimo (Midia midia);

    protected int analiseDeMidiasEmprestadas (Midia midia) {

        int qtd = 0;

        for (Midia midiaAnalise:emprestimos) {
            if (midiaAnalise instanceof DVD && midia instanceof DVD) {
                qtd++;
            } else if (midiaAnalise instanceof Livro && midia instanceof Livro) {
                qtd++;
            } else if (midiaAnalise instanceof Revista && midia instanceof Revista) {
                qtd++;
            } else if (midiaAnalise instanceof Jornal && midia instanceof Jornal) {
                qtd++;
            }
        }
        return qtd;
    }

    public String consultarMidia (int codigo) {

        Midia midia = Midia.procurarMidia(codigo);

        if (midia == null) {
            return "Midia nao encontrada";
        }
        return midia.toString();

    }

    public List<Midia> getEmprestimos() {
        return emprestimos;
    }

    public static Usuario procurarUsuario(String usuario){
        for(Usuario usuarioAchado : usuarios){
            if(usuarioAchado.usuario.equals(usuario)){
                return usuarioAchado;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
