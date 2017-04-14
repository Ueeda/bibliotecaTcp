import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		while(true){
			ServerSocket server = new ServerSocket(3333);
			Biblioteca biblioteca = new Biblioteca();
			while(true){
				Socket socket = server.accept();
				Bibliotecario bibliotecario = new Bibliotecario(socket, biblioteca);
				bibliotecario.start();
			}
		}
	}

}
