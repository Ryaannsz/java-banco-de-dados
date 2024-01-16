package br.com.aplicacoesjava.maven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class conexao {
	//Referência global
	static Scanner sc = new Scanner(System.in);
	static Connection conexao = null;
	
	public static void main(String[] args) throws SQLException {
		int op = 0, whilezinho=0;
		
		//Conexão com o banco
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/java_banco", "root", "1234");
			Autor autor = new Autor(conexao);
			Livro livro = new Livro(conexao);
			
			
			while(whilezinho==0) {
				System.out.println("Caso 1 - cadastro autor");
				System.out.println("Caso 2 - excluir autor");
				System.out.println("Caso 3 - cadastro livro");
				System.out.println("Caso 4 - excluir livro");
				System.out.println("Caso 5 - listar autor");
				System.out.println("Caso 6 - listar livros");
				op=sc.nextInt();
			
			switch(op) {
				case 1:
					autor.cadastroAutor();
					break;
				case 2:
					autor.excluirAutor();
					break;
				case 3:
					livro.cadastrarLivro();
					break;
				case 4:
					livro.excluirLivro();
					break;
				case 5:
					autor.listarAutor();
					break;
				case 6:
					livro.listarLivros();
					break;
				default:
					whilezinho++;
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
	
	
	
	/*
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
	*/
	
}
