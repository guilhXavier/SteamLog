package c.gg.steamlog.Model;

public class ImagemPostagem {
	
	private long idImagemPost;
	private Imagens imagens;
	private Postagem postagem;
	
	public ImagemPostagem() {}

	public ImagemPostagem(long idImagemPost, Imagens imagens, Postagem postagem) {
		super();
		this.idImagemPost = idImagemPost;
		this.imagens = imagens;
		this.postagem = postagem;
	}

	public long getIdImagemPost() {
		return idImagemPost;
	}

	public void setIdImagemPost(long idImagemPost) {
		this.idImagemPost = idImagemPost;
	}

	public Imagens getImagens() {
		return imagens;
	}

	public void setImagens(Imagens imagens) {
		this.imagens = imagens;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}
	
	
	
	


	
	
	
}
