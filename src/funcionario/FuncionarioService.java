package funcionario;

import database.FuncionarioDAO;

import java.util.List;
import java.util.Scanner;

public class FuncionarioService {
    private final Scanner sc;
    private final FuncionarioDAO funcionarioDao = new FuncionarioDAO();

    public FuncionarioService(Scanner sc) {
        this.sc = sc;
    }

    public void cadastrar() {
        Funcionario funcionario = new Funcionario();
        System.out.println("Digite o nome:");
        funcionario.setNome(sc.nextLine().trim());

        if (funcionario.getNome().isEmpty()) {
            System.out.println("Nome nao pode ficar vazio.");
            return;
        }

        funcionario.setTelefone(lerTextoObrigatorio("Digite o telefone:"));
        if (funcionario.getTelefone() == null) {
            return;
        }

        funcionario.setIdade(lerIdade());
        funcionario.setCargo(lerTextoObrigatorio("Digite o cargo:"));
        if (funcionario.getCargo() == null) {
            return;
        }

        funcionario.setSalario(lerSalario());
        funcionarioDao.addFuncionario(
                funcionario.getNome(),
                funcionario.getTelefone(),
                funcionario.getIdade(),
                funcionario.getCargo(),
                funcionario.getSalario()
        );
    }

    public void listaFuncionarios() {
        List<Funcionario> lista = funcionarioDao.listaFuncionarios();
        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }

        for (Funcionario funcionario : lista) {
            System.out.println(funcionario);
        }
    }

    public void buscarPorNome(String nome) {
        String nomeTratado = nome.trim();
        if (nomeTratado.isEmpty()) {
            System.out.println("Informe um nome para busca.");
            return;
        }

        List<Funcionario> funcionarios = funcionarioDao.buscarPorNome(nomeTratado);
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario encontrado com esse nome.");
            return;
        }

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    public void deletarFuncionario() {
        Long id = lerIdFuncionario("Digite o ID do funcionario que deseja deletar:");
        if (id == null) {
            return;
        }
        System.out.println("Tem certeza que deseja excluir (S/N) ?");
        String resposta = sc.nextLine().trim().toLowerCase();
        if(resposta.equalsIgnoreCase("s")) {
            boolean removido = funcionarioDao.deletarFuncionario(id);
            if (removido) {
                System.out.println("Funcionario deletado com sucesso.");
                return;
            }
        }else return;

        System.out.println("Nenhum funcionario encontrado com o ID informado.");
    }

    public void menuAlteracoes() {
        String opcao;

        do {
            System.out.println(
                    "Alterar salario: (1), Alterar telefone: (2), Alterar idade: (3), " +
                    "Alterar cargo: (4), Voltar: (5)"
            );
            opcao = sc.nextLine().trim();

            switch (opcao) {
                case "1" -> alterarSalario();
                case "2" -> alterarTelefone();
                case "3" -> alterarIdade();
                case "4" -> alterarCargo();
                case "5" -> System.out.println("Voltando ao menu principal.");
                default -> System.out.println("Opcao invalida.");
            }
        } while (!opcao.equals("5"));
    }

    public void alterarSalario() {
        Long id = lerIdFuncionario("Digite o ID do funcionario que deseja alterar:");
        if (id == null) {
            return;
        }

        double novoSalario = lerSalario();
        boolean atualizado = funcionarioDao.atualizarSalario(id, novoSalario);
        if (atualizado) {
            System.out.println("Salario atualizado com sucesso.");
            return;
        }

        System.out.println("Nenhum funcionario encontrado com o ID informado.");
    }

    public void alterarTelefone() {
        Long id = lerIdFuncionario("Digite o ID do funcionario que deseja alterar:");
        if (id == null) {
            return;
        }

        String novoTelefone = lerTextoObrigatorio("Digite o novo telefone:");
        if (novoTelefone == null) {
            return;
        }

        boolean atualizado = funcionarioDao.atualizarTelefone(id, novoTelefone);
        if (atualizado) {
            System.out.println("Telefone atualizado com sucesso.");
            return;
        }

        System.out.println("Nenhum funcionario encontrado com o ID informado.");
    }

    public void alterarIdade() {
        Long id = lerIdFuncionario("Digite o ID do funcionario que deseja alterar:");
        if (id == null) {
            return;
        }

        int novaIdade = lerIdade();
        boolean atualizado = funcionarioDao.atualizarIdade(id, novaIdade);
        if (atualizado) {
            System.out.println("Idade atualizada com sucesso.");
            return;
        }

        System.out.println("Nenhum funcionario encontrado com o ID informado.");
    }

    public void alterarCargo() {
        Long id = lerIdFuncionario("Digite o ID do funcionario que deseja alterar:");
        if (id == null) {
            return;
        }

        String novoCargo = lerTextoObrigatorio("Digite o novo cargo:");
        if (novoCargo == null) {
            return;
        }

        boolean atualizado = funcionarioDao.atualizarCargo(id, novoCargo);
        if (atualizado) {
            System.out.println("Cargo atualizado com sucesso.");
            return;
        }

        System.out.println("Nenhum funcionario encontrado com o ID informado.");
    }

    private double lerSalario() {
        while (true) {
            System.out.println("Digite o salario:");
            String entrada = sc.nextLine().trim().replace(",", ".");

            try {
                double salario = Double.parseDouble(entrada);
                if (salario < 0) {
                    System.out.println("Salario nao pode ser negativo.");
                    continue;
                }
                return salario;
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero valido para o salario.");
            }
        }
    }

    private int lerIdade() {
        while (true) {
            System.out.println("Digite a idade:");
            String entrada = sc.nextLine().trim();

            try {
                int idade = Integer.parseInt(entrada);
                if (idade <= 0) {
                    System.out.println("Idade deve ser maior que zero.");
                    continue;
                }
                return idade;
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero inteiro para a idade.");
            }
        }
    }

    private String lerTextoObrigatorio(String mensagem) {
        System.out.println(mensagem);
        String valor = sc.nextLine().trim();

        if (valor.isEmpty()) {
            System.out.println("Campo obrigatorio nao pode ficar vazio.");
            return null;
        }

        return valor;
    }

    private Long lerIdFuncionario(String mensagem) {
        System.out.println(mensagem);
        String entrada = sc.nextLine().trim();

        if (entrada.isEmpty()) {
            System.out.println("ID nao pode ficar vazio.");
            return null;
        }

        try {
            long id = Long.parseLong(entrada);
            if (id <= 0) {
                System.out.println("ID deve ser maior que zero.");
                return null;
            }
            return id;
        } catch (NumberFormatException e) {
            System.out.println("ID invalido. Digite um numero inteiro.");
            return null;
        }
    }
}
