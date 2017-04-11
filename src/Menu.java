import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Menu {
	
	private static Scanner sc = new Scanner(System.in);
	static String nome, senha;
	
	public static void menuPrincipal() throws UnknownHostException, IOException{
		System.out.println("----- Sistema da biblioteca -----");
		System.out.print("Nome: ");
		nome = sc.next();
		System.out.print("Senha: ");
		senha = sc.next();
		
		Socket socket = new Socket("localhost", 3333);
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		
		out.writeUTF(nome);
		out.writeUTF(senha);
		
		boolean usuarioExiste = in.readBoolean();
		
		if(usuarioExiste){
			menuInterno(socket, in, out);
		}
		else{
			mensagemErro();
			menuPrincipal();
		}
			
	}
	
	public static void menuInterno(Socket socket, DataInputStream in, DataOutputStream out) throws UnknownHostException, IOException{
		out.writeInt(2);
		System.out.println("----- Escolha uma opção -----");
		System.out.println("1- Listar todos os livros");
		System.out.println("2- Emprestar um livro");
		System.out.println("3- Devolver um livro");
		System.out.println("4- Adicionar um livro");
		System.out.println("5- Excluir um livro");
		System.out.println("6- Voltar para o menu principal");
		System.out.print("Opção escolhida: ");
		int opcao = Integer.parseInt(sc.next());
		
		switch(opcao){
			case 1:
				out.writeInt(opcao);
				String todosLivros = in.readUTF();
				System.out.println(todosLivros);
				menuInterno(socket, in, out);
				break;
			case 2:
				System.out.print("Nome do livro: ");
				String tituloLivro = sc.next();
				out.writeUTF(tituloLivro);
				System.out.println(in.readUTF());
				break;
			case 3:
				out.writeInt(opcao);
				break;
			case 4:
				out.writeInt(opcao);
				System.out.print("Nome do livro: ");
				String nomeLivro = sc.next();
				System.out.print("Ano de Publicação: ");
				int anoPublicacao = sc.nextInt();
				out.writeUTF(nomeLivro);
				out.writeInt(anoPublicacao);
				System.out.println(in.readUTF());
				menuInterno(socket, in, out);
				break;
			case 5:
				out.writeInt(opcao);
				break;
			case 6:
				System.out.println("Voltando para o menu principal");
				menuPrincipal();
				break;
			default: 
				System.out.println("Digite uma opção correspondente");
				menuInterno(socket, in, out);
				break;
		}
	}
	
	public static void mensagemErro(){
		System.out.println("Erro na operação! Retornando ao menu principal");
	}
	
}
