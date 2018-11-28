package c.gg.steamlog;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import c.gg.steamlog.Model.Post;
import c.gg.steamlog.Model.Postagem;
import c.gg.steamlog.Model.Usuario;
import c.gg.steamlog.Services.SteamLogService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamLogService.BASE_URL_SERVER;

public class FazerPost extends AppCompatActivity {

    private ActionBar actionBar;
    private EditText edTitulo,edDesc;
    private Button btPost;
    private Retrofit retrofitServer;
    private Postagem postagem;
    private Usuario usuario;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_post);
        this.incrementarComponentes();
        usuario =(Usuario) getIntent().getSerializableExtra("usuario");
        this.actionBar.setTitle("FazerPost");
        this.btPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postagem = new Postagem();
                postagem.setTituloPostagem(edTitulo.getText().toString());
                postagem.setDescPostagem(edDesc.getText().toString());
                final SteamLogService service = retrofitServer.create(SteamLogService.class);
                Call<Postagem> fazerpost = service.inserirPostagem(postagem);
                fazerpost.enqueue(new Callback<Postagem>() {
                    @Override
                    public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                        if(!response.isSuccessful()){
                            Log.e("PostResponseFailure:",""+response.code());
                        } else {
                            postagem = response.body();
                            post = new Post();
                            post.setUsuario(usuario);
                            post.setPostagem(postagem);
                            Call<Post> postCall = service.inserirPost(post);
                            postCall.enqueue(new Callback<Post>() {
                                @Override
                                public void onResponse(Call<Post> call, Response<Post> response) {
                                    if(!response.isSuccessful()){
                                        Log.e("PostResponseFailure:",""+response.message());
                                    } else {
                                        Intent intent = new Intent(FazerPost.this,ListaPost.class);
                                        intent.putExtra("usuario",usuario);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onFailure(Call<Post> call, Throwable t) {
                                    Log.e("PostFailure",t.getMessage());

                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<Postagem> call, Throwable t) {
                        Log.e("Failure:",t.getMessage());
                    }
                });
            }
        });

    }

    private void incrementarComponentes(){
        this.actionBar = getSupportActionBar();
        this.edTitulo = findViewById(R.id.ed_titulo_fazerpost);
        this.edDesc = findViewById(R.id.ed_desc_fazerpost);
        this.btPost = findViewById(R.id.bt_postar);

        this.retrofitServer  = new Retrofit.Builder()
                .baseUrl(BASE_URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
