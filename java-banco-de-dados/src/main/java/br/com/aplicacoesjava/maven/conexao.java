package br.com.aplicacoesjava.maven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class conexao {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws SQLException {
		Connection conexao = null;
		
		//Conexão com o banco
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/java_banco", "root", "1234");
			
			int num=0,op=0;
			while(num==0) {
			System.out.println("Digite a opção que desejar: ");
			System.out.println("1 - Listar");
			System.out.println("2 - Adicionar");
			System.out.println("3 - Remover");
			System.out.println("Qualquer outra tecla fechará o programa");
			op = sc.nextInt();
				switch(op) {
				
					case 1:
						listar(conexao);
						break;
					case 2:
						insert(conexao);
						break;
					case 3:
						remover(conexao);
						break;
					default:
						System.out.println("Obrigado por utilizar o programa!");
						num++;
				}
			}
			
			
			
			
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Classe não encontrada");
		}catch(SQLException e){
			System.out.println("Conexão não realizada: "+e.getMessage());
		} finally {
			if(conexao != null) {
				conexao.close();
			}
		}
		
		
		
	}
	
	
	public static boolean insert(Connection conexao) {
		
		try {
			PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO cliente(nome) VALUE (?)");
			System.out.println("Digite o nome do cliente: ");
			String nome = sc.next();
		
			pstmt.setString(1, nome);
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir no banco de dados: "+e.getMessage());
			return false;
		}
		
	
		
	}
	
	public static boolean listar(Connection conexao) {
		
	
			try {
				ResultSet listarClientes = conexao.createStatement().executeQuery("SELECT * FROM CLIENTE");
				while(listarClientes.next()) {
					System.out.println("Nome: "+listarClientes.getString("nome"));
				}
				return true;
			} catch (SQLException e) {
				System.out.println("Erro ao listar os clients: "+e.getMessage());
				return false;
			}
			
			
	}
	
	public static boolean remover(Connection conexao) {
		
		try {
			PreparedStatement pstmt = conexao.prepareStatement("DELETE FROM cliente WHERE nome=?");
			System.out.println("Digite o nome que deseja excluir");
			String nome = sc.next();
			pstmt.setString(1, nome);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao deletar o nome: "+e.getMessage());
			return false;
		}
		
		
	
	}
	
}
