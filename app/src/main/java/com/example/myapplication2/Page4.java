package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Page4 extends AppCompatActivity {

    EditText editTextPhoneNumber,editTextMessage;
    Button buttonSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);

        editTextPhoneNumber=findViewById(R.id.editTextPhoneNumber);
        editTextMessage=findViewById(R.id.editTextMessage);
        buttonSms=findViewById(R.id.buttonSms);


        buttonSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(Page4.this,Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                }else {
                    ActivityCompat.requestPermissions(Page4.this,new String[]{Manifest.permission.SEND_SMS},100);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==100&& grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendSMS();
        }else{
            Toast.makeText(this,"İzin reddedildi!",Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSMS() {

        String phone=editTextPhoneNumber.getText().toString();
        String message=editTextMessage.getText().toString();

        if(!phone.isEmpty()&& !message.isEmpty()){
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null,message,null,null);

            Toast.makeText(this,"Sms başarıyla gönderildi!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Lütfen telefo numarası ve mesaj giriniz!", Toast.LENGTH_SHORT).show();
        }
    }
}

