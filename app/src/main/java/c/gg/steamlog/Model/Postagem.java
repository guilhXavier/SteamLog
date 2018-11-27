package c.gg.steamlog.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;

public class Postagem implements Serializable {

	private long idPostagem;
	private String tituloPostagem;
	private String descPostagem;
	private Date dataPostagem;
	private ArrayList<ImagemPostagem>listImagens;
	private ArrayList<Comentarios>listComentarios;
	private ArrayList<Post>listPost;

	public Postagem() {}

	public Postagem(long idPostagem,String tituloPostagem, String descPostagem, Date dataPostagem, ArrayList<ImagemPostagem> listImagens,
					ArrayList<Comentarios> listComentarios, ArrayList<Post> listPost) {
		super();
		this.tituloPostagem = tituloPostagem;
		this.idPostagem = idPostagem;
		this.descPostagem = descPostagem;
		this.dataPostagem = dataPostagem;
		this.listImagens = listImagens;
		this.listComentarios = listComentarios;
		this.listPost = listPost;
	}



	public String getTituloPostagem() {
		return tituloPostagem;
	}

	public void setTituloPostagem(String tituloPostagem) {
		this.tituloPostagem = tituloPostagem;
	}

	public long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public String getDescPostagem() {
		return descPostagem;
	}

	public void setDescPostagem(String descPostagem) {
		this.descPostagem = descPostagem;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public ArrayList<ImagemPostagem> getListImagens() {
		return listImagens;
	}

	public void setListImagens(ArrayList<ImagemPostagem> listImagens) {
		this.listImagens = listImagens;
	}

	public ArrayList<Comentarios> getListComentarios() {
		return listComentarios;
	}

	public void setListComentarios(ArrayList<Comentarios> listComentarios) {
		this.listComentarios = listComentarios;
	}

	public ArrayList<Post> getListPost() {
		return listPost;
	}

	public void setListPost(ArrayList<Post> listPost) {
		this.listPost = listPost;
	}

	@Override
	public String toString() {
		return tituloPostagem;
	}
}

