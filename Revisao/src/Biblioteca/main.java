package Biblioteca;

import Biblioteca.Usuarios.Bibliotecario;
import Biblioteca.Usuarios.Cliente;
import Biblioteca.Usuarios.Funcionario;
import Biblioteca.Usuarios.Usuario;

import java.util.Scanner;

public class main {
    private static final Scanner sc = new Scanner(System.in);

    private static Usuario usuarioLogado;
    public static void main(String[] args) {

        System.out.println("Bem vindo a Biblioteca");

        System.out.println("""
                1 - Cadastro de Usuario
                2 - Login
                3 - Sair
                """);

        int escolha = sc.nextInt();
        switch (escolha){
            case 1 -> cadastroUsuario();
            case 2 -> loginUsuario();
            case 3 -> System.exit(0);
        }

    }

    private static void loginUsuario() {

        System.out.println("");
        Usuario usuario = new Cliente("","","");
        Usuario.addUsuario(usuario);

    }

    private static void cadastroUsuario() {

        //Usuario usuario = null;

        do {

            System.out.println("");
            usuarioLogado = Usuario.login("","");

        } while (usuarioLogado == null);

        //while (usuario == null);

        menuUsuario();

    }

    private static void menuUsuario() {

        System.out.println("""
                1 -> Alterar Nome
                2 -> Alterar Senha
                3 -> Ver Emprestimos
                4 -> Ver Perfil
                5 -> Consultar disponibilidade de Midia em estoque na biblioteca
                """);

        if (usuarioLogado instanceof Funcionario) {
            System.out.println("""
                    6 -> Cadastrar usuario
                    7 -> Remover usuario
                    8 -> Emprestimo de Midia
                    9 -> Devolucao de Midia
                    10 -> Ver Midias
                    """);
            if (usuarioLogado instanceof Bibliotecario) {
                System.out.println("""
                        11 -> Cadastro de Midia
                        12 -> Remover Midia
                        """);
            }
        }

        System.out.println("0 - Logout");



    }

}