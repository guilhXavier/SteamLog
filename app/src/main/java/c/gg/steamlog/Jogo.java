package c.gg.steamlog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import c.gg.steamlog.ModelSteam.GetAppDetailsRequest;
import c.gg.steamlog.ModelSteam.GetNumberOfConcurrentPlayers;
import c.gg.steamlog.Services.SteamAPIService;
import c.gg.steamlog.Services.SteamSpyService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamAPIService.BASE_URL_STEAM_API;
import static c.gg.steamlog.Services.SteamLogService.BASE_URL_SERVER;
import static c.gg.steamlog.Services.SteamSpyService.BASE_URL_STEAM_SPY;

public class Jogo extends AppCompatActivity {

    private TextView edNome, edAppid, edDeveloperPublisher, edPositiveNegativeUserScore, edPriceInitialPrice, edLanguages, edGenre, edCCUYesterdayToday;
    private Retrofit retrofitSteamSpy, retrofitSteamAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        inicializarComponentes();
        final Intent meuIntent = getIntent();

        SteamSpyService steamSpyService = retrofitSteamSpy.create(SteamSpyService.class);
        final SteamAPIService steamAPIService = retrofitSteamAPI.create(SteamAPIService.class);

        Call<GetAppDetailsRequest> getAppDetailsCall = steamSpyService.getAppDetails("appdetails", meuIntent.getLongExtra("appid", 730));
        getAppDetailsCall.enqueue(new Callback<GetAppDetailsRequest>() {
            @Override
            public void onResponse(Call<GetAppDetailsRequest> call, Response<GetAppDetailsRequest> response) {
                final GetAppDetailsRequest getAppDetailsObj = response.body();
                edNome.setText(getAppDetailsObj.getName());
                edAppid.setText(getAppDetailsObj.getAppid()+"");
                edDeveloperPublisher.setText("Desenvolvedora: "+ getAppDetailsObj.getDeveloper() + " / Publisher: " + getAppDetailsObj.getPublisher());
                edPositiveNegativeUserScore.setText("Pontuação dos Usuários: " + getAppDetailsObj.getUserscore() + " / Reviews Positivas: " + getAppDetailsObj.getPositiveReview() + " / Reviews Negativas: " + getAppDetailsObj.getNegativeReview());
                edPriceInitialPrice.setText("Preço Inicial em US$: " + algoritmoDoCaralho(getAppDetailsObj.getInitialPrice()) + " / Preço Atual em US$: " + algoritmoDoCaralho(getAppDetailsObj.getPrice()) + " / Desconto: " + getAppDetailsObj.getDiscount());
                edLanguages.setText("Línguas: " + getAppDetailsObj.getLanguages());
                edGenre.setText("Gênero: " + getAppDetailsObj.getGenre());

                Call<GetNumberOfConcurrentPlayers> getNumberOfConcurrentPlayersCall = steamAPIService.getCCU("F8FC0E4F7BA600F9FCEC8D77F5801D83", getAppDetailsObj.getAppid()+"");
                getNumberOfConcurrentPlayersCall.enqueue(new Callback<GetNumberOfConcurrentPlayers>() {
                    @Override
                    public void onResponse(Call<GetNumberOfConcurrentPlayers> call, Response<GetNumberOfConcurrentPlayers> response) {
                        GetNumberOfConcurrentPlayers getNumberOfConcurrentPlayers = response.body();
                        edCCUYesterdayToday.setText("Ontem: " + getAppDetailsObj.getConcurrentUsers() + " / Agora: " + getNumberOfConcurrentPlayers.getResponse().getPlayer_count());
                    }

                    @Override
                    public void onFailure(Call<GetNumberOfConcurrentPlayers> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<GetAppDetailsRequest> call, Throwable t) {

            }
        });

    }

    public String algoritmoDoCaralho(String s){
        String x = s.substring(0, s.length()-2) + "," + s.substring(s.length()-2, s.length());
        return x;
    }


    private void inicializarComponentes() {
        this.edNome = findViewById(R.id.txt_nome);
        this.edAppid = findViewById(R.id.txt_appid);
        this.edDeveloperPublisher = findViewById(R.id.txt_developer_publisher);
        this.edPositiveNegativeUserScore = findViewById(R.id.txt_positive_negative_userscore);
        this.edCCUYesterdayToday = findViewById(R.id.txt_CCU_yesterday_today);
        this.edPriceInitialPrice = findViewById(R.id.txt_price_initial_price);
        this.edLanguages = findViewById(R.id.txt_languages);
        this.edGenre = findViewById(R.id.txt_genre);
        this.retrofitSteamSpy = new Retrofit.Builder()
                .baseUrl(BASE_URL_STEAM_SPY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.retrofitSteamAPI = new Retrofit.Builder()
                .baseUrl(BASE_URL_STEAM_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
