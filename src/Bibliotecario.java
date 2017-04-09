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
			
			int opcao = in.readInt();
			
			if(opcao == 1){
				out.writeUTF(biblioteca.listarTodosLivros());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
