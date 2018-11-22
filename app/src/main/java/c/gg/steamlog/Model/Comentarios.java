package c.gg.steamlog.Model;

import java.sql.Date;

public class Comentarios {
	
	private long idComentario;
	private String corpoComentario;
	private Date dataComentario;
	private Postagem postagem;
	
	public Comentarios() {}

	public Comentarios(long idComentario, String corpoComentario, Date dataComentario, Postagem postagem) {
		super();
		this.idComentario = idComentario;
		this.corpoComentario = corpoComentario;
		this.dataComentario = dataComentario;
		this.postagem = postagem;
	}

	public long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(long idComentario) {
		this.idComentario = idComentario;
	}

	public String getCorpoComentario() {
		return corpoComentario;
	}

	public void setCorpoComentario(String corpoComentario) {
		this.corpoComentario = corpoComentario;
	}

	public Date getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(Date dataComentario) {
		this.dataComentario = dataComentario;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}


	
	
	
	
}
