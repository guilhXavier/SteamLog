package c.gg.steamlog;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import c.gg.steamlog.Model.Post;
import c.gg.steamlog.Model.Postagem;
import c.gg.steamlog.Services.SteamLogService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamLogService.BASE_URL_SERVER;

public class VerPost extends AppCompatActivity {

    private ActionBar actionBar;
    private TextView tvTitulo,tvDesc,tvAutor;
    private ProgressBar progressBar;
    private Retrofit retrofitServer;
    private Postagem postagem;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_post);
        this.incrementarComponentes();
        postagem = (Postagem) getIntent().getSerializableExtra("postagem");
        actionBar.setTitle("Post");

        SteamLogService service = retrofitServer.create(SteamLogService.class);

        Call<Post> buscarAutor =  service.buscarUsuarioPost(postagem.getIdPostagem());
        buscarAutor.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    Log.e("ResponseFailure:",""+response.code());
                }else {
                    post = response.body();
                    tvTitulo.setText(postagem.getTituloPostagem());
                    tvDesc.setText(postagem.getDescPostagem());
                    tvAutor.setText(post.getUsuario().getNickname());
                    setVisibility();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("Failure:",t.getMessage());
            }
        });

    }


    private void incrementarComponentes(){
        this.actionBar = getSupportActionBar();
        this.tvTitulo = findViewById(R.id.tv_titulo_verpost);
        this.tvDesc = findViewById(R.id.tv_desc_verpost);
        this.tvAutor = findViewById(R.id.tv_autor_verpost);
        this.progressBar = findViewById(R.id.progressBar_fazerpost);
        this.retrofitServer = new Retrofit.Builder()
                .baseUrl(BASE_URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void setVisibility(){
        this.tvTitulo.setVisibility(View.VISIBLE);
        this.tvDesc.setVisibility(View.VISIBLE);
        this.tvAutor.setVisibility(View.VISIBLE);
        this.progressBar.setVisibility(View.INVISIBLE);
    }
}
