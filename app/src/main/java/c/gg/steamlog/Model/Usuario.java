package c.gg.steamlog.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
	private long idUsuario;
	private String email;
	private String nickname;
	private String senha;
	private int steamid;
	private String fotoPerfil;
	private int numJogos;
	private int numConquistas;
	private Imagens imagens;
	private ArrayList<Post>listPost;
	private ArrayList<Ranking>listrankings ; 
	
	
	public Usuario() {
		
	}
	
	public Usuario(long idUsuario, String email, String nickname, String senha, int steamid, String fotoPerfil,int numJogos, int numConquistas, Imagens imagens, ArrayList<Post> listPost, ArrayList<Ranking> listrankings) {
		super();
		this.idUsuario = idUsuario;
		this.email = email;
		this.nickname = nickname;
		this.senha = senha;
		this.steamid = steamid;
		this.fotoPerfil = fotoPerfil;
		this.numJogos = numJogos;
		this.numConquistas = numConquistas;
		this.imagens = imagens;
		this.listPost = listPost;
		this.listrankings = listrankings;
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

	public int getSteamid() {
		return steamid;
	}

	public void setSteamid(int steamid) {
		this.steamid = steamid;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public int getNumJogos() {
		return numJogos;
	}

	public void setNumJogos(int numJogos) {
		this.numJogos = numJogos;
	}

	public int getNumConquistas() {
		return numConquistas;
	}

	public void setNumConquistas(int numConquistas) {
		this.numConquistas = numConquistas;
	}

	public Imagens getImagens() {
		return this.imagens;
	}

	public void setImagens(Imagens imagens) {
		this.imagens = imagens;
	}

	public ArrayList<Post> getListPost() {
		return listPost;
	}

	public void setListPost(ArrayList<Post> listPost) {
		this.listPost = listPost;
	}

	public ArrayList<Ranking> getListrankings() {
		return listrankings;
	}

	public void setListrankings(ArrayList<Ranking> listrankings) {
		this.listrankings = listrankings;
	}
	

}
