package c.gg.steamlog;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import c.gg.steamlog.ModelSteam.GetAppDetailsRequest;
import c.gg.steamlog.Services.SteamSpyService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamSpyService.BASE_URL_STEAM_SPY;

public class ExposicaoEstatisticas extends Fragment {


    private Retrofit retrofitSteamSpy;


    public ExposicaoEstatisticas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_exposicao_estatisticas, container, false);
        Bundle bundle =getArguments();
        String appid = bundle.getString("appid");
        this.inicializarComponentes(v);

        Toast.makeText(getActivity(),appid,Toast.LENGTH_LONG).show();



        SteamSpyService steamSpyService = retrofitSteamSpy.create(SteamSpyService.class);
        Call<GetAppDetailsRequest> getAppDetailsCall = steamSpyService.getAppDetails("appdetails", Long.parseLong(appid));

        getAppDetailsCall.enqueue(new Callback<GetAppDetailsRequest>() {
            @Override
            public void onResponse(Call<GetAppDetailsRequest> call, Response<GetAppDetailsRequest> response) {
                GetAppDetailsRequest getAppDetailsObj = response.body();
            }

            @Override
            public void onFailure(Call<GetAppDetailsRequest> call, Throwable t) {
                System.out.println("nao deu");
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    private void inicializarComponentes(View view){

        this.retrofitSteamSpy = new Retrofit.Builder()
                .baseUrl(BASE_URL_STEAM_SPY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}

