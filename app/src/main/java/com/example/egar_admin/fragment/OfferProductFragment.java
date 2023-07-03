package com.example.egar_admin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.egar_admin.Model.Offer;
import com.example.egar_admin.Model.Product;
import com.example.egar_admin.R;
import com.example.egar_admin.adapters.ProductHomeAdapter;
import com.example.egar_admin.adapters.offers.OffersAdapter;
import com.example.egar_admin.controllers.OfferController;
import com.example.egar_admin.databinding.FragmentOfferProductBinding;
import com.example.egar_admin.databinding.FragmentProductTapBinding;
import com.example.egar_admin.interfaces.OnOfferFetchListener;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class OfferProductFragment extends Fragment {

    FragmentOfferProductBinding binding;
    private List<Offer> offerList = new ArrayList<>();
    OfferController offerController = new OfferController();
    private OffersAdapter adapter;

    public OfferProductFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentOfferProductBinding.inflate(getLayoutInflater());

        initializeView();


        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    private void initializeView() {
        initializeRecyclerAdapter();
        getOffers();
    }

    private void initializeRecyclerAdapter() {
        adapter = new OffersAdapter(offerList);
        //adapter.setCallback(this);
        binding.recOffers.setAdapter(adapter);
        binding.recOffers.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    private void getOffers(){
        offerController.getOffersByProviderId(FirebaseAuth.getInstance().getUid(), new OnOfferFetchListener() {
            @Override
            public void onListFetchSuccess(List<Offer> offerList) {

            }

            @Override
            public void onGetOffersByServiceProviderIdSuccess(List<Offer> offers) {
                offerList.clear();
                offerList.addAll(offers.subList(0, Math.min(3, offers.size())));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onGetOffersByServiceProviderIdFailure(String message) {
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
            }
        });

    }
}