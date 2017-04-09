
public class Livro {
	private int codigoLivro;
	private String tituloLivro;
	private int anoPublicacao;
	private boolean emprestado;
	private int codigoUsuario;
	
	public Livro(int cod, String titulo, int anoPublicacao){
		this.codigoLivro = cod;
		this.tituloLivro = titulo;
		this.anoPublicacao = anoPublicacao;
		this.emprestado = false;
		this.setCodigoUsuario(0);
	}
	
	// Gets e Sets
	public int getCodigoLivro() {
		return codigoLivro;
	}
	
	public void setCodigoLivro(int codigoLivro) {
		this.codigoLivro = codigoLivro;
	}
	
	public String getTituloLivro() {
		return tituloLivro;
	}
	
	public void setTituloLivro(String tituloLivro) {
		this.tituloLivro = tituloLivro;
	}
	
	public int getAnoPublicacao() {
		return anoPublicacao;
	}
	
	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	
	public boolean isEmprestado() {
		return emprestado;
	}
	
	public void setEmprestado(boolean emprestado) {
		this.emprestado = emprestado;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
}
