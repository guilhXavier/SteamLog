package c.gg.steamlog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;




import c.gg.steamlog.Model.Usuario;
import c.gg.steamlog.ModelSteam.GetAppDetailsRequest;
import c.gg.steamlog.ModelSteam.GetNumberOfConcurrentPlayers;
import c.gg.steamlog.ModelSteam.GetOwnedGames;
import c.gg.steamlog.Services.SteamAPIService;
import c.gg.steamlog.Services.SteamLogService;
import c.gg.steamlog.Services.SteamSpyService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamAPIService.BASE_URL_STEAM_API;
import static c.gg.steamlog.Services.SteamLogService.BASE_URL_SERVER;
import static c.gg.steamlog.Services.SteamSpyService.BASE_URL_STEAM_SPY;

public class Home extends AppCompatActivity {

    private EditText edNickname, edSenha;
    private Button btLogar;
    private TextView tvRegistrar;
    private Retrofit retrofitServer, retrofitSteam;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.inicializarComponentes();

        this.btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final SteamAPIService apiService = retrofitSteam.create(SteamAPIService.class);
                final SteamLogService service = retrofitServer.create(SteamLogService.class);
                Call<Usuario>buscar = service.buscarPorLoginESenha(edNickname.getText().toString(),edSenha.getText().toString());
                buscar.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if(!response.isSuccessful()){
                            Log.e("ResponseError:","Erro:"+response.code());
                        } else{
                            usuario = response.body();
                            Call<GetOwnedGames> gamesCount = apiService.ownedGames("802A0913D1CB0336DFE046F0294F4B04",Long.toString(usuario.getSteamid()));
                            gamesCount.enqueue(new Callback<GetOwnedGames>() {
                                @Override
                                public void onResponse(Call<GetOwnedGames> call, Response<GetOwnedGames> response) {
                                    if(!response.isSuccessful()){
                                        Log.e("SteamResponseFailure:","Erro:"+response.code());
                                    } else{
                                        GetOwnedGames games = response.body();
                                        usuario.setNumJogos(Integer.parseInt(games.getResponse().getGame_Count()));
                                        Call<Void> editar = service.editarUsuario(usuario);
                                        editar.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                if(!response.isSuccessful()){
                                                    Log.e("EditarResponseFailure:","Erro:"+response.code());
                                                }else {
                                                    Intent intent = new Intent(Home.this,Perfil.class);
                                                    intent.putExtra("usuario",usuario);
                                                    startActivity(intent);
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                Log.e("EditarFailure:",t.getMessage());
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onFailure(Call<GetOwnedGames> call, Throwable t) {
                                    Log.e("SteamFailure",t.getMessage());
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Log.e("Failure:",t.getMessage());
                    }
                });
            }
        });

        this.tvRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Cadastro.class);
                startActivity(intent);
            }
        });






    }


    private void inicializarComponentes() {
        this.edNickname = findViewById(R.id.ed_nickname_home);
        this.edSenha = findViewById(R.id.ed_senha_home);
        this.btLogar = findViewById(R.id.bt_logar_home);
        this.tvRegistrar = findViewById(R.id.tv_registar_home);
        this.retrofitServer = new Retrofit.Builder()
                .baseUrl(BASE_URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.retrofitSteam = new Retrofit.Builder()
                .baseUrl(BASE_URL_STEAM_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
