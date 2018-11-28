package c.gg.steamlog;

import android.content.Intent;
import android.net.Uri;
//import android.support.design.widget.NavigationView;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import c.gg.steamlog.Model.Usuario;

public class Perfil extends AppCompatActivity {

    private DrawerLayout drawerLayoutPerfil;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private ActionBar actionBar;
    private ImageView imvFotoPerfil;
    private TextView tvBemVindo,tvEmail,tvNumeroJogos, tvSteamid;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        this.inicializarComponentes();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        boolean cadastroLogin = getIntent().getBooleanExtra("cadastro/login",false);
        this.actionBar.setTitle(R.string.perfil);
        this.drawerLayoutPerfil.addDrawerListener(this.toggle);
        this.toggle.syncState();
        this.actionBar.setDisplayHomeAsUpEnabled(true);
        if(cadastroLogin){
         Uri fotoPerfil = Uri.parse(usuario.getImagens().getArquivoImagem());
         this.imvFotoPerfil.setImageURI(fotoPerfil);
        }
        this.tvBemVindo.setText("Bem Vindo,"+usuario.getNickname());
        this.tvEmail.setText("Email:"+usuario.getEmail());
        this.tvNumeroJogos.setText("Numero de jogos:"+usuario.getNumJogos());
        this.tvSteamid.setText("SteamID:"+usuario.getSteamid());

        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){

                    case R.id.nav_estatisticas:
                         intent = new Intent(Perfil.this,Estatisticas.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_ranking:
                        intent = new Intent(Perfil.this,Ranking.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_post:
                        intent = new Intent(Perfil.this, ListaPost.class);
                        intent.putExtra("usuario",usuario);
                        startActivity(intent);
                        break;
                    case R.id.nav_amigos:
                        intent = new Intent(Perfil.this, Amigos.class);
                        intent.putExtra("usuario", usuario);
                        startActivity(intent);
                        break;
                }

                return false;
            }
        });


    }

    private void inicializarComponentes(){
        this.drawerLayoutPerfil = findViewById(R.id.drawerlayout_perfill);
        this.toggle = new ActionBarDrawerToggle(this,this.drawerLayoutPerfil,R.string.open,R.string.close);
        this.navigationView = findViewById(R.id.nv_layout_perfil);
        this.actionBar = getSupportActionBar();
        this.imvFotoPerfil = findViewById(R.id.imv_foto_perfil);
        this.tvBemVindo = findViewById(R.id.tv_bemvindo_perfil);
        this.tvEmail = findViewById(R.id.tv_email);
        this.tvNumeroJogos = findViewById(R.id.tv_numerojogos_perfil);
        this.tvSteamid = findViewById(R.id.tv_steamid_perfil);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(this.toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
