package c.gg.steamlog.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
	private long idUsuario;
	private String email;
	private String nickname;
	private String senha;
	private long steamid;
	private int numJogos;
	private String refFotoPerfil;
	private ArrayList<Post>listPost;
	
	
	public Usuario() {
		
	}
	
	public Usuario(long idUsuario, String email, String nickname, String senha, long steamid, int numJogos, int numConquistas, String refFotoPerfil, ArrayList<Post> listPost, ArrayList<Ranking> listrankings) {
		super();
		this.idUsuario = idUsuario;
		this.email = email;
		this.nickname = nickname;
		this.senha = senha;
		this.steamid = steamid;
		this.numJogos = numJogos;
		this.refFotoPerfil = refFotoPerfil;
		this.listPost = listPost;
	}

	
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public long getSteamid() {
		return steamid;
	}

	public void setSteamid(long steamid) {
		this.steamid = steamid;
	}

	public int getNumJogos() {
		return numJogos;
	}

	public void setNumJogos(int numJogos) {
		this.numJogos = numJogos;
	}

	public String getRefFotoPerfil() {
		return this.refFotoPerfil;
	}

	public void setRefFotoPerfil(String refFotoPerfil) {
		this.refFotoPerfil = refFotoPerfil;
	}

	public ArrayList<Post> getListPost() {
		return listPost;
	}

	public void setListPost(ArrayList<Post> listPost) {
		this.listPost = listPost;
	}

	@Override
	public String toString() {
		return numJogos +"| "+ nickname ;
	}
}
