package database;

import funcionario.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    public void addFuncionario(String nome, String telefone, int idade, String cargo, Double salario) {
        String sql = "INSERT INTO funcionarios (nome, telefone, idade, cargo, salario) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConexaoDB.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, telefone);
            statement.setInt(3, idade);
            statement.setString(4, cargo);
            statement.setDouble(5, salario);
            statement.executeUpdate();
            System.out.println("Funcionario cadastrado!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar funcionario: " + e.getMessage());
        }
    }

    public List<Funcionario> listaFuncionarios() {
        String sql = "SELECT id, nome, telefone, idade, cargo, salario FROM funcionarios";
        List<Funcionario> listaFuncionarios = new ArrayList<>();

        try (Connection connection = ConexaoDB.conectar();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getLong("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setIdade(rs.getInt("idade"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSalario(rs.getDouble("salario"));
                listaFuncionarios.add(funcionario);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar funcionarios: " + e.getMessage());
        }

        return listaFuncionarios;
    }

    public List<Funcionario> buscarPorNome(String nome) {
        String sql = "SELECT id, nome, telefone, idade, cargo, salario FROM funcionarios WHERE nome LIKE ?";
        List<Funcionario> funcionariosEncontrados = new ArrayList<>();

        try (Connection connection = ConexaoDB.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + nome + "%");

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId(rs.getLong("id"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setTelefone(rs.getString("telefone"));
                    funcionario.setIdade(rs.getInt("idade"));
                    funcionario.setCargo(rs.getString("cargo"));
                    funcionario.setSalario(rs.getDouble("salario"));
                    funcionariosEncontrados.add(funcionario);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar funcionario: " + e.getMessage());
        }

        return funcionariosEncontrados;
    }

    public boolean deletarFuncionario(long id) {
        String sql = "DELETE FROM funcionarios WHERE id = ?";

        try (Connection connection = ConexaoDB.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao deletar funcionario: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarSalario(long id, double salario) {
        String sql = "UPDATE funcionarios SET salario = ? WHERE id = ?";

        try (Connection connection = ConexaoDB.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, salario);
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar salario: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarTelefone(long id, String telefone) {
        String sql = "UPDATE funcionarios SET telefone = ? WHERE id = ?";

        try (Connection connection = ConexaoDB.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, telefone);
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar telefone: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarIdade(long id, int idade) {
        String sql = "UPDATE funcionarios SET idade = ? WHERE id = ?";

        try (Connection connection = ConexaoDB.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idade);
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar idade: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarCargo(long id, String cargo) {
        String sql = "UPDATE funcionarios SET cargo = ? WHERE id = ?";

        try (Connection connection = ConexaoDB.conectar();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cargo);
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cargo: " + e.getMessage());
            return false;
        }
    }
}
