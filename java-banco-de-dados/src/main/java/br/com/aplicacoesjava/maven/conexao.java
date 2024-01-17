package br.com.aplicacoesjava.maven;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


import com.google.protobuf.TextFormat.ParseException;

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
				System.out.println("Caso 7 - criar usuarios");
				System.out.println("Caso 8- remover usuarios");
				System.out.println("Caso 9 - listar usuarios");
				System.out.println("Caso 10 - criar emprestimos");
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
				case 7:
					criarUsuario();
					break;
				case 8:
					excluirUsuario();
					break;
				case 9:
					listarUsuarios();
					break;
				case 10:
					criarEmprestimo();
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
	
	public static void criarUsuario() {
		System.out.println("Digite o nome do usuário: ");
		String nomeUsuario = sc.next();
		System.out.println("Digite o CPF: ");
		String cpfUsuario = sc.next();
		
		Usuario usuario = new Usuario(nomeUsuario, cpfUsuario, conexao);
		usuario.criarUsuario();
	}
	
	public static void excluirUsuario() {
		listarUsuarios();
		
		System.out.println("Digite o nome do usuário que deseja excluir: ");
		String nomeUsuario = sc.next();
		
		Usuario usuario = new Usuario(nomeUsuario, null, conexao);
		usuario.excluirUsuario();
	}
	
	public static void listarUsuarios() {
		Usuario usuario = new Usuario(null, null, conexao);
		usuario.listarUsuarios();
	}
	
	public static void criarEmprestimo() {
		System.out.println("Qual usuário que deseja pegar um livro?");
		String nomeUsuario = sc.next();
		
		
		System.out.println("Qual livro "+nomeUsuario+" deseja pegar?");
		String tituloLivro = sc.next();
		
		System.out.println("Qual a data deseja devolver? Formato: dd/MM/yyyy");
		String inputDate = sc.next();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			
            java.util.Date utilDate = dateFormat.parse(inputDate);
            Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			Emprestimo emprestimo = new Emprestimo(nomeUsuario, tituloLivro, sqlDate, conexao);
			emprestimo.criarEmprestimo();
		} catch (java.text.ParseException e) {
			System.out.println("Erro ao formatar a data: "+e.getMessage());
		}
	}
	
	
	
}
