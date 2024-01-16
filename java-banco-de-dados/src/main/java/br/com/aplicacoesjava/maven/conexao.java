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
					criarAutor();
					break;
				case 2:
					removerAutor();
					break;
				case 3:
					criarLivro();
					break;
				case 4:
					excluirLivro();
					break;
				case 5:
					listarAutor();
					break;
				case 6:
					listarLivro();
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
	
	
	public static void criarAutor() {
		System.out.println("Digite o nome do autor: ");
		String nome = sc.next();
		System.out.println("Digite a nacionalidade do autor: ");
		String nacionalidade = sc.next();
		Autor autor = new Autor(nome, nacionalidade, conexao);
		autor.cadastroAutorBanco();
	}
	
	public static void removerAutor() {
		
		listarAutor();
		
		System.out.println("Digite o nome do autor: ");
		String nome = sc.next();
		Autor autor = new Autor(nome, null, conexao);
		autor.excluirAutorBanco();
	}
	
	public static void listarAutor() {
		Autor autor = new Autor(null, null, conexao);
		autor.listarAutor();
	}
	
	
	public static void criarLivro() {
		System.out.println("Digite o titulo: ");
		String titulo = sc.next();
		System.out.println("Digite o ano de publicação: ");
		int ano = sc.nextInt();
		
		Livro livro = new Livro(titulo, ano, conexao);
		livro.cadastrarLivroBanco();
		
	}
	
	public static void excluirLivro() {
		listarLivro();
		
		System.out.println("Informe o titulo do livro que deseja excluir: ");
		String titulo = sc.next();
		
		Livro livro = new Livro(titulo, 0, conexao);
		livro.excluirLivroBanco();
		
	}
	
	public static void listarLivro() {
		Livro livro = new Livro(null, 0, conexao);
		livro.listarLivrosBanco();
	}
	
	
	
}
