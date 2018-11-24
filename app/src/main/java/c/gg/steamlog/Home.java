package c.gg.steamlog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;




import c.gg.steamlog.Model.Usuario;
import c.gg.steamlog.ModelSteam.GetAppDetailsRequest;
import c.gg.steamlog.Services.SteamSpyService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamSpyService.BASE_URL_STEAM_SPY;

public class Home extends AppCompatActivity {

    private EditText edNickname, edSenha;
    private Button btLogar;
    private TextView tvRegistrar;
    private Retrofit retrofit;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.inicializarComponentes();

//        this.btLogar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SteamLogService service = retrofit.create(SteamLogService.class);
//                Call<Usuario>buscar = service.buscarPorLoginESenha(edNickname.getText().toString(),edSenha.getText().toString());
//                buscar.enqueue(new Callback<Usuario>() {
//                    @Override
//                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
//                        if(!response.isSuccessful()){
//                            Log.e("ResponseError:","Erro:"+response.code());
//                        } else{
//                            usuario = response.body();
//                            Intent intent = new Intent(Home.this,Perfil.class);
//                            intent.putExtra("usuario",usuario);
//                            startActivity(intent);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Usuario> call, Throwable t) {
//                        Log.e("Failure:",t.getMessage());
//                    }
//                });
//            }
//        });
//
//        this.tvRegistrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Home.this,Cadastro.class);
//                startActivity(intent);
//            }
//        });

        String x = null;
//        SteamSpyService steamSpyService = retrofit.create(SteamSpyService.class);
//
//        Call<GetAppDetailsRequest> getAppDetailsCall = steamSpyService.getAppDetails("appdetails", 730);
//
//        getAppDetailsCall.enqueue(new Callback<GetAppDetailsRequest>() {
//            @Override
//            public void onResponse(Call<GetAppDetailsRequest> call, Response<GetAppDetailsRequest> response) {
//                GetAppDetailsRequest getAppDetailsObj = response.body();
//                System.out.println(getAppDetailsObj.getDeveloper());
//            }
//
//            @Override
//            public void onFailure(Call<GetAppDetailsRequest> call, Throwable t) {
//                System.out.println("nao deu");
//            }
//        });




    }


    private void inicializarComponentes() {
        this.edNickname = findViewById(R.id.ed_nickname_home);
        this.edSenha = findViewById(R.id.ed_senha_home);
        this.btLogar = findViewById(R.id.bt_logar_home);
        this.tvRegistrar = findViewById(R.id.tv_registar_home);
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_STEAM_SPY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
