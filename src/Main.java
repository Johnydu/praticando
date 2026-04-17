import funcionario.FuncionarioService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FuncionarioService service = new FuncionarioService(sc);
        String resposta;

        do {
            System.out.println(
                    "Cadastrar: (1), Lista de cadastro: (2), Procurar por nome: (3), " +
                    "Deletar funcionario: (4), Alterar dados: (5), Sair: (6)"
            );
            resposta = sc.nextLine();

            switch (resposta) {
                case "1" -> service.cadastrar();
                case "2" -> service.listaFuncionarios();
                case "3" -> {
                    System.out.println("Digite o nome da pessoa que deseja procurar:");
                    String nome = sc.nextLine();
                    service.buscarPorNome(nome);
                }
                case "4" -> service.deletarFuncionario();
                case "5" -> service.menuAlteracoes();
                case "6" -> System.out.println("Encerrando programa !!");
                default -> System.out.println("Opcao invalida");
            }
        } while (!resposta.equals("6"));

        System.out.println("Programa encerrado com sucesso !!");
        sc.close();
    }
}
