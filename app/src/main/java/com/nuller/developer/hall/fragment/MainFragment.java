package com.nuller.developer.hall.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nuller.developer.hall.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nuller.developer.hall.main_recycle.MainAdapter;
import com.nuller.developer.hall.main_recycle.MainModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    private MainAdapter adapter;


    public MainFragment() {
        // Required empty public constructor
    }





    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = view.findViewById(R.id.main_rv);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Halls");



        setupRecycle();
        viewData();

        return view;
    }


    private void viewData() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<MainModel> mainModelList = new ArrayList<>();

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot dataSnapshot1 : children){
                    MainModel mainModel = dataSnapshot1.getValue(MainModel.class);
                    mainModelList.add(mainModel);

                }

                adapter.sendDataToAdapter(mainModelList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    private void setupRecycle() {

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MainAdapter(getContext());
        recyclerView.setAdapter(adapter);

    }


}
