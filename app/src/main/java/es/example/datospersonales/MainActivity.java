package es.example.datospersonales;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
Button btnValidar;
Button btnBorrar;
RadioGroup radioGroup;
RadioButton radioButton;
Spinner lista;
String seleccionado;
Switch aSwitch;
String hijo;
//traducir
private Button button;
private Locale locale;
private Configuration config = new Configuration();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//traductor
        button = ((Button)findViewById(R.id.button));
      button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        showDialog();
                    }});
   //Switch
        aSwitch= findViewById(R.id.switchHijos);


  //Spinner
        lista = findViewById(R.id.spinnerEstadoCivil);
        final String[] datos = getResources().getStringArray(R.array.datos);
        final ArrayAdapter adapador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, datos);

        lista.setAdapter(adapador);
            //switch para elegir
        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                switch (i){
                    case 1:
                        seleccionado = datos[i];
                        break;

                    case 2:
                        seleccionado = datos[i];
                        break;
                    case 3:
                        seleccionado = datos[i];
                        break;

                    case 4:
                        seleccionado = datos[i];
                        break;

                    case 5:
                        seleccionado = datos[i];
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    //radiobutton
        radioGroup = findViewById(R.id.RG);

        //botón principal
        btnValidar = findViewById(R.id.btnValidar);
        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Switch
                if (aSwitch.isChecked()) {
                    hijo = " tiene hijos";
                } else {
                    hijo = " no tiene hijos";
                }

    //radio button
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
                //buscando los datos
              EditText nombre = findViewById(R.id.edNombre);
                EditText apellido = findViewById(R.id.edApellido);
                EditText edad = findViewById(R.id.NumEdad);




              if (nombre.length()==0) {
                  Toast.makeText(MainActivity.this, "No escribiste ningun Nombre", Toast.LENGTH_SHORT).show();
              }   else if (apellido.length() == 0) {
                      Toast.makeText(MainActivity.this, "No escribiste ningun Apellido", Toast.LENGTH_SHORT).show();

                  } else  if (edad.length()==0){
                  Toast.makeText(MainActivity.this, "No escribiste ninguna edad", Toast.LENGTH_SHORT).show();

              } else  if (seleccionado == "Seleccionar uno"){
                  Toast.makeText(MainActivity.this, "No seleccionaste ninguna relación", Toast.LENGTH_SHORT).show();
              } else {


                      Toast.makeText(MainActivity.this, nombre.getText() + " " + apellido.getText() + ", tiene " + edad.getText() + " años"+ " genero "+ radioButton.getText()+" esta "+ seleccionado+ " y "+ hijo, Toast.LENGTH_SHORT).show();
                  }
              }
        });
        //boton borrar
        btnBorrar=findViewById(R.id.btnBorrar);
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nombre = findViewById(R.id.edNombre);
                EditText apellido = findViewById(R.id.edApellido);
                EditText edad = findViewById(R.id.NumEdad);

                nombre.setText("");
                apellido.setText("");
                edad.setText("");
                 radioGroup.clearCheck();
                aSwitch.setChecked(false);
                lista.setAdapter(adapador);

            }
        });

    }

    private void showDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getResources().getString(R.string.str_button));
        //obtiene los idiomas del array de string.xml
        String[] types = getResources().getStringArray(R.array.languages);
        b.setItems(types, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                switch(which){
                    case 0:
                        locale = new Locale("en");
                        config.locale =locale;
                        break;
                    case 1:
                        locale = new Locale("es");
                        config.locale =locale;
                        break;
                    case 2:
                        locale = new Locale("pt");
                        config.locale =locale;
                        break;
                }
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                startActivity(refresh);
                finish();
            }

        });

        b.show();
    }

}

