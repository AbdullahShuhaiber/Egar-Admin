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
import com.example.egar_admin.interfaces.ProcessCallback;

import java.util.ArrayList;


public class OfferProductFragment extends Fragment {

    FragmentOfferProductBinding binding;
    private ArrayList<Offer> offers = new ArrayList<>();
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
        adapter = new OffersAdapter(offers);
        //adapter.setCallback(this);
        binding.recOffers.setAdapter(adapter);
        binding.recOffers.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    private void getOffers(){
        OfferController offerController = new OfferController();
        offerController.getAllOffers(new ProcessCallback() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onFailure(String message) {

            }
        });

    }
}