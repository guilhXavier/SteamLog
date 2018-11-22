package c.gg.steamlog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

import c.gg.steamlog.Model.Usuario;
import c.gg.steamlog.Services.SteamLogService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamLogService.BASE_URL;

public class Home extends AppCompatActivity {

    private Button btIr;
    private TextView txtTeste;

    public void inicializador() {
        this.btIr = findViewById(R.id.bt_ir);
        this.txtTeste = findViewById(R.id.txt_teste);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        inicializador();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        final SteamLogService SteamLogService = retrofit.create(SteamLogService.class);

        Usuario usuario = new Usuario();
        usuario.setEmail("email");
        usuario.setNickname("busato");
        usuario.setSenha("1234");
        usuario.setSteamid(1223244);
        usuario.setFotoPerfil("nicol bolas");
        usuario.setNumJogos(10);
        usuario.setNumConquistas(14);



    }
}
