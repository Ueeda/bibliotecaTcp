
public class Usuario {
	private String nomeUsuario;
	private int codigoUsuario;
	private String senha;
	private int tipoUsuario;
	
	public Usuario(String nome, int codigo, String senha, int tipoUsuario){
		this.nomeUsuario = nome;
		this.codigoUsuario = codigo;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}
	
	public boolean isAdmin(){
		return this.tipoUsuario == 1;
	}
	
	// Gets e Sets
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public int getCodigoUsuario() {
		return codigoUsuario;
	}
	
	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public int getTipoUsuario() {
		return tipoUsuario;
	}
	
	public void setTipoUsuario(byte tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
