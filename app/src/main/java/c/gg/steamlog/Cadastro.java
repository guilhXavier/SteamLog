package c.gg.steamlog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import c.gg.steamlog.Model.Imagens;
import c.gg.steamlog.Model.Usuario;
import c.gg.steamlog.Services.SteamLogService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static c.gg.steamlog.Services.SteamLogService.BASE_URL;

public class Cadastro extends AppCompatActivity {

    public static final int PICK_IMAGE = 1234;
    private ActionBar actionBar;
    private ImageView imvFotoPerfil;
    private EditText edNickname,edEmail,edSenha,edConfirmeSenha,edSteamid;
    private Button btRegistrar;
    private Retrofit retrofit;
    private Usuario usuario;
    private Imagens imagens;
    private String CaminhoImagem;


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
                    usuario.setSteamid((Integer.parseInt(edSteamid.getText().toString())));
                    imagens.setArquivoImagem(CaminhoImagem);
                    imagens.setTipoImagem((short) 1);


                    final SteamLogService service = retrofit.create(SteamLogService.class);
                    Call<Imagens> cadastrar = service.inserirImagem(imagens);
                    cadastrar.enqueue(new Callback<Imagens>() {
                        @Override
                        public void onResponse(Call<Imagens> call, Response<Imagens> response) {
                            if(!response.isSuccessful()){
                                Log.e("ImagemResponseError:","Erro:"+response.code());
                            } else {
                                Toast.makeText(Cadastro.this,"Imagem Foi",Toast.LENGTH_SHORT).show();
                                imagens = response.body();
                                usuario.setImagens(imagens);
                                Call<Usuario> cadastrar = service.cadastraUsuario(usuario);
                                cadastrar.enqueue(new Callback<Usuario>() {
                                    @Override
                                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                        if(!response.isSuccessful()){
                                            Log.e("UsuarioResponseError:","Erro:"+response.code());
                                        } else {
                                            Toast.makeText(Cadastro.this,"FAZER INTEGRADOR É FACÍL",Toast.LENGTH_SHORT).show();
                                           Intent intent = new Intent(Cadastro.this,Perfil.class);
                                           intent.putExtra("usuario",usuario);
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
                            Log.e("ImagemFailure:",t.getMessage());
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

        this.retrofit  = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != Activity.RESULT_CANCELED) {
            if (requestCode == PICK_IMAGE) {
                Uri selectedImage = data.getData();
                imvFotoPerfil.setImageURI(selectedImage);
                Toast.makeText(getApplicationContext(), selectedImage.toString(), Toast.LENGTH_SHORT).show();
                this.CaminhoImagem = selectedImage.toString();
            }
        }
    }


}
