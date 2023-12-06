package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Page2 extends AppCompatActivity {

    private EditText editTextInputDecimal,editTextInputMegaByte,editTextInputCelcius;
    private Spinner spinnerDecimal,spinnerMegaByte;
    private RadioGroup radioGroupCelcius;
    private Button buttonConvertDecimal,buttonConvertMegaByte,buttonConvertCelcius;
    private TextView textViewDecimalResult,textViewMegabayteResult,textViewCelciusResult;

    private String[] decimalOptions={"İkilik","Sekizlik","On altılık"};
    private String[] megaByteOptions={"KiloByte","Byte","Bit","KibiByte"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        editTextInputDecimal=findViewById(R.id.editTextInputDecimal);
        editTextInputMegaByte=findViewById(R.id.editTextInputMegaByte);
        editTextInputCelcius=findViewById(R.id.editTextInputCelcius);

        spinnerDecimal=findViewById(R.id.spinnerDecimal);
        spinnerMegaByte=findViewById(R.id.spinnerMegaByte);

        radioGroupCelcius=findViewById(R.id.radioGroupCelcius);

        buttonConvertDecimal=findViewById(R.id.buttonConvertDecimal);
        buttonConvertMegaByte=findViewById(R.id.buttonConvertMegaByte);
        buttonConvertCelcius=findViewById(R.id.buttonConvertCelcius);

        textViewDecimalResult=findViewById(R.id.textViewDecimalResult);
        textViewMegabayteResult=findViewById(R.id.textViewMegaByteResult);
        textViewCelciusResult=findViewById(R.id.textViewCelciusResult);


        setUpSpinner(spinnerDecimal,decimalOptions);
        setUpSpinner(spinnerMegaByte,megaByteOptions);

        buttonConvertDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertDecimal();
            }
        });

        buttonConvertMegaByte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertMegabyte();
            }
        });
        buttonConvertCelcius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCelcius();
            }
        });

    }

    private void setUpSpinner(Spinner spinner,String[] options){
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void convertDecimal(){
        try{
            String inputValue=editTextInputDecimal.getText().toString();
            int decimalValue=Integer.parseInt(inputValue);

            String selectedOption=spinnerDecimal.getSelectedItem().toString();
            String result="";

            switch(selectedOption){
                case "İkilik":
                    result=Integer.toBinaryString(decimalValue);
                    break;
                case "Sekizlik":
                    result=Integer.toOctalString(decimalValue);
                    break;
                case "On altılık":
                    result=Integer.toHexString(decimalValue);
                    break;
            }
              textViewDecimalResult.setText(result);
        }catch (NumberFormatException e){
            Toast.makeText(this,"Geçersiz giriş",Toast.LENGTH_SHORT).show();
        }
    }

    private void convertMegabyte(){
        try{
            String inputValue=editTextInputMegaByte.getText().toString();
            double megaByteValue=Double.parseDouble(inputValue);

            String selectedOption=spinnerMegaByte.getSelectedItem().toString();
            double result =0;

            switch (selectedOption){
                case "KibiByte":
                    result =megaByteValue*1024/1000;
                    break;
                case "Byte":
                    result =megaByteValue*1024*1024;
                    break;

                case "Bit":
                    result =megaByteValue*1024*1024*8;
                    break;

                case "KiloByte":
                    result =megaByteValue*1024;
                    break;
            }
            textViewMegabayteResult.setText(String.valueOf(result));
        }catch (NumberFormatException e){
            Toast.makeText(this,"Geçersiz giriş",Toast.LENGTH_SHORT).show();
        }
    }

    private void convertCelcius(){
        try{
            String inputValue=editTextInputCelcius.getText().toString();
            double celciusValue=Double.parseDouble(inputValue);

            int selectedRadioButtonId=radioGroupCelcius.getCheckedRadioButtonId();
            String result="";

            if(selectedRadioButtonId==R.id.radioButtonFahrenheit){
                result=String.valueOf((celciusValue*9/5)+32);
            }else if (selectedRadioButtonId==R.id.radioButtonKelvin){
                result=String.valueOf(celciusValue+273.15);
            }

            textViewCelciusResult.setText(result);
        }catch(NumberFormatException e){
            Toast.makeText(this,"Geçersiz giriş",Toast.LENGTH_SHORT).show();
        }
    }

}