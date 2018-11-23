package c.gg.steamlog.Services;

import java.util.List;

import c.gg.steamlog.Model.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface SteamLogService {

    //url base
    public final String BASE_URL = "http://192.168.1.104:8080/";

    //inicio dos metodos Usuario
    @POST("usuario/")
    Call<Usuario> cadastraUsuario(@Body Usuario usuario);

    @PUT("usuario/")
    Call<Void> editarUsuario(@Body Usuario usuario);

    @DELETE("usuario/")
    Call<Void> excluirUsuario(@Query("idUsuario") long id);

    @GET("usuario/")
    Call<List<Usuario>> listaTodosUsuarios();

    @GET("usuario/")
    Call<Usuario> buscarUsuarioPorId(@Query("idUsuario") long id);

    @GET("usuario/{nickname}/{senha}")
    Call<Usuario> buscarPorLoginESenha(@Path("nickname") String nickname,@Path("senha") String senha);
    //fim dos metodos do usuario

    //inicio dos metodos das imagens
    @POST("imagens/")
    Call<Imagens> inserirImagem(@Body Imagens imagens);

    @DELETE("imagens/")
    Call<Void> excluirImagem(@Query("idImagem") long id);

    @GET("imagens/")
    Call<List<Imagens>> listaTodasImagens();

    @GET("imagens/")
    Call<Imagens> buscarImagemPorId(@Query("idImagem") long id);
    //fim dos metodos das imagens


}
