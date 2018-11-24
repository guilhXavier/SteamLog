package c.gg.steamlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamSpyService.BASE_URL_STEAM_SPY;

public class Estatisticas extends AppCompatActivity{

    private Button btPesquisar;
    private EditText edtxtAppid;
    private FragmentManager fm = getSupportFragmentManager();
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);
        inicializarComponentes();

        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment;
                if(edtxtAppid.getText()!=null){

                    fragment = new ExposicaoEstatisticas();
                    String s = edtxtAppid.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("appid",s);
                    fragment.setArguments(bundle);
                    ft.replace(R.id.fragment_container,fragment);
                    ft.commit();

                }else{
                    Toast.makeText(Estatisticas.this,"Insira o appid, mi amigo",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void inicializarComponentes() {
        this.btPesquisar = findViewById(R.id.bt_pesquisar);
        this.edtxtAppid = findViewById(R.id.edtxt_appid);
        this.ft = fm.beginTransaction();
    }


}
