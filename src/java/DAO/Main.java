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

public class Main {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
                Connection conexao = connectionFactory.getConnection(); // Chamando a conexão
	}

}
