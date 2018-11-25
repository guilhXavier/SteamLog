package c.gg.steamlog;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import c.gg.steamlog.Model.Usuario;
import c.gg.steamlog.Services.SteamLogService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamLogService.BASE_URL_SERVER;

public class Ranking extends AppCompatActivity {

    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggleButton;
    private NavigationView navigationView;
    private ListView lvRanking;
    private ArrayList<Usuario>listUsuario;
    private Retrofit retrofitServer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        this.inicializarComponentes();
        this.actionBar.setTitle("Ranking");
        this.drawerLayout.addDrawerListener(this.toggleButton);
        this.toggleButton.syncState();
        this.actionBar.setDisplayHomeAsUpEnabled(true);

        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {

                    case R.id.nav_estatisticas:
                        intent = new Intent(Ranking.this, Estatisticas.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_perfil:
                        intent = new Intent(Ranking.this, Perfil.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }

        });


        SteamLogService service = retrofitServer.create(SteamLogService.class);
        Call<ArrayList<Usuario>> ranking = service.buscarRanking();
        ranking.enqueue(new Callback<ArrayList<Usuario>>() {
            @Override
            public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
                if(!response.isSuccessful()){
                    Log.e("ResponseErro:","Error: "+response.code());
                } else {
                    listUsuario = response.body();
                    ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(Ranking.this,android.R.layout.simple_list_item_1,listUsuario);
                    lvRanking.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {
                Log.e("Failure:",t.getMessage()) ;
            }
        });

    }

    private void inicializarComponentes(){
        this.lvRanking = findViewById(R.id.lv_ranking_ranking);
        this.listUsuario  = new ArrayList<Usuario>();
        this.actionBar = getSupportActionBar();
        this.retrofitServer = new Retrofit.Builder()
                .baseUrl(BASE_URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.drawerLayout = findViewById(R.id.drawerlayout_ranking);
        this.toggleButton = new ActionBarDrawerToggle(this,this.drawerLayout,R.string.open,R.string.close);
        this.navigationView = findViewById(R.id.nv_layout_ranking);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(this.toggleButton.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}