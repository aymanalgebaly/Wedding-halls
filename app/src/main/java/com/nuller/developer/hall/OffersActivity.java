package com.nuller.developer.hall;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.nuller.developer.hall.R;
import com.nuller.developer.hall.main_recycle.MainModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class OffersActivity extends AppCompatActivity {

    TextView mName, mStatus, mlocation, mphone, detalis, mdetalis;

    Button call, location;

    String number;

    private String img, img1, img2,img3,img4;
    private ViewPager viewPager;
    private LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private OffersActivity.ViewPagerAdapter viewPagerAdapter;

    private String[] permissions;
    private String map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Locale locale = new Locale("en_US");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getResources().updateConfiguration(config, null);
        setContentView(R.layout.activity_offers);

        mName = (TextView) findViewById(R.id.offer_name);
        mStatus = (TextView) findViewById(R.id.offer_capacity);
        mlocation = (TextView) findViewById(R.id.offer_location_text);
        mphone = (TextView) findViewById(R.id.offer_number);
        detalis = (TextView) findViewById(R.id.offer_details_text);
        mdetalis = (TextView) findViewById(R.id.offer_details_description);

        call = (Button) findViewById(R.id.offer_call_button);
        location = (Button) findViewById(R.id.offer_location_button);

        viewPager = (ViewPager) findViewById(R.id.offer_viewPager);
        sliderDotspanel = (LinearLayout) findViewById(R.id.offer_SliderDots);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("offer")) {

            MainModel model = intent.getParcelableExtra("offer");
            mName.setText(model.getName());
            mStatus.setText(model.getCapacity());
            mlocation.setText(model.getLocation());
            mphone.setText(model.getPhone());
            mdetalis.setText(model.getDetails());

            map = model.getMap();


            img = model.getImg();
            img1 = model.getImg1();
            img2 = model.getImg2();
            img3 = model.getImg3();
            img4 = model.getImg4();

        }



        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];


        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);
        }



        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);


        permissions = new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.VIBRATE,
                Manifest.permission.RECORD_AUDIO,
        };


        checkPermissions();




    }




    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            OffersActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(3);
                    }else if (viewPager.getCurrentItem() == 3) {
                        viewPager.setCurrentItem(4);
                    }
                    else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }




    public class ViewPagerAdapter extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;

        private List<String> mainModelList;

        public ViewPagerAdapter(Context context) {
            this.context = context;
        }


        public void sendDataToAdapter(List<String> mainModelList) {
            this.mainModelList = mainModelList;
        }

        private String  [] images = {img,img1,img2,img3,img4};

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.custom_layout, null);
            final ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            //  imageView.setImageResource(String.valueOf(images));
            Picasso.get().load(String.valueOf(images[position])).placeholder(R.drawable.default1).into(imageView);



            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    customDialog("تخزين الصورة","هل ترغب فى تخزين الصورة على الهاتف ", "cancelMethod","okMethod");

                    return true;
                }



                public void customDialog(String title, String message, final String cancelMethod, final String okMethod){
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(context,R.style.AppCompatAlertDialogStyle);
                    builderSingle.setIcon(R.drawable.ic_notifcation);
                    builderSingle.setTitle(title);
                    builderSingle.setMessage(message);

                    builderSingle.setNegativeButton(
                            "غير موافق",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(cancelMethod.equals("cancelMethod")){
                                        Toast.makeText(context, " لم يتم تخزين الصورة ", Toast.LENGTH_LONG).show();

                                    }

                                }
                            });

                    builderSingle.setPositiveButton(
                            "موافق",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(okMethod.equals("okMethod")){



                                        //  كود تخزين الصوره
                                        CapturePhotoUtils photoUtils = new CapturePhotoUtils();
                                        imageView.setDrawingCacheEnabled(true);
                                        Bitmap b = imageView.getDrawingCache();
                                        photoUtils.insertImage(context.getContentResolver(),
                                                b, "1image", "this is downloaded image sample");

                                        Toast.makeText(context, " تم تخزين الصورة ", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });


                    builderSingle.show();
                }



            });

            ViewPager vp = (ViewPager) container;
            vp.addView(view, 0);
            return view;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            ViewPager vp = (ViewPager) container;
            View view = (View) object;
            vp.removeView(view);

        }


    }



    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do something
            }
            return;
        }
    }


    public void location(View view) {

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<x>,<y>?q=<x>,<y>("+map+")"));
        startActivity(mapIntent);

    }


    public void call(View view) {

        number = mphone.getText().toString();


        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    101);
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + number)));
        }

    }







}

