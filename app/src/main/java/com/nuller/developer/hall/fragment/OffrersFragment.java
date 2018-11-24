package com.nuller.developer.hall.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.nuller.developer.hall.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nuller.developer.hall.main_recycle.MainModel;
import com.nuller.developer.hall.offers_recycle.OffersAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OffrersFragment extends Fragment {

    RecyclerView recyclerView;
    TextView hollName;
    private DatabaseReference databaseReference;
    private OffersAdapter adapter;


    public OffrersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offrers, container, false);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Offers");

        hollName = view.findViewById(R.id.offer_holl_name);
        recyclerView = view.findViewById(R.id.offer_rv);



        setupRecycle();
        viewData();
        return view;
    }


    private void viewData() {



        if(databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        final List<MainModel> offerModelList = new ArrayList<>();

                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot dataSnapshot1 : children) {
                            MainModel offerModel = dataSnapshot1.getValue(MainModel.class);
                            offerModelList.add(offerModel);

                        }

                        adapter.sendDataToAdapter(offerModelList);
                        adapter.notifyDataSetChanged();

                    } else
                        Toast.makeText(getContext(), "لا يوجد عروض اليوم", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }



    private void setupRecycle() {

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OffersAdapter(getContext());
        recyclerView.setAdapter(adapter);

    }



}
