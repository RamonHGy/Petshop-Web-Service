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
import java.sql.DriverManager;
import java.sql.SQLException;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    
    private Properties dbProperties;
    
    public ConnectionFactory() {
        loadProperties();
    }
    
    private void loadProperties() {
        dbProperties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (input != null) {
                dbProperties.load(input);
            } else {
                System.out.println("Arquivo database.properties não encontrado, usando valores padrão");
                setDefaultProperties();
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar propriedades: " + e.getMessage());
            setDefaultProperties();
        }
    }
    
    private void setDefaultProperties() {
        dbProperties.setProperty("db.url", "jdbc:derby://localhost:1527/apcquatro");
        dbProperties.setProperty("db.user", "apc4");
        dbProperties.setProperty("db.password", "apc4");
    }
    
    public Connection getConnection() {
        try {
            String url = dbProperties.getProperty("db.url");
            String user = dbProperties.getProperty("db.user");
            String password = dbProperties.getProperty("db.password");
            
            Connection conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão bem sucedida!");
            return conexao;
            
        } catch (SQLException e) {
            System.out.println("Falha de conexão com banco de dados! " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
