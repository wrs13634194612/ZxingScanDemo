package com.example.scantestdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btScan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bt_scan
        btScan = findViewById(R.id.bt_scan);

        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize intent integrator
                IntentIntegrator intentIntegrator = new IntentIntegrator
                        (
                                MainActivity.this
                        );
                //Set prompt text
                intentIntegrator.setPrompt("For flash use volume up key");
                //Set beep
                intentIntegrator.setBeepEnabled(true);
                //Locked orientation
                intentIntegrator.setOrientationLocked(true);
                //Set capture activity
                //intentIntegrator.setCaptureActivity(Capture.class);
                //Initiate scan
                intentIntegrator.initiateScan();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Initialize intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data
        );
        //gson 解析
        /**
         {
         "age":26,
         "email":"249175190@qq.com",
         "isDeveloper":true,
         "name":"Normal"
         }
         */
//        Gson gson = new Gson();
//        ScanBean mScanBean = gson.fromJson(intentResult.getContents(), ScanBean.class);
//        int age = mScanBean.getAge();
//        String email = mScanBean.getEmail();
//        String name = mScanBean.getName();
//        boolean developer = mScanBean.isIsDeveloper();
//        Log.e("TAG", age + "\t" + email + "\t" + name + "\t" + developer);
//
//
        Log.e("TAG", "wrs_code:" + intentResult.getContents());

//        Base64 base64 = new Base64();
//       // String encodedString = new String(base64.encode(originalInput.getBytes()));
//        String decodedString = new String(base64.decode(encodedString.getBytes()));


        //  base64 = base64.substring(base64.lastIndexOf(",")+1);
        byte[] decodedString = Base64.decode(intentResult.getContents(), Base64.DEFAULT);
        // Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        String decodedString2 = new String(decodedString);
        String decodedString3 = intentResult.getContents();


        //Check condition
        if (intentResult.getContents() != null) {
            //When result content is not null
            //Initialize alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    MainActivity.this
            );
            //Set title
            builder.setTitle("Result");
            //Set message
            builder.setMessage(decodedString3);
            Log.e("TAG", "" + intentResult.getContents());
            //Set positive button
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Dismiss dialog
                    dialogInterface.dismiss();
                }
            });
            //Show alert dialog
            builder.show();
        } else {
            //When result content is null
            //Display toast
            Toast.makeText(getApplicationContext()
                    , "OOPS... You did not scan anything", Toast.LENGTH_SHORT).show();
        }
    }
}