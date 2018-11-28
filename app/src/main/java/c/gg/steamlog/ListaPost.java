package c.gg.steamlog;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import c.gg.steamlog.Model.Postagem;
import c.gg.steamlog.Model.Usuario;
import c.gg.steamlog.Services.SteamLogService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamLogService.BASE_URL_SERVER;

public class ListaPost extends AppCompatActivity {

    private ActionBar actionBar;
    private ListView lvPost;
    private FloatingActionButton flButoon;
    private ArrayList <Postagem> listPostagens;
    private Retrofit retrofitServer;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_post);
        this.incrementarComponentes();
        this.actionBar.setTitle("ListaPost");
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        SteamLogService service = retrofitServer.create(SteamLogService.class);
        Call<ArrayList<Postagem>> todasPostagens = service.listaTodasPostagens();
        todasPostagens.enqueue(new Callback<ArrayList<Postagem>>() {
            @Override
            public void onResponse(Call<ArrayList<Postagem>> call, Response<ArrayList<Postagem>> response) {
                if(!response.isSuccessful()){
                    Log.e("ResponseErro:","Error: "+response.code());
                } else {
                    listPostagens = response.body();
                    ArrayAdapter<Postagem> adapter = new ArrayAdapter<Postagem>(ListaPost.this,android.R.layout.simple_list_item_1,listPostagens);
                    lvPost.setAdapter(adapter);
                    lvPost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            Intent intent  = new Intent(ListaPost.this,VerPost.class);
                            intent.putExtra("postagem",listPostagens.get(position));
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Postagem>> call, Throwable t) {
                Log.e("Failure:",t.getMessage()) ;
            }
        });

            this.flButoon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ListaPost.this,FazerPost.class);
                    intent.putExtra("usuario",usuario);
                    startActivity(intent);
                }
            });

    }

    private void incrementarComponentes(){
        this.actionBar = getSupportActionBar();
        this.lvPost = findViewById(R.id.lv_post_listapost);
        this.flButoon = findViewById(R.id.flbt_addPost);
        this.retrofitServer = new Retrofit.Builder()
                .baseUrl(BASE_URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
