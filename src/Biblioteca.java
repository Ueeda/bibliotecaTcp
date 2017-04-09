import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Biblioteca {
	private List<Livro> livros;
	private List<Usuario> usuarios;
	private HashMap<String, Integer> emprestimos;
	
	public Biblioteca(){
		this.livros = new ArrayList<Livro>();
		this.usuarios = new ArrayList<Usuario>();
		this.emprestimos = new HashMap<String, Integer>();
		this.usuarios.add(new Usuario("Vilmar", usuarios.size(), "1234", 1));
		this.usuarios.add(new Usuario("Rafael", usuarios.size(), "1234", 1));
		this.livros.add(new Livro(livros.size(), "Naruto", 2008));
		this.livros.add(new Livro(livros.size(), "Cavaleiros do Zodíaco", 2003));
	}
	
	public synchronized String adicionarLivro(Livro livro){
		for(Livro l : livros){
			if(l.getTituloLivro().equals(livro.getTituloLivro())){
				return "Não foi possível realizar a adição do livro";
			}
		}
		livros.add(livro);
		emprestimos.put(livro.getTituloLivro(), 0);
		return "Livro adicionado com sucesso";
	}
	
	public synchronized String removerLivro(String titulo){
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
	
	public synchronized int posicaoLivro(String titulo){
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
		for(String t : emprestimos.keySet()){
			int donoEmprestimo = emprestimos.get(t);
			if(t.equals(titulo) && donoEmprestimo != 0){
				emprestimos.put(titulo, idUsuario);
				return "Emprestimo realizado com sucesso";
			}
		}
		return "Não foi possível realizar o emprestimo, pois alguém já emprestou o livro";		
	}
	
	public synchronized String devolverLivro(String titulo, int idUsuario){
		for(String t : emprestimos.keySet()){
			int donoEmprestimo = emprestimos.get(t);
			if(t.equals(titulo) && donoEmprestimo == 0){
				emprestimos.put(titulo, idUsuario);
				return "Devolução realizada com sucesso";
			}
		}
		notifyAll();
		return "Não foi possível devolver o livro porque você não é o dono do empréstimo";	
	}
	
	public synchronized boolean isEmprestado(String titulo){
		for(String t : emprestimos.keySet()){
			int donoEmprestimo = emprestimos.get(t);
			if(t.equals(titulo) && donoEmprestimo != 0){
				return true;
			}
		}
		return false;
	}
	
	public synchronized boolean existeUsuario(String nome, String senha){
		for(Usuario u : usuarios){
			if(nome.equals(u.getNomeUsuario()) && senha.equals(u.getSenha()))
				return true;
		}
		return false;
	}
	
	public synchronized String listarTodosLivros(){
		String todosLivros = "";
		for(Livro l :livros){
			todosLivros += "Titulo: " + l.getTituloLivro() + ", Ano de publicação: " + l.getAnoPublicacao() + "\n"; 
		}
		return todosLivros;
	}
}
