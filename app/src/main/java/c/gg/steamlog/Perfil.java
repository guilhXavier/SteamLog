package c.gg.steamlog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
//import android.support.design.widget.NavigationView;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;
import java.net.MalformedURLException;

import c.gg.steamlog.Model.Usuario;

public class Perfil extends AppCompatActivity {

    private DrawerLayout drawerLayoutPerfil;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private ActionBar actionBar;
    private ImageView imvFotoPerfil;
    private TextView tvBemVindo,tvEmail,tvNumeroJogos, tvSteamid;
    private Usuario usuario;
    private Bitmap bm;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        singInAnonymously();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        this.inicializarComponentes();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();


        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        boolean cadastroLogin = getIntent().getBooleanExtra("cadastro/login",false);
        this.actionBar.setTitle(R.string.perfil);
        this.drawerLayoutPerfil.addDrawerListener(this.toggle);
        this.toggle.syncState();
        this.actionBar.setDisplayHomeAsUpEnabled(true);
        Glide.with(Perfil.this)
                .using(new FirebaseImageLoader())
                .load(storageReference)
                .into(imvFotoPerfil);
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

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void singInAnonymously(){
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Perfil.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        // [END_EXCLUDE]
                    }
                });
    }
}
