package c.gg.steamlog;

import android.content.Intent;
import android.os.Bundle;;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamSpyService.BASE_URL_STEAM_SPY;

public class Estatisticas extends AppCompatActivity{

    private Button btPesquisar;
    private EditText edtxtAppid;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);
        inicializarComponentes();

        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtxtAppid.getText()!=null){
                    Intent myIntent = new Intent(Estatisticas.this, Jogo.class);
                    myIntent.putExtra("appid", edtxtAppid.getText().toString());
                    startActivity(myIntent);
                }else{
                    Toast.makeText(Estatisticas.this,"Insira o appid, mi amigo",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void inicializarComponentes() {

        this.btPesquisar = findViewById(R.id.bt_pesquisar);
        this.edtxtAppid = findViewById(R.id.ed_appid);
    }



}
