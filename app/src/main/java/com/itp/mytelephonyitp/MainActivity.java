package com.itp.mytelephonyitp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn_sms,btn_call;
    EditText et_number,et_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_sms=findViewById(R.id.btn_sms);
        btn_call=findViewById(R.id.btn_call);
        et_number=findViewById(R.id.et_mo_number);
        et_text=findViewById(R.id.et_text);


        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //use intent for calling functionality

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_CALL);
//                intent.setData(Uri.parse("tel:"+"45656768789"));
                intent.setData(Uri.parse("tel:"+et_number.getText().toString()));
                startActivity(intent);
            }
        });

        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SmsManager smsManager=SmsManager.getDefault();
                String[] mobile_numbers=et_number.getText().toString().split("#");
                String text=et_text.getText().toString();

                for(String mobile_number:mobile_numbers) {
                    Intent si_intent = new Intent(MainActivity.this, SentActivity.class);
                    PendingIntent si = PendingIntent.getActivity(MainActivity.this, 0, si_intent, 0);

                    Intent dl_intent = new Intent(MainActivity.this, DeliverActivity.class);
                    PendingIntent di = PendingIntent.getActivity(MainActivity.this, 0, dl_intent, 0);

                    smsManager.sendTextMessage(mobile_number, null, text, si, di);
                }
            }
        });


    }
}