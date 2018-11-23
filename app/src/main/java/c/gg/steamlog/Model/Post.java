package c.gg.steamlog.Model;

public class Post {
	
	private long idUsuarioPostagem;
	private Usuario usuario;
	private Postagem postagem;
	
	public Post() {}

	public Post(long idUsuarioPostagem, Usuario usuario, Postagem postagem) {
		super();
		this.idUsuarioPostagem = idUsuarioPostagem;
		this.usuario = usuario;
		this.postagem = postagem;
	}

	public long getIdUsuarioPostagem() {
		return this.idUsuarioPostagem;
	}

	public void setIdUsuarioPostagem(long idUsuarioPostagem) {
		this.idUsuarioPostagem = idUsuarioPostagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	
	
}
