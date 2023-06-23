package com.example.egar_admin.interfaces;


import com.example.egar_admin.Model.Offer;

import java.util.List;

public interface OnOfferFetchListener {
    void onListFetchSuccess(List<Offer> offerList);
    void onListFetchFailure(String message);
    void onAddOfferSuccess(String offerId);
    void onAddOfferFailure(String message);
    void onUpdateOfferSuccess();
    void onUpdateOfferFailure(String message);
    void onDeleteOfferSuccess();
    void onDeleteOfferFailure(String message);
    void onGetOffersByServiceProviderIdSuccess(List<Offer> offers);
    void onGetOffersByServiceProviderIdFailure(String message);
}

