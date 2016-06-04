package br.com.easyvansapp.easyvans;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_FOTO = 1;
    // Spinner
    private String[] states;
    private Spinner editTextWho;
    //Defining View
    private EditText editTextName;
    private EditText editTextCpf;
    private EditText editTextTelephone;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonAvancar;
    private ImageButton buttonImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_01);

        // INICIO SPINNER
        states = getResources().getStringArray(R.array.who_array);
        editTextWho = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, states);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editTextWho.setAdapter(dataAdapter);

        // FIM SPINNER

        // INICIALIZANDO VIEWS
        editTextName = (EditText) findViewById(R.id.nome);
        editTextCpf = (EditText) findViewById(R.id.cpf);
        editTextTelephone = (EditText) findViewById(R.id.telefone);
        editTextPassword = (EditText) findViewById(R.id.senha);
        editTextConfirmPassword = (EditText) findViewById(R.id.conf_senha);
        editTextWho = (Spinner) findViewById(R.id.spinner);
        buttonAvancar = (Button) findViewById(R.id.avancar_cad_btn);
        //buttonImage = (ImageButton) findViewById(R.id.foto_btn);

        // INICIALIZANDO BOTOES
        buttonAvancar.setOnClickListener(this);
        //buttonImage.setOnClickListener(this);
    }
        // CADASTRANDO USUARIO
        private void addUser(){
            final String user_name = editTextName.getText().toString().trim();
            final String user_cpf = editTextCpf.getText().toString().trim();
            final String user_telephone = editTextTelephone.getText().toString().trim();
            final String user_password = editTextPassword.getText().toString().trim();
            //final String user_who = editTextWho

        class AddUser extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Adicionando...","Aguarde...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(ConfigUser.KEY_USER_TELEPHONE,user_telephone);
                params.put(ConfigUser.KEY_USER_NAME,user_name);
                params.put(ConfigUser.KEY_USER_CPF,user_cpf);
                params.put(ConfigUser.KEY_USER_PASSWORD,user_password);
                //params.put(ConfigUser.KEY_USER_URL_IMAGE, user_who);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(ConfigUser.URL_ADD_USERS, params);
                Log.d("NGVL", res);
                return res;
            }
        }

        AddUser ae = new AddUser();
        ae.execute();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if (resultCode == RESULT_OK && requestCode == REQUEST_FOTO) {
            //Uri selectedImageUri = data.getData();
            //mTxtArquivo.setText(getPath(selectedImageUri));
        //}
    }


    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(
                uri, projection, null, null, null);

        int column_index = cursor.getColumnIndexOrThrow(
                MediaStore.Images.Media.DATA);

        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    private void selecionarFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_FOTO);
    }

    private void enviarFoto() {
        new UploadArquivoTask().execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAvancar){
            String password = editTextPassword.getText().toString();
            String confirmPassword = editTextConfirmPassword.getText().toString();

            if(!password.equals(confirmPassword)){
                editTextConfirmPassword.setError(getString(R.string.msg_failed_text_password));
            } else{
                enviarFoto();
                addUser();
                startActivity(new Intent(this, cadastro_02.class));
            }

        }

        if(v == buttonImage){
            selecionarFoto();
        }

    }

    class UploadArquivoTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            boolean sucesso = false;
            try {
                //UtilHttp.enviarFoto(

                        //editTextImage.getText().toString(),
                        //mTxtArquivo.getText().toString()
                //);
                sucesso = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return sucesso;
        }

        @Override
        protected void onPostExecute(Boolean sucesso) {
            super.onPostExecute(sucesso);
            int mensagem = sucesso ?
                    R.string.msg_sucesso :
                    R.string.msg_falha ;

            Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_SHORT).show();
        }
    }
}

