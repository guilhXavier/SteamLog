package c.gg.steamlog.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Imagens implements Serializable {
	
	private long idImagem;
	private short tipoImagem;
	private String arquivoImagem;
	private ArrayList<Usuario>listusuario;
	private ArrayList<ImagemPostagem>listImagemPostagem;
	
	public Imagens() {}
	
	public Imagens(long idImagem, short tipoImagem, String arquivoImagem, ArrayList<ImagemPostagem> listImagemPostagem, ArrayList<Usuario> listusuario) {
		super();
		this.idImagem = idImagem;
		this.tipoImagem = tipoImagem;
		this.arquivoImagem = arquivoImagem;
		this.listusuario = listusuario;
		this.listImagemPostagem = listImagemPostagem;
	}
	
	public long getIdImagem() {
		return idImagem;
	}

	public void setIdImagem(long idImagem) {
		this.idImagem = idImagem;
	}

	public short getTipoImagem() {
		return this.tipoImagem ;
	}

	public void setTipoImagem(short tipoImagem) {
		this.tipoImagem = tipoImagem;
	}

	public String getArquivoImagem() {
		return this.arquivoImagem;
	}

	public void setArquivoImagem(String arquivoImagem) {
		this.arquivoImagem = arquivoImagem;
	}

	public ArrayList<Usuario> getListusuario() {
		return listusuario;
	}

	public void setListusuario(ArrayList<Usuario> listusuario) {
		this.listusuario = listusuario;
	}

	public ArrayList<ImagemPostagem> getListImagemPostagem() {
		return listImagemPostagem;
	}

	public void setListImagemPostagem(ArrayList<ImagemPostagem> listImagemPostagem) {
		this.listImagemPostagem = listImagemPostagem;
	}
	
}
