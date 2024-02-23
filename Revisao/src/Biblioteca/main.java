package Biblioteca;

import Biblioteca.Midias.DVD;
import Biblioteca.Midias.Jornal;
import Biblioteca.Midias.Livro;
import Biblioteca.Midias.Revista;
import Biblioteca.Usuarios.*;

import java.util.Scanner;

public class main {
    private static final Scanner sc = new Scanner(System.in);

    private static Usuario usuarioLogado;

    public static void main(String[] args) {

        Usuario.addUsuario(new Bibliotecario("Joana", "jo", "123"));
        Usuario.addUsuario(new Atendente("Luiz", "lu", "123"));
        Usuario.addUsuario(new Cliente("Beatriz", "bia", "123"));

        do {
            System.out.println("Bem vindo a Biblioteca");
            System.out.print("""
                1 - Login
                0 - Sair
                ->
                """);

            int escolha = Integer.parseInt(sc.nextLine());

            switch (escolha){
                case 1 -> loginUsuario();
                case 0 -> System.exit(0);
            }

        }while (true);


    }

    private static void loginUsuario() {

        do {

            System.out.print("\nUsuário: ");
            String usuario = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();
            usuarioLogado = Usuario.login(usuario,senha);

        } while (usuarioLogado == null);

        menuUsuario();

    }

    private static void menuUsuario() {

        do {

        System.out.print("""
                \nMenu Inicial: 
                1 - Alterar Nome
                2 - Alterar Senha
                3 - Ver Emprestimos
                4 - Ver Perfil
                5 - Consultar disponibilidade de Midia em estoque na biblioteca
                """);

        if (usuarioLogado instanceof Funcionario) {
            System.out.print("""
                    6 - Cadastrar usuario
                    7 - Remover usuario
                    8 - Emprestimo de Midia
                    9 - Devolucao de Midia
                    10 - Ver Midias
                    """);

            if (usuarioLogado instanceof Bibliotecario) {
                System.out.print("""
                        11 - Cadastro de Midia
                        12 - Remover Midia
                        """);
            }
        }

        System.out.println("0 - Logout" +
                "\n->");

        int escolha = Integer.parseInt(sc.nextLine());

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
            case 9 -> devolucaoMidia();
            case 10 -> verMidia();
            case 11 -> cadastroMidia();
            case 12 -> removeMidia();


        }

        }while (usuarioLogado != null);
    }

    private static void removeMidia() {

        if(usuarioLogado instanceof Bibliotecario){

            System.out.print("\nDigite o código da mídia que deseja remover: ");
            int codigo = Integer.parseInt(sc.nextLine());
            if( Midia.procurarMidia(codigo) != null){
                ((Bibliotecario) usuarioLogado).removerMidia(codigo);
                System.out.print("Mídia removida");
            }else{
                System.out.println("Mídia não encontrada");
            }
        }else{
            System.out.println("\nDigite uma opção valida");
        }



    }

    private static void cadastroMidia() {

        if (usuarioLogado instanceof Bibliotecario){

            System.out.print("\nDigite o código para a nova mídia: ");
            int codigo = Integer.parseInt(sc.nextLine());

            if(Midia.procurarMidia(codigo) == null){


                System.out.print("Digite o nome para a mídia: ");
                String nomeMidia = sc.nextLine();

                System.out.print("""
               \nDigite a opção de mídia que deseja cadastrar:
                1 - DVD
                2 - Jornal
                3 - Livro
                4 - Revista
                ->
                """);

                int escolha = Integer.parseInt(sc.nextLine());

                switch (escolha){
                    case 1: ((Bibliotecario) usuarioLogado).cadastrarMidia(new DVD(codigo, nomeMidia));
                    System.out.println("DVD cadastrado");
                    break;
                    case 2: ((Bibliotecario) usuarioLogado).cadastrarMidia(new Jornal(codigo, nomeMidia));
                    System.out.println("Jornal cadastrado");
                    break;
                    case 3: ((Bibliotecario) usuarioLogado).cadastrarMidia(new Livro(codigo, nomeMidia));
                    System.out.println("Livro cadastrado");
                    break;
                    case 4: ((Bibliotecario) usuarioLogado).cadastrarMidia(new Revista(codigo, nomeMidia));
                    System.out.println("Revista cadastrada");
                    break;
                }



            }else{
                System.out.println("Esse código já foi cadastrado para uma mídia");
            }


        }else{
            System.out.println("\nDigite uma opção valida");
        }


    }

    private static void verMidia() {
        if(usuarioLogado instanceof Funcionario){

           for (Midia midia : Midia.getMidias()){
               System.out.println(midia.toString());
           }

        }else{
            System.out.println("\nDigite uma opção valida");
        }
    }


    private static void devolucaoMidia() {

        if(usuarioLogado instanceof Funcionario){

            Usuario usuarioDevolucao = null;
            Midia midiaDevolucao = null;

            do{
                System.out.print("\nDigite o nome do usuario que deseja fazer a devolução: ");
                String usuarioAchar = sc.nextLine();
                usuarioDevolucao = Usuario.procurarUsuario(usuarioAchar);

                if(usuarioDevolucao == null){
                    System.out.println("Usuário não encontrado");
                }

            }while (usuarioDevolucao == null);


            do{
                System.out.print("Digite o código da mídia que deseja fazer a devolução: ");
                int codigo = Integer.parseInt(sc.nextLine());

                midiaDevolucao = Midia.procurarMidia(codigo);

                if(midiaDevolucao == null){
                    System.out.println("Midia não encontrada");
                }else if(!midiaDevolucao.isEmprestado()){
                    System.out.println("Midia não foi emprestada");
                }

            }while (midiaDevolucao == null);

            if(midiaDevolucao != null && usuarioDevolucao != null){
                ((Funcionario) usuarioLogado).devolverMidia(midiaDevolucao, usuarioDevolucao);
                System.out.println("\nEmprestimo devolvido:" +
                        "\nNome: "+usuarioDevolucao.getNome()+
                        "\nMídia: "+midiaDevolucao.getCodigo());
            }

        }else{
            System.out.println("\nDigite uma opção valida");
        }

    }

    private static void emprestimoMidia() {

        if(usuarioLogado instanceof Funcionario){

            Usuario usuarioEmprestimo = null;
            Midia midiaEmprestimo = null;

            do{
                System.out.print("\nDigite o nome de usuario que terá o emprestimo: ");
                String usuarioAchar = sc.nextLine();
                usuarioEmprestimo = Usuario.procurarUsuario(usuarioAchar);

                if(usuarioEmprestimo == null){
                    System.out.println("Usuário não encontrado");
                }

            }while (usuarioEmprestimo == null);

            do{

                System.out.print("Digite o código da mídia para o empréstimo: ");
                int codigoMidia = Integer.parseInt(sc.nextLine());

                midiaEmprestimo = Midia.procurarMidia(codigoMidia);

                if(midiaEmprestimo == null){
                    System.out.println("Mídia não encontrada");
                }else if(midiaEmprestimo.isEmprestado()){
                    System.out.println("Mídia já emprestada");
                }

            }while (midiaEmprestimo == null);

            if(usuarioEmprestimo != null && midiaEmprestimo != null){
                ((Funcionario) usuarioLogado).emprestarMidia(midiaEmprestimo, usuarioEmprestimo);
                System.out.println("\nEmpréstimo realizado:" +
                        "\n- Nome: "+ usuarioEmprestimo.getNome()+
                        "\n- Mídia: "+ midiaEmprestimo.getCodigo());
            }

        }else{
            System.out.println("\nDigite uma opção valida");
        }

    }

    private static void removeUsuario() {

        if(usuarioLogado instanceof Funcionario){
            System.out.print("\nDigite o nome do usuário que deseja remover: ");
            String nome = sc.nextLine();

            Usuario usuarioRemover = Usuario.procurarUsuario(nome);

            if(usuarioRemover == null){
                System.out.println("Usuário não encontrado");
            }else{
                Usuario.removeUsuario(usuarioRemover);
                System.out.println("Usuário removido: " + usuarioRemover.getNome());
            }
        }else{
            System.out.println("\nDigite uma opção valida");
        }
    }

    private static void cadastroUsuario() {

        if(usuarioLogado instanceof Funcionario){
            System.out.print("\nNome do cliente: ");
            String nome = sc.nextLine();
            System.out.print("Usuário: ");
            String usuario = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();

            Usuario usuarioCadastro = new Cliente(nome,usuario,senha);
            Usuario.addUsuario(usuarioCadastro);

            System.out.println("Usuário Cadastrado");

        }else{
            System.out.println("\nDigite uma opção valida");
        }


    }

    private static void consultaMidia() {

        System.out.print("\nDigite o código da mídia que deseja consultar: ");
        int codigoMidia = Integer.parseInt(sc.nextLine());
        System.out.println(usuarioLogado.consultarMidia(codigoMidia));

    }

    private static void verPerfil() {
        System.out.println(usuarioLogado.toString());
    }

    private static void verEmprestimos() {

        if(usuarioLogado.getEmprestimos().size() == 0){
            System.out.println("\nNão há empréstimos");
        }else{
            System.out.print("Empréstimos: ");
            for(Midia midia : usuarioLogado.getEmprestimos()){
                System.out.println(midia.toString());
            }
        }

    }

    private static void alterarSenha() {
        System.out.print("\nDigite sua senha nova: ");
        String novaSenha = sc.nextLine();
        usuarioLogado.alterarSenha(novaSenha);

        System.out.println("Senha alterada");
    }

    private static void alterarNome() {
        System.out.print("\nDigite seu novo nome: ");
        String novoNome = sc.nextLine();
        usuarioLogado.alterarNome(novoNome);

        System.out.println("Nome alterado para: " + novoNome);
    }



    private static void logout(){
        usuarioLogado = null;
    }

}