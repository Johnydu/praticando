package funcionario;

public class Funcionario {
    private Long id;
    private String nome;
    private String telefone;
    private int idade;
    private String cargo;
    private double salario;

    public Funcionario() {}

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d, Nome: %s, Telefone: %s, Idade: %d, Cargo: %s, Salario: R$ %.2f",
                id, nome, telefone, idade, cargo, salario
        );
    }
}
