package com.example.egar_admin.interfaces;


import com.example.egar_admin.Model.Offer;

import java.util.List;

public interface OnOfferFetchListener {
    void onListFetchSuccess(List<Offer> offerList);

    void onGetOffersByServiceProviderIdSuccess(List<Offer> offers);
    void onGetOffersByServiceProviderIdFailure(String message);
}

