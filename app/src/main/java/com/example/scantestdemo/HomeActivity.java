package com.example.scantestdemo;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;


public class HomeActivity extends AppCompatActivity {

    private ActionBar toolbar;
    CoordinatorLayout mCoordinatorLayoutl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = getSupportActionBar();
        loadFragment(new HomeFragment());
        BottomNavigationView navigation = findViewById(R.id.bottom_nav);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("Jadwal");
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frLayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.action_start:
                    toolbar.setTitle("Jadwal");
                    fragment = new HomeFragment();
                    break;
                case R.id.action_favorites:
                    toolbar.setTitle("Mata Kuliah");
                    fragment = new HomeFragment();
                    break;
                case R.id.action_profile:
                    toolbar.setTitle("Dosen");
                    fragment = new HomeFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Initialize intent result

        if (data != null) {

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

            Intent intent = new Intent(HomeActivity.this, TestActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", decodedString3);
            intent.putExtras(bundle);
            startActivity(intent);

            //Check condition
//        if (intentResult.getContents() != null) {
//            //When result content is not null
//            //Initialize alert dialog
//            AlertDialog.Builder builder = new AlertDialog.Builder(
//                    HomeActivity.this
//            );
//            //Set title
//            builder.setTitle("Result");
//            //Set message
//            builder.setMessage(decodedString3);
//            Log.e("TAG", "" + intentResult.getContents());
//            //Set positive button
//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    //Dismiss dialog
//                    dialogInterface.dismiss();
//                }
//            });
//            //Show alert dialog
//            builder.show();
//        } else {
//            //When result content is null
//            //Display toast
//            Toast.makeText(getApplicationContext()
//                    , "OOPS... You did not scan anything", Toast.LENGTH_SHORT).show();
//        }
        }
    }




}