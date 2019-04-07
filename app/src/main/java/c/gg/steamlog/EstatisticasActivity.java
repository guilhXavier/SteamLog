package c.gg.steamlog;

import android.content.Intent;
import android.os.Bundle;;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import c.gg.steamlog.ModelSteam.GetAppList;
import c.gg.steamlog.Services.SteamAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamAPIService.BASE_URL_STEAM_API;

public class EstatisticasActivity extends AppCompatActivity{

    private ActionBar actionBar;

    private Button btPesquisar;
    private AutoCompleteTextView atxtNome;
    private Retrofit retrofitSteam;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);
        this.inicializarComponentes();
        this.actionBar.setTitle("Estatísticas");

        SteamAPIService steamAPIService = retrofitSteam.create(SteamAPIService.class);
        Call<GetAppList> getAppListCall = steamAPIService.appList();
        getAppListCall.enqueue(new Callback<GetAppList>() {
            @Override
            public void onResponse(Call<GetAppList> call, Response<GetAppList> response) {
                final GetAppList getAppListObj = response.body();
                ArrayList<String> nomes = new ArrayList<String>();
                for(int x = 0; x < getAppListObj.getApplist().getApps().size(); x++){
                    nomes.add(getAppListObj.getApplist().getApps().get(x).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(EstatisticasActivity.this, android.R.layout.simple_dropdown_item_1line, nomes);
                atxtNome.setAdapter(adapter);

                EstatisticasActivity.this.btPesquisar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(atxtNome.getText()!=null){
                            Intent myIntent = new Intent(EstatisticasActivity.this, JogoActivity.class);
                            Map<String, String> mapaNomesAppid = new HashMap<String, String>();
                            for(int x = 0; x < getAppListObj.getApplist().getApps().size(); x++){
                                mapaNomesAppid.put(getAppListObj.getApplist().getApps().get(x).getName(), getAppListObj.getApplist().getApps().get(x).getAppid());
                            }
                            String appid = mapaNomesAppid.get(atxtNome.getText().toString());


                            myIntent.putExtra("appid", Long.parseLong(appid));
                            startActivity(myIntent);
                        }else{
                            Toast.makeText(EstatisticasActivity.this,"Insira o nome, mi amigo",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<GetAppList> call, Throwable t) {

            }
        });




    }

    private void inicializarComponentes() {

        this.btPesquisar = findViewById(R.id.bt_pesquisar);
        this.atxtNome = findViewById(R.id.atxt_app_nome);
        this.actionBar = getSupportActionBar();
        this.atxtNome = findViewById(R.id.atxt_app_nome);
        this.retrofitSteam = new Retrofit.Builder()
                .baseUrl(BASE_URL_STEAM_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }



}
