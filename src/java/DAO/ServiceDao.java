/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Ramon Godoy
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao {

    private final Connection connection;

    // Construtor para inicializar a conexão com o banco de dados
    public ServiceDao() {
        this.connection = new ConnectionFactory().getConnection();
    }

    // Método para inserir um novo serviço de banho
    public void addService(Bath bath) {
        String sql = "INSERT INTO petservices(id, petName, ownerName, service, duration, price) VALUES(?, ?, ?, ?, ?,?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setLong(1, bath.getId());
            stmt.setString(2, bath.getPetName());
            stmt.setString(3, bath.getOwnerName());
            stmt.setString(4, bath.getService());
            stmt.setLong(5, bath.getDuration());
            stmt.setDouble(6, bath.getPrice());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir!" + e);
        }

    }

    //metodo para retornar todos serviços registrados
    public List<Bath> getAllServices() {
        List<Bath> listaServices = new ArrayList<>();
        String sql = "SELECT * FROM petservices WHERE service = 'banho'";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Bath bath = new Bath(
                        rs.getInt("id"),
                        rs.getString("petName"),
                        rs.getString("ownerName"),
                        rs.getString("service"),
                        rs.getInt("duration"),
                        rs.getDouble("price")
                );
                listaServices.add(bath);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Erro!" + e.getMessage());
        }

        return listaServices;
    }
    //metodo para buscar um servico pelo id

    public Bath getIdService(int id) {
        String sql = "SELECT * FROM petservices WHERE id = ? AND service = 'banho'";
        Bath bath = null;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                bath = new Bath();
                bath.setId(rs.getInt("id"));
                bath.setPetName(rs.getString("petName"));
                bath.setOwnerName(rs.getString("ownerName"));
                bath.setService(rs.getString("service"));
                bath.setDuration(rs.getInt("duration"));
                bath.setPrice(rs.getDouble("price"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return bath;
    }
    //metodo para atualizar um servico

    public boolean updateService(Bath bath) {
        String sql = "UPDATE petservices SET petName = ?, ownerName = ?, service = ?, duration = ?, price = ? WHERE id = ?";
        int cont = 0;

        try {
            try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
                stmt.setString(1, bath.getPetName());
                stmt.setString(2, bath.getOwnerName());
                stmt.setString(3, bath.getService());
                stmt.setLong(4, bath.getDuration());
                stmt.setDouble(5, bath.getPrice());
                stmt.setLong(6, bath.getId());
                cont = stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar serviço!" + e);
        }
        return cont == 1;
    }

    public boolean remove(int id) {
        String sql = "DELETE FROM petservices WHERE id = ?";
        int serviceRemove = 0;

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, id);
            serviceRemove = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serviceRemove == 1;
    }
}
