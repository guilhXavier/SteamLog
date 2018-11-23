package c.gg.steamlog;

import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import c.gg.steamlog.Model.Usuario;

public class Perfil extends AppCompatActivity {

    private ActionBar actionBar;
    private ImageView imvFotoPerfil;
    private TextView tvBemVindo,tvEmail,tvNumeroJogos,tvNumeroConquistas,tvSteamid;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        this.inicializarComponentes();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        this.actionBar.setTitle(R.string.perfil);
        Uri fotoPerfil = Uri.parse(usuario.getImagens().getArquivoImagem().toString());
        this.imvFotoPerfil.setImageURI(fotoPerfil);
        this.tvBemVindo.setText("Bem Vindo,"+usuario.getNickname());
        this.tvEmail.setText("Emai:"+usuario.getEmail());
        this.tvNumeroJogos.setText("Numero de jogos:"+usuario.getNumJogos());
        this.tvNumeroConquistas.setText("Numero de Conquistas:"+usuario.getNumConquistas());
        this.tvSteamid.setText("SteamID:"+usuario.getSteamid());


    }

    private void inicializarComponentes(){
        this.actionBar = getSupportActionBar();
        this.imvFotoPerfil = findViewById(R.id.imv_foto_perfil);
        this.tvBemVindo = findViewById(R.id.tv_bemvindo_perfil);
        this.tvEmail = findViewById(R.id.tv_email);
        this.tvNumeroJogos = findViewById(R.id.tv_numerojogos_perfil);
        this.tvNumeroConquistas = findViewById(R.id.tv_numeroconquistas_perfil);
        this.tvSteamid = findViewById(R.id.tv_steamid_perfil);
    }
}
