package com.paulabetancur.formulario;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private String loggin, password, mail, password2, sexo, hobbie = "", ciudad, fecha;
    private DatePickerDialog datePickerDialog;
    private EditText eLoggin, ePassword, eMail, ePassword2;
    private Button bAceptar, date;
    private TextView tInfo, eDate;
    private RadioButton rMasculino, rFemenino;
    private CheckBox cCine, cDormir, cComer, cBailar;
    private Spinner sCiudades;
    private static final String TAG = "MainAcivity";
    private DatePickerDialog.OnDateSetListener mDate;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        eLoggin = (EditText) findViewById( R.id.eLoggin);
        ePassword = (EditText) findViewById( R.id.ePassword);
        ePassword2 = (EditText) findViewById( R.id.ePassword2);
        bAceptar = (Button) findViewById( R.id.bAceptar);
        tInfo = (TextView) findViewById( R.id.tInfo);
        eMail = (EditText) findViewById(R.id.eMail);
        rMasculino = (RadioButton) findViewById ( R.id.rMasculino) ;
        rFemenino = (RadioButton) findViewById( R.id.rFemenino);
        cCine = (CheckBox) findViewById( R.id.cCine);
        cComer = (CheckBox) findViewById( R.id.cComer);
        cDormir = (CheckBox) findViewById( R.id.cDormir);
        cBailar = (CheckBox) findViewById( R.id.cBailar);
        sCiudades = (Spinner) findViewById( R.id.sCiudades);
        eDate = (TextView) findViewById( R.id.eDate);






        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudades, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sCiudades.setAdapter(adapter);

        sCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudad = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int año = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,  android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDate, año, mes, dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int año, int mes, int dia) {
                mes = mes + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + mes + "/" + dia + "/" + año);

                String fecha = " " + mes + "/" + dia + "/" + año;
                eDate.setText(fecha);
            }
        };





        bAceptar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            fecha = eDate.getText().toString();

            if (rMasculino.isChecked()) {sexo = "Masculino";}
            else {sexo = "Femenino";}

            if(eLoggin.getText().toString().isEmpty() || eMail.getText().toString().isEmpty()
                    || ePassword.getText().toString().isEmpty() || ePassword2.getText().toString().isEmpty() || sexo.isEmpty() || eDate.getText().toString().isEmpty()
                    || (!cCine.isChecked() && !cBailar.isChecked() && !cDormir.isChecked() && !cComer.isChecked()) ){
                tInfo.setText("Esta vacio algún campo del fomulario");
            }else{
                password = ePassword.getText().toString();
                password2 = ePassword2.getText().toString();
                if (!password.equals(password2)){
                    tInfo.setText("El password no coincide, ingreselo nuevamente");
                }
                else {
                    if(cCine.isChecked()){hobbie = hobbie + " " + "Cine";}
                    if(cComer.isChecked()){hobbie = hobbie+ " " + "Comida";}
                    if(cBailar.isChecked()){hobbie = hobbie+ " " + "Bailar";}
                    if (cDormir.isChecked()){hobbie = hobbie+ " " + "Dormir";}


                    loggin = eLoggin.getText().toString();
                    mail = eMail.getText().toString();
                    tInfo.setText("Sus datos son:" +
                            "\nLogiin: " + loggin +
                            "\nPassword: " + password +
                            "\nEmail: " + mail +
                            "\nSexo: " + sexo +
                            "\nFecha de nacimiento:"  + fecha +
                            "\nCiudad: "+ ciudad +
                            "\nHobbie(s): " + hobbie);
                }
            }

            hobbie = "";
        }
    });



}
}





