package c.gg.steamlog;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import c.gg.steamlog.ModelSteam.GetAppDetailsRequest;
import c.gg.steamlog.Services.SteamSpyService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamSpyService.BASE_URL_STEAM_SPY;

public class ExposicaoEstatisticas extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private Retrofit retrofitSteamSpy;

    private OnFragmentInteractionListener mListener;

    public ExposicaoEstatisticas() {
        // Required empty public constructor
    }


    public static ExposicaoEstatisticas newInstance(String param1, String param2) {
        ExposicaoEstatisticas fragment = new ExposicaoEstatisticas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        this.retrofitSteamSpy = new Retrofit.Builder()
                .baseUrl(BASE_URL_STEAM_SPY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SteamSpyService steamSpyService = retrofitSteamSpy.create(SteamSpyService.class);
        Call<GetAppDetailsRequest> getAppDetailsCall = steamSpyService.getAppDetails("appdetails", 730);

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exposicao_estatisticas, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
