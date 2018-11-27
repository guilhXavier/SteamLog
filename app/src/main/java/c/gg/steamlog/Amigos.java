package c.gg.steamlog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import c.gg.steamlog.Model.Usuario;
import c.gg.steamlog.ModelSteam.GetFriendList;
import c.gg.steamlog.ModelSteam.GetPlayerSummaries;
import c.gg.steamlog.Services.SteamAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamAPIService.BASE_URL_STEAM_API;

public class Amigos extends AppCompatActivity {

    private ListView lvAmigos;
    private List<String> nomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos);
        this.inicializarComponentes();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL_STEAM_API).addConverterFactory(GsonConverterFactory.create()).build();

        final SteamAPIService service = retrofit.create(SteamAPIService.class);

        Intent meuIntent = getIntent();
        Usuario usuario = (Usuario) meuIntent.getExtras().getSerializable("usuario");

        Call<GetFriendList> friendListCall = service.listFriends("F8FC0E4F7BA600F9FCEC8D77F5801D83", usuario.getSteamid()+"", "friend");
        friendListCall.enqueue(new Callback<GetFriendList>() {
            @Override
            public void onResponse(Call<GetFriendList> call, Response<GetFriendList> response) {
                GetFriendList getFriendList = response.body();
                nomes = new ArrayList<>();
                for(int x = 0; x < getFriendList.getFriendslist().getFriends().size(); x++){
                    Call<GetPlayerSummaries> getPlayerSummariesCall = service.summary("F8FC0E4F7BA600F9FCEC8D77F5801D83", getFriendList.getFriendslist().getFriends().get(x).getSteamid());
                    getPlayerSummariesCall.enqueue(new Callback<GetPlayerSummaries>() {
                        @Override
                        public void onResponse(Call<GetPlayerSummaries> call, Response<GetPlayerSummaries> response) {

                            GetPlayerSummaries getPlayerSummaries = response.body();
                            nomes.add(getPlayerSummaries.getResponse().getPlayers().get(0).getPersonaname());
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Amigos.this, android.R.layout.simple_list_item_1, nomes);
                            lvAmigos.setAdapter(adapter);

                        }



                        @Override
                        public void onFailure(Call<GetPlayerSummaries> call, Throwable t) {

                        }
                    });
                }




            }

            @Override
            public void onFailure(Call<GetFriendList> call, Throwable t) {

            }
        });
    }

    private void inicializarComponentes(){
        this.lvAmigos = findViewById(R.id.lv_amigos);
    }
}
