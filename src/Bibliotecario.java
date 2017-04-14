import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Bibliotecario extends Thread{
	private Socket socket;
	private Biblioteca biblioteca;
	private int usuarioAtendido;
	
	public Bibliotecario(Socket socket, Biblioteca biblioteca){
		this.socket = socket;
		this.biblioteca = biblioteca;
	}
	
	public void run(){
		try {
			DataInputStream in = new DataInputStream(this.socket.getInputStream());
			DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
			
			String nomeCliente = in.readUTF();
			String senhaCliente = in.readUTF();
			
			out.writeBoolean(biblioteca.existeUsuario(nomeCliente, senhaCliente));
			if(biblioteca.existeUsuario(nomeCliente, senhaCliente))
				usuarioAtendido = biblioteca.posicaoUsuario(nomeCliente, senhaCliente);
			
			int menulogado = in.readInt();
			
			while(menulogado == 2){
				int opcao = in.readInt();
				switch(opcao){
					case 1:
						out.writeUTF(biblioteca.listarTodosLivros());
						break;
					case 2:
						String tituloSolicitado = in.readUTF();
						out.writeUTF(biblioteca.emprestarLivro(tituloSolicitado, usuarioAtendido));
						break;
					case 3:
						String tituloDevolvido = in.readUTF();
						out.writeUTF(biblioteca.devolverLivro(tituloDevolvido, usuarioAtendido));
						break;
					case 4:
						String tituloAdicionar = in.readUTF();
						int anoPublicacao = in.readInt();
						int codigo = biblioteca.quantidadeLivros();
						Livro l = new Livro(codigo, tituloAdicionar, anoPublicacao);
						out.writeUTF(biblioteca.adicionarLivro(l, usuarioAtendido));
						break;
					case 5:
						String tituloRemover = in.readUTF();
						out.writeUTF(biblioteca.removerLivro(tituloRemover, usuarioAtendido));
						break;
					
				}
				menulogado = in.readInt();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
