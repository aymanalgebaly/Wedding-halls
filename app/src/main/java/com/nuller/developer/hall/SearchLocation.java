package com.nuller.developer.hall;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nuller.developer.hall.R;
import com.nuller.developer.hall.main_recycle.MainModel;
import com.squareup.picasso.Picasso;

public class SearchLocation extends AppCompatActivity {

    private RadioGroup radioGroup;

    private EditText mSearchField;

    Typeface myFont;

    private RecyclerView mResultList;

    private String search_child;
    private FirebaseRecyclerOptions<MainModel> options;
    private FirebaseRecyclerAdapter<MainModel, AllUsersViewHolder> adapter;
    private DatabaseReference mUserDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);

        myFont = Typeface.createFromAsset(SearchLocation.this.getAssets(), "fonts/JannaLT-Regular.ttf");


        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Halls");

        mSearchField = (EditText) findViewById(R.id.search_field);

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));


        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() != 0) {

                    String searchText = mSearchField.getText().toString();

                    firebaseUserSearch(searchText);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        addListenerOnButton();

    }


    public void addListenerOnButton() {

        radioGroup = (RadioGroup) findViewById(R.id.radio);

        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.radio_name:
                        search_child = "name";
                        break;
                    case R.id.radio_location:
                        search_child = "city";
                        break;

                }
            }
        });


    }


    private void firebaseUserSearch(String searchText) {

        if (search_child != null) {

            Query query = mUserDatabase.orderByChild(search_child).startAt(searchText).endAt(searchText + "\uf8ff");

            options =
                    new FirebaseRecyclerOptions.Builder<MainModel>()
                            .setQuery(query, MainModel.class)
                            .build();

            if (searchText != null && !TextUtils.isEmpty(searchText)) {

                adapter = new FirebaseRecyclerAdapter<MainModel, AllUsersViewHolder>(options) {


                    @NonNull
                    @Override
                    public AllUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.list_layout, parent, false);
                        return new AllUsersViewHolder(view);
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull final AllUsersViewHolder holder, int position, @NonNull final MainModel model) {


                        holder.setDetails(getApplicationContext(), model.getName(),model.getCity(),model.getSalary(), model.getImg(),model.getRate());

                        final String list_user_id = getRef(position).getKey();

                        mUserDatabase.child(list_user_id).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                holder.mView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent profileIntent = new Intent(SearchLocation.this, DetailsActivity.class);
                                        profileIntent.putExtra("search_model", model);
                                        startActivity(profileIntent);
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                };


                mResultList.setAdapter(adapter);
                adapter.startListening();

            }

          }

       }
   }




    class AllUsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public AllUsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx, String userName,String userCity, String userStatus, String userImage ,String userRate){

            Typeface myFont;
            myFont = Typeface.createFromAsset(ctx.getAssets(),"fonts/JannaLT-Regular.ttf");

            TextView name = (TextView) mView.findViewById(R.id.search_name);
            TextView city1 = (TextView) mView.findViewById(R.id.search_city);
            TextView salary =mView.findViewById(R.id.search_salary);
            TextView rate = mView.findViewById(R.id.search_rate);
            ImageView img = (ImageView) mView.findViewById(R.id.search_img);


            name.setText(userName);
            city1.setText(userCity);
            salary.setText(userStatus);
            rate.setText(userRate);
            Picasso.get().load(userImage).placeholder(R.drawable.default1).into(img);

            name.setTypeface(myFont);
            city1.setTypeface(myFont);



        }

    }



