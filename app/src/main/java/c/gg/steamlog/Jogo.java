package c.gg.steamlog;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

    private ActionBar actionBar;
    private TextView edNome, edAppid, edDeveloper, edPublisher, edPositiveReviews, edNegativeReviews, edUserScore, edPrice, edInitialPrice, edDiscount, edLanguages, edGenre, edCCUYesterday, edCCUToday;
//    private ProgressBar progressBarJogo;
    private Retrofit retrofitSteamSpy, retrofitSteamAPI, retrofitServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        inicializarComponentes();
        actionBar.setTitle("Jogo");
        final Intent meuIntent = getIntent();

        SteamSpyService steamSpyService = retrofitSteamSpy.create(SteamSpyService.class);
        final SteamAPIService steamAPIService = retrofitSteamAPI.create(SteamAPIService.class);

        final Call<GetAppDetailsRequest> getAppDetailsCall = steamSpyService.getAppDetails("appdetails", meuIntent.getLongExtra("appid",730));
        getAppDetailsCall.enqueue(new Callback<GetAppDetailsRequest>() {
            @Override
            public void onResponse(Call<GetAppDetailsRequest> call, Response<GetAppDetailsRequest> response) {
                if(!response.isSuccessful()){
                    Log.e("ResponseErro:","Erro:"+response.code());
                } else {
//                    setVisibiity();
                    final GetAppDetailsRequest getAppDetailsObj = response.body();
                    edNome.setText(getAppDetailsObj.getName());
                    edAppid.setText(getAppDetailsObj.getAppid()+"");
                    edDeveloper.setText("Desenvolvedora: "+ getAppDetailsObj.getDeveloper());
                    edPublisher.setText("Publisher: " + getAppDetailsObj.getPublisher());
                    edUserScore.setText("Pontuação dos Usuários: " + getAppDetailsObj.getUserscore()+"%");
                    edPositiveReviews.setText("Reviews Positivas: " + getAppDetailsObj.getPositiveReview());
                    edNegativeReviews.setText("Reviews Negativas: " + getAppDetailsObj.getNegativeReview());
                    if(!getAppDetailsObj.getInitialPrice().equals("0") && !getAppDetailsObj.getPrice().equals("0")){
                        edInitialPrice.setText("Preço Inicial em US$: " + algoritmoDoCaralho(getAppDetailsObj.getInitialPrice()));
                        edPrice.setText("Preço Atual em US$: " + algoritmoDoCaralho(getAppDetailsObj.getPrice()));
                        edDiscount.setText("Desconto: " + getAppDetailsObj.getDiscount()+"%");
                    }else{
                        edInitialPrice.setText("Preço Inicial em US$: FREE");
                        edPrice.setText("\nPreço Atual em US$: FREE");
                        edDiscount.setText("\nDesconto: " + getAppDetailsObj.getDiscount() + "%");
                    }
                    edLanguages.setText("Línguas: " + getAppDetailsObj.getLanguages());
                    edGenre.setText("Gênero: " + getAppDetailsObj.getGenre());

                    Call<GetNumberOfConcurrentPlayers> getNumberOfConcurrentPlayersCall = steamAPIService.getCCU("F8FC0E4F7BA600F9FCEC8D77F5801D83", getAppDetailsObj.getAppid()+"");
                    getNumberOfConcurrentPlayersCall.enqueue(new Callback<GetNumberOfConcurrentPlayers>() {
                        @Override
                        public void onResponse(Call<GetNumberOfConcurrentPlayers> call, Response<GetNumberOfConcurrentPlayers> response) {
                            GetNumberOfConcurrentPlayers getNumberOfConcurrentPlayers = response.body();
                            edCCUYesterday.setText("Pico ontem: " + getAppDetailsObj.getConcurrentUsers());
                            edCCUToday.setText("Usuarios agora: " + getNumberOfConcurrentPlayers.getResponse().getPlayer_count());
                        }

                        @Override
                        public void onFailure(Call<GetNumberOfConcurrentPlayers> call, Throwable t) {
                            Log.e("Failure:",t.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<GetAppDetailsRequest> call, Throwable t) {
                Log.e("Failure:",t.getMessage());
            }
        });

    }

    public String algoritmoDoCaralho(String s){
        String x = s.substring(0, s.length()-2) + "," + s.substring(s.length()-2, s.length());
        return x;
    }


    private void inicializarComponentes() {

        this.actionBar = getSupportActionBar();
        this.edNome = findViewById(R.id.txt_nome);
        this.edAppid = findViewById(R.id.txt_appid);
        this.edDeveloper = findViewById(R.id.txt_developer);
        this.edPublisher = findViewById(R.id.txt_publisher);
        this.edPositiveReviews = findViewById(R.id.txt_positive);
        this.edNegativeReviews = findViewById(R.id.txt_negative);
        this.edUserScore = findViewById(R.id.txt_userscore);
        this.edCCUYesterday = findViewById(R.id.txt_CCU_yesterday);
        this.edCCUToday = findViewById(R.id.txt_CCU_today);
        this.edInitialPrice = findViewById(R.id.txt_initial_price);
        this.edPrice = findViewById(R.id.txt_price);
        this.edDiscount = findViewById(R.id.txt_discount);
        this.edLanguages = findViewById(R.id.txt_languages);
        this.edGenre = findViewById(R.id.txt_genre);
//        this.progressBarJogo = findViewById(R.id.progressbar_jogo);
        this.retrofitSteamSpy = new Retrofit.Builder()
                .baseUrl(BASE_URL_STEAM_SPY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.retrofitSteamAPI = new Retrofit.Builder()
                .baseUrl(BASE_URL_STEAM_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
//    private void setVisibiity(){
//        this.edNome.setVisibility(View.VISIBLE);
//        this.edAppid.setVisibility(View.VISIBLE);
//        this.edDeveloper.setVisibility(View.VISIBLE);
//        this.edPublisher.setVisibility(View.VISIBLE);
//        this.edPositiveNegativeUserScore.setVisibility(View.VISIBLE);
//        this.edCCUYesterdayToday.setVisibility(View.VISIBLE);
//        this.edPriceInitialPrice.setVisibility(View.VISIBLE);
//        this.edLanguages.setVisibility(View.VISIBLE);;
//        this.edGenre.setVisibility(View.VISIBLE);
////        this.progressBarJogo.setVisibility(View.INVISIBLE);
//    }
}
