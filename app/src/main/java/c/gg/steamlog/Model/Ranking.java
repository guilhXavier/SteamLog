package c.gg.steamlog.Model;

import java.util.Date;

public class Ranking {
	
	private long idRanking;
	private short tipo;
	private Date dataAtualizacao;
	private Usuario usuario;
	
	public Ranking() {}

	public Ranking(long idRanking, short tipo, Date dataAtualizacao, Usuario usuario) {
		super();
		this.idRanking = idRanking;
		this.tipo = tipo;
		this.dataAtualizacao = dataAtualizacao;
		this.usuario = usuario;
	}

	public long getIdRanking() {
		return idRanking;
	}

	public void setIdRanking(long idRanking) {
		this.idRanking = idRanking;
	}

	public short getTipo() {
		return tipo;
	}

	public void setTipo(short tipo) {
		this.tipo = tipo;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
	
	