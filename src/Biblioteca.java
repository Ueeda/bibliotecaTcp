import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Biblioteca {
	private List<Livro> livros;
	private List<Usuario> usuarios;
	private HashMap<String, Integer> emprestimos;
	
	public Biblioteca(){
		this.livros = new ArrayList<Livro>();
		this.usuarios = new ArrayList<Usuario>();
		this.emprestimos = new HashMap<String, Integer>();
		this.usuarios.add(new Usuario("Vilmar", usuarios.size(), "1234", 1));
		this.usuarios.add(new Usuario("Rafael", usuarios.size(), "1234", 0));
		this.usuarios.add(new Usuario("Vinicius", usuarios.size(), "1234", 0));
		this.livros.add(new Livro(livros.size(), "Naruto", 2008));
		this.livros.add(new Livro(livros.size(), "CDZ", 2003));
		this.livros.add(new Livro(livros.size(), "Boruto", 2003));
	}
	
	public int quantidadeLivros(){
		return livros.size();
	}
	
	public synchronized String adicionarLivro(Livro livro, int usuarioAtendido){
		Usuario u = usuarios.get(usuarioAtendido);
		if(! u.isAdmin())
			return "Você não pode realizar a operação";
		for(Livro l : livros){
			if(l.getTituloLivro().equals(livro.getTituloLivro())){
				return "Não foi possível realizar a adição do livro";
			}
		}
		livros.add(livro);
		return "Livro adicionado com sucesso";
	}
	
	public synchronized String removerLivro(String titulo, int usuarioAtendido){
		Usuario u = usuarios.get(usuarioAtendido);
		if(! u.isAdmin())
			return "Você não pode realizar a operação";
		int posicaoLivro = this.posicaoLivro(titulo);
		while(isEmprestado(titulo)){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		livros.remove(posicaoLivro);
		return "Livro removido com sucesso";
	}
	
	public int posicaoLivro(String titulo){
		for(int i = 0; i < livros.size(); i++){
			Livro l = livros.get(i);
			if(l.getTituloLivro().equals(titulo))
				return i;
		}
		return -1;
	}
	
	public synchronized String emprestarLivro(String titulo, int idUsuario){
		while(isEmprestado(titulo)){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		emprestimos.put(titulo, idUsuario);
		return "Emprestimo realizado com sucesso";				
	}
	
	public synchronized String devolverLivro(String titulo, int idUsuario){
		for(String t : emprestimos.keySet()){
			int donoEmprestimo = emprestimos.get(t);
			if(t.equals(titulo) && donoEmprestimo == idUsuario){
				emprestimos.remove(titulo, idUsuario);
				notifyAll();
				return "Devolução realizada com sucesso";
			}
		}
		
		return "Não foi possível devolver o livro porque você não é o dono do empréstimo";	
	}
	
	public boolean isEmprestado(String titulo){
		for(String t : emprestimos.keySet()){
			if(t.equals(titulo)){
				return true;
			}
		}
		return false;
	}
	
	public boolean existeUsuario(String nome, String senha){
		for(Usuario u : usuarios){
			if(nome.equals(u.getNomeUsuario()) && senha.equals(u.getSenha()))
				return true;
		}
		return false;
	}
	
	public int posicaoUsuario(String nome, String senha){
		for(int i = 0; i < usuarios.size(); i++){
			Usuario u = usuarios.get(i);
			if(nome.equals(u.getNomeUsuario()) && senha.equals(u.getSenha()))
				return i;
		}
		return -1;
	}	
	
	public String listarTodosLivros(){
		String todosLivros = "";
		for(Livro l :livros){
			todosLivros += "Titulo: " + l.getTituloLivro() + ", Ano de publicação: " + l.getAnoPublicacao() + "\n"; 
		}
		return todosLivros;
	}
}
