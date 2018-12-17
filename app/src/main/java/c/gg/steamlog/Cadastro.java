package c.gg.steamlog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import c.gg.steamlog.Model.Imagens;
import c.gg.steamlog.Model.Usuario;
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

public class Cadastro extends AppCompatActivity {

    public static final int PICK_IMAGE = 1234;
    private ActionBar actionBar;
    private ImageView imvFotoPerfil;
    private EditText edNickname,edEmail,edSenha,edConfirmeSenha,edSteamid;
    private Button btRegistrar;
    private Retrofit retrofitServer;
    private Retrofit retrofitSteam;
    private Usuario usuario;
    private Imagens imagens;
    private String encodedImage, CaminhoImagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.inicializarComponentes();
        this.actionBar.setTitle(R.string.cadastro);

        imvFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(i,"Selecione uma imagem"),PICK_IMAGE);
            }
        });

        this.btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(edSenha.getText().toString().equals(edConfirmeSenha.getText().toString()))){
                    Toast.makeText(Cadastro.this,"As senhas diferentes",Toast.LENGTH_SHORT).show();
                }else {
                    usuario = new Usuario();
                    imagens = new Imagens();
                    usuario.setEmail(edEmail.getText().toString());
                    usuario.setNickname(edNickname.getText().toString());
                    usuario.setSenha(edSenha.getText().toString());
                    usuario.setSteamid((Long.parseLong(edSteamid.getText().toString())));
//                    imagens.setArquivoImagem(CaminhoImagem);
                    imagens.setArquivoImagem(encodedImage);
                    imagens.setTipoImagem((short) 1);

                    SteamAPIService apiService = retrofitSteam.create(SteamAPIService.class);
                    final SteamLogService service = retrofitServer.create(SteamLogService.class);
                    Call<GetOwnedGames> gamesCount = apiService.ownedGames("802A0913D1CB0336DFE046F0294F4B04",Long.toString(usuario.getSteamid()));
                    gamesCount.enqueue(new Callback<GetOwnedGames>() {
                        @Override
                        public void onResponse(Call<GetOwnedGames> call, Response<GetOwnedGames> response) {
                            if(!response.isSuccessful()){
                                Log.e("SteamResponseFailure:","Erro:"+response.code());
                            } else{
                                GetOwnedGames games = response.body();
                                Log.e("Numero:",games.getResponse().getGame_Count());
                                usuario.setNumJogos(Integer.parseInt(games.getResponse().getGame_Count()));
                                Call<Imagens> cadastrar = service.inserirImagem(imagens);
                                cadastrar.enqueue(new Callback<Imagens>() {
                                    @Override
                                    public void onResponse(Call<Imagens> call, Response<Imagens> response) {
                                        if(!response.isSuccessful()){
                                            Log.e("ImagemResponseError:","Erro:"+response.code());
                                        }else{
                                            imagens = response.body();
                                            usuario.setImagens(imagens);
                                            Call<Usuario> cadastrar = service.cadastraUsuario(usuario);
                                            cadastrar.enqueue(new Callback<Usuario>() {
                                                @Override
                                                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                                    if(!response.isSuccessful()) {
                                                        Log.e("UsuarioResponseError:", "Erro:" + response.code());
                                                    } else {
                                                        usuario = response.body();
                                                        Intent intent = new Intent(Cadastro.this,Perfil.class);
                                                        intent.putExtra("usuario",usuario);
                                                        intent.putExtra("cadastro/login",true);
                                                        startActivity(intent);
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<Usuario> call, Throwable t) {
                                                    Log.e("UsuarioFailure:",t.getMessage());
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Imagens> call, Throwable t) {
                                        Log.e("UsuarioFailure:",t.getMessage());
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
        });

    }


    private void inicializarComponentes(){
        this.actionBar = getSupportActionBar();
        this.imvFotoPerfil = findViewById(R.id.imv_foto_cadastro);
        this.edNickname = findViewById(R.id.ed_nickname_cadastro);
        this.edEmail = findViewById(R.id.ed_email_cadastro);
        this.edSenha = findViewById(R.id.ed_senha_cadastro);
        this.edConfirmeSenha = findViewById(R.id.ed_confsenha_cadastro);
        this.edSteamid = findViewById(R.id.ed_steamid_cadastro);
        this.btRegistrar = findViewById(R.id.bt_registrar_cadastro);

        this.retrofitServer  = new Retrofit.Builder()
                .baseUrl(BASE_URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.retrofitSteam = new Retrofit.Builder()
                .baseUrl(BASE_URL_STEAM_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != Activity.RESULT_CANCELED) {
            if (requestCode == PICK_IMAGE) {
                Uri selectedImage = data.getData();
                imvFotoPerfil.setImageURI(selectedImage);
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImage);
                    Bitmap imagemSelecionada = BitmapFactory.decodeStream(imageStream);
                    encodedImage = encodeImage(imagemSelecionada);
                    Toast.makeText(getApplicationContext(), selectedImage.toString(), Toast.LENGTH_SHORT).show();
                    this.CaminhoImagem = selectedImage.toString();
                    System.out.println(encodedImage);
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }

            }
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }


}
