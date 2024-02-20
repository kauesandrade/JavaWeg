package Biblioteca;

import Biblioteca.Midias.Livro;
import Biblioteca.Usuarios.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    private static final Scanner sc = new Scanner(System.in);

    private static Usuario usuarioLogado;

//    private static Usuario atendente = ;
    private static Usuario cliente = new Cliente("Beatriz", "bia", "123");
    private static Usuario bibliotecario = new Bibliotecario("Joana", "jo", "123");

    public static void main(String[] args) {

        Usuario.addUsuario(bibliotecario);
        Usuario.addUsuario(new Atendente("Luiz", "lu", "123"));
        Usuario.addUsuario(cliente);

        do {
            System.out.println("Bem vindo a Biblioteca");
            System.out.println("""
                1 - Login
                2 - Sair
                """);

            int escolha = sc.nextInt();

            switch (escolha){
                case 1 -> loginUsuario();
                case 2 -> System.exit(0);
            }

        }while (true);


    }

    private static void loginUsuario() {

        do {

            System.out.println("Usuário: ");
            String usuario = sc.next();
            System.out.println("Senha: ");
            String senha = sc.next();


            System.out.println(Usuario.login(usuario,senha));

            usuarioLogado = Usuario.login(usuario,senha);

        } while (usuarioLogado == null);

        menuUsuario();

    }

    private static void menuUsuario() {

        do {

        System.out.println("""
                1 - Alterar Nome
                2 - Alterar Senha
                3 - Ver Emprestimos
                4 - Ver Perfil
                5 - Consultar disponibilidade de Midia em estoque na biblioteca
                """);

        if (usuarioLogado instanceof Funcionario) {
            System.out.println("""
                    6 - Cadastrar usuario
                    7 - Remover usuario
                    8 - Emprestimo de Midia
                    9 - Devolucao de Midia
                    10 - Ver Midias
                    """);

            if (usuarioLogado instanceof Bibliotecario) {
                System.out.println("""
                        11 - Cadastro de Midia
                        12 - Remover Midia
                        """);
            }
        }

        System.out.println("0 - Logout");

        int escolha = sc.nextInt();

        switch (escolha){
            case 0 -> logout();
            case 1-> alterarNome();
            case 2 -> alterarSenha();
            case 3 -> verEmprestimos();
            case 4 -> verPerfil();
            case 5 -> consultaMidia();
            case 6 -> cadastroUsuario();
            case 7 -> removeUsuario();
            case 8 -> emprestimoMidia();
//            case 9 -> devolucaoMidia();
//            case 10 -> verMidia();
//            case 11 -> cadastroMidia();
//            case 12 -> removeMidia();


        }

        }while (usuarioLogado != null);
    }

    private static void emprestimoMidia() {

        System.out.println(usuarioLogado.getNome());

        if(usuarioLogado instanceof Funcionario){

            Usuario usuarioEmprestimo = null;
            Midia midiaEmprestimo = null;

            do{
                System.out.println("Digite o nome de usuario que terá o emprestimo: ");
                String usuarioAchar = sc.next();
                usuarioEmprestimo = Usuario.procurarUsuario(usuarioAchar);

                if(usuarioEmprestimo == null){
                    System.out.println("Usuário não encontrado");
                }

            }while (usuarioEmprestimo == null);

            do{

                System.out.println("Digite o código da mídia para o emprestimo: ");
                int codigoMidia = sc.nextInt();

                midiaEmprestimo = Midia.procurarMidia(codigoMidia);

                if(midiaEmprestimo == null){
                    System.out.println("Midia não encontrada");
                }else if(midiaEmprestimo.isEmprestado()){
                    System.out.println("Midia já emprestada");
                }

            }while (midiaEmprestimo == null);




            if(usuarioEmprestimo != null && midiaEmprestimo != null){
                ((Funcionario) usuarioLogado).emprestarMidia(midiaEmprestimo, usuarioEmprestimo);
            }

        }

    }

    private static void removeUsuario() {
        System.out.println("Digite o nome do usuario que deseja remover: ");
        String nome = sc.next();

        Usuario usuarioRemover = Usuario.procurarUsuario(nome);

        if(usuarioRemover == null){
            System.out.println("Usuário não encontrado");
        }else{
            Usuario.removeUsuario(usuarioRemover);
        }
    }

    private static void cadastroUsuario() {

        System.out.println("Nome: ");
        String nome = sc.next();
        System.out.println("Usuário: ");
        String usuario = sc.next();
        System.out.println("Senha: ");
        String senha = sc.next();

        Usuario usuarioCadastro = new Cliente(nome,usuario,senha);
        Usuario.addUsuario(usuarioCadastro);

    }

    private static void consultaMidia() {

        System.out.println("Digite o código da mídia que deseja consultar: ");
        int codigoMidia = sc.nextInt();
        System.out.println(usuarioLogado.consultarMidia(codigoMidia));

    }

    private static void verPerfil() {
        System.out.println(usuarioLogado.toString());
    }

    private static void verEmprestimos() {

        if(usuarioLogado.getEmprestimos() == null){
            System.out.println("Não há emprestimos");
        }else{
            for(Midia midia : usuarioLogado.getEmprestimos()){
                System.out.println(midia.toString());
            }
        }


    }

    private static void alterarSenha() {
        System.out.println("digite sua senha nova: ");
        String novaSenha = sc.next();
        usuarioLogado.alterarSenha(novaSenha);
    }

    private static void alterarNome() {
        System.out.println("Digite seu novo nome: ");
        String novoNome = sc.next();
        usuarioLogado.alterarNome(novoNome);
    }



    private static void logout(){
        usuarioLogado = null;
    }

}