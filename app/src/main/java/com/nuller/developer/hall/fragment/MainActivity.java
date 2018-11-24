package com.nuller.developer.hall.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nuller.developer.hall.R;
import com.nuller.developer.hall.SearchLocation;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView title;
    Button search;
    Typeface myFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Locale locale = new Locale("en_US");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getResources().updateConfiguration(config, null);
        setContentView(R.layout.activity_main);


        myFont = Typeface.createFromAsset(this.getAssets(),"fonts/JannaLT-Regular.ttf");

        toolbar = findViewById(R.id.main_bar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        checkInternet();

        title = findViewById(R.id.app_title);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);

        search = findViewById(R.id.app_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchLocation.class));
            }
        });

        FragmentsAdapter fragmentsAdapter = new FragmentsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentsAdapter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        title.setTypeface(myFont);
                        title.setText("الرئيسية");
                        search.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        title.setText("أرسل دعوة   ");
                        title.setTypeface(myFont);
                        search.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        title.setText("العروض     ");
                        title.setTypeface(myFont);
                        search.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setTabsIcon();

    }

    private boolean checkInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

            if (ni == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
                builder.setTitle("خطأ فى اللإتصال")
                        .setMessage("برجاء المحاولة مرة آخرى")
                        .setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Toast.makeText(MainActivity.this, "تأكد من وجود الإنترنت !!", Toast.LENGTH_SHORT).show();

                            }
                        }).show();
                return false;

            }

        return true;
    }


    public void setTabsIcon(){

        int tabIcons [] = {R.drawable.ic_home_black_24dp,R.drawable.ic_message_black_24dp,R.drawable.ic_notifications_active_black_24dp};

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_search:

                startActivity(new Intent(MainActivity.this,SearchLocation.class));
                break;


            case R.id.menu_contact:


                final String [] numbers = {"01029520915" , "01095691592"};

                AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AppCallAlertDialogStyle);
                final String[] position = new String[1];
                builder.setTitle("اختر رقما للإتصــال       ")
                        .setIcon(R.drawable.ic_call_black_24dp)
                        .setSingleChoiceItems(numbers, -1, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                position[0] = numbers[i];

                            }
                        })
                        .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);

                                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(
                                            MainActivity.this,
                                            new String[]{Manifest.permission.CALL_PHONE},
                                            101);
                                }else if(position[0] == null){
                                    Toast.makeText(MainActivity.this, "إختر رقم للإتصال", Toast.LENGTH_LONG).show();
                                }
                                 else {
                                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+ position[0])));
                                }

                            }
                        }).show();

                break;
            case R.id.menu_exit:

                AlertDialog.Builder builder1 = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
                builder1.setTitle("خروج")
                        .setMessage("هل تريد الخروج ؟")
                        .setIcon(R.drawable.ic_close_black_24dp)
                        .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        finishAffinity();
//                        System.exit(0);
                        finish();

                    }
                }).show();



                break;
        }

        return super.onOptionsItemSelected(item);

    }



    public void onCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    101);
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:12345678901")));
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    onCall();
                }
                break;

            default:
                break;
        }
    }




}
