package com.nuller.developer.hall.fragment;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nuller.developer.hall.R;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class InviteFragment extends Fragment {


    private Button call ;
    private EditText invent;
    private Typeface myfont;
    private Spinner spinner;

    String s1 = "دعوة مميزة لكل المميزين في قلبي ليتميزوا معي في حفل زفافي يوم" +
            " .......... الموافق ......... في فندق ......... و نشكر الزوق الي حضروا   (تطبيق قاعتى)";

    String s2 = "باسم الرحمن بنبدأ دعوتنا و فيكم تزداد فرحتنا نتمنى تكونوا في ليلتنا يوم ....... الموافق ....... في صالة ......  (تطبيق قاعتى)";

    String s3 = "يوم ......... الموافق ......... في صالة ......... ستشاركوني فرحتي في حفل زفافي و تنورون عالمي فكم يسعدني أن تلبوا دعوتي في ذلك اليوم الجيل .  (تطبيق قاعتى)";

    String s4 = "الفرح و السعادة بشوفتكم أعرفها يوم ألقاكم و بالدعوة الي وصلتكم من جوا القلب دعيناكم بالمراحب و الهلا اهنيكم و يجعل الأفراح دنياكم في صالة ........ يوم ........... الموافق .......  (تطبيق قاعتى)";

    String s5 = "نتشرف بدعوتكم لحضور حفل زفافنا العتيد في فندق ......... يوم ......... الموافق ........ وبس تيجونا أفراحنا بتزيد .  (تطبيق قاعتى)";

    String s6 = "من غير وجودكم ما بتكتمل الفرحة و بلقاكم السعادة تزيد من غير جيتكم ينقص الهنا فأرجوكم تيجون يوم .......... التاريخ .......... في صالة ..........  (تطبيق قاعتى)";

    String s7 = "الي يحبنا يشرفنا في يوم المنى يشاركنا و بحضوره يشرفنا في ........ الموافق ........... في فندق ........  (تطبيق قاعتى)";

    String s8 = "بتمنى ما تنسونا تشرفونا بحضوركم في عرسي أنا و عريسي ...... يوم ...... الموافق ...... في ..... للنساء و ....... للرجال . (تطبيق قاعتى)";

    String s9 = "دعوتكم وحضوركم هم أجمل حيـــــــــــاه فرحتي عامره والكون ينور في ضيــــــاه يا نهار العيد في حياتي مع صبحه ومساه أجمل آيات التهاني لكم مع الغــــــــــــرام .(تطبيق قاعتى)";


    String names[] = {s1,s2,s3,s4,s5,s6,s7,s8,s9};

    ArrayAdapter<String> adapter;


    public InviteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_invite, container, false);

        call =view.findViewById(R.id.whatsapp);
        invent=view.findViewById(R.id.invent);

        spinner = view.findViewById(R.id.invite_spinner);

        myfont = Typeface.createFromAsset(getContext().getAssets(), "fonts/JannaLT-Regular.ttf");
        invent.setTypeface(myfont);


        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,names);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //use postion value

                String s = parent.getItemAtPosition(position).toString();
                invent.setText(s);

            }

            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });













        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = invent.getText().toString();

                PackageManager pm = getContext().getPackageManager();
                try {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");

                    PackageInfo info = pm.getPackageInfo("com.whatsapp",
                            PackageManager.GET_META_DATA);

                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    startActivity(Intent.createChooser(waIntent, "Share with"));

                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(getContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });

        return view;
    }

}
