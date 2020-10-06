package com.toni.peeperandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imageBtnPhone;
    private ImageButton imageBtnWeb;
    private ImageButton imageBtnCamera;

    private final int PHONE_CALL_CODE = 100; //codigo que identifica la comprobacion de permiso de telefono

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        imageBtnPhone = (ImageButton) findViewById(R.id.imageBtnPhone);
        imageBtnWeb = (ImageButton) findViewById(R.id.imageBtnWeb);
        imageBtnCamera = (ImageButton) findViewById(R.id.imageBtnCamera);

        //BOTON PARA EL TELEFONO
        imageBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = editTextPhone.getText().toString();
                if (phoneNumber != null && !phoneNumber.isEmpty()){
                    //comprobar version actual de android que estamos corriendo es mayor a la version M
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        //comprobar si ha aceptado, no ha aceptado o nunca se le ha preguntado
                        if (checkPermission(Manifest.permission.CALL_PHONE)){
                            //Ha aceptado
                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber)); //se puede introducir con metodo addData etc
                            startActivity(i);
                        } else {
                            //ha denegado o no se le ha preguntado
                            if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                                //ha denegado
                                Toast.makeText(ThirdActivity.this, "Habilita el permiso, por favor", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:"+getPackageName()));
                                //FLAGS para al ir atrás vuelve a la aplicación
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);
                            } else {
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE); //se le comprueba el permiso CALL_PHONE, puede haber mas
                            }
                        }
                    } else {
                        OlderVersion(phoneNumber);
                    }
                } else {
                    Toast.makeText(ThirdActivity.this, "Introduce un número de teléfono", Toast.LENGTH_LONG).show();
                }
            }

            //método específico, se puede usar para permiso de teléfono y en ámbito local
            private void OlderVersion (String phoneNumber){
                if (checkPermission(Manifest.permission.CALL_PHONE)) {
                    Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "Acceso rechazado", Toast.LENGTH_LONG).show();
                }
            }
        });


        //BOTON PARA EL NAVEGADOR WEB
        imageBtnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editTextWeb.getText().toString();
                if (url != null && !url.isEmpty()){
                    Intent intentWeb = new Intent();
                    intentWeb.setAction(Intent.ACTION_VIEW);
                    intentWeb.setData(Uri.parse("http://"+url));

                    //Intent para contacto
                    Intent intentContact = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));

                    //Intent para mail rapido
                    String mail = "ferreirocouto";
                    Intent intentCorreo = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+mail));

                    //Intent para mail completo
                    Intent intentFullCorreo = new Intent(Intent.ACTION_VIEW, Uri.parse("mail"));
                    intentFullCorreo.setType("plain/text");
                    intentFullCorreo.putExtra(Intent.EXTRA_SUBJECT, "Title");
                    intentFullCorreo.putExtra(Intent.EXTRA_TEXT, "Hi there! Im Toni (...)");
                    intentFullCorreo.putExtra(Intent.EXTRA_EMAIL, new String[]{"fernandolelcatolico@yahoo.es", "ferreirocouto@gmail.com"});

                    //telefono 2 sin permisos requeridos, nos lo marca y solo hace falta pulsar para llamar
                    Intent intentTel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+6000000000") );

                    startActivity(intentTel);
                }
            }
        });
    }

    //se llama este método en cada llamada de requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Estamos en el caso del telefono
        switch (requestCode){
            case PHONE_CALL_CODE :
                String permission = permissions[0];
                int result = grantResults[0]; //recoge el resultado del usuario si permite o no el permiso del telefono

                if (permission.equals(Manifest.permission.CALL_PHONE)){
                    //comprobar si ha sido aceptado o denegado la peticion de permiso
                    if (result == PackageManager.PERMISSION_GRANTED){
                        //permiso concedido
                        String phoneNumber = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
                        startActivity(intentCall);
                    } else {
                        //permiso no concedido
                        Toast.makeText(ThirdActivity.this, "Permiso declinado", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }


    //método genérico, se puede usar con permisos de cualquier tipo, no solo de teléfono
    private boolean checkPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}