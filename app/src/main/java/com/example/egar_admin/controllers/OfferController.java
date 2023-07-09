package com.example.egar_admin.controllers;

import com.example.egar_admin.Model.Offer;
import com.example.egar_admin.Model.Product;
import com.example.egar_admin.interfaces.OnOfferFetchListener;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class OfferController {
    private static final String COLLECTION_PATH = "offers";
    private CollectionReference offersCollection;

    public OfferController() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        offersCollection = db.collection(COLLECTION_PATH);
    }
    public void addOffer(Product product, double price, int quantity, String startDate, String endDate, ProcessCallback callback) {
        CollectionReference offersCollection = FirebaseFirestore.getInstance().collection("offers");

        Offer offer = new Offer(product, price, quantity, startDate, endDate);

        HashMap<String, Object> offerData = new HashMap<>();
        offerData.put("product", offer.getProduct());
        offerData.put("price", offer.getPrice());
        offerData.put("quantity", offer.getQuantity());
        offerData.put("startDate", offer.getStartDate());
        offerData.put("endDate", offer.getEndDate());

        offersCollection.add(offerData)
                .addOnSuccessListener(documentReference -> {
                    String documentId = documentReference.getId();
                    if (!documentId.isEmpty()) {
                        offerData.put("id", documentId);
                        offersCollection.document(documentId).set(offerData)
                                .addOnSuccessListener(aVoid -> {
                                    callback.onSuccess("Product added with ID: " + documentId);
                                })
                                .addOnFailureListener(e -> {
                                    callback.onFailure(e.getMessage());
                                });
                    } else {
                        callback.onFailure("فشل في الحصول على معرف العرض.");
                    }
                })
                .addOnFailureListener(e -> {
                    callback.onFailure(e.getMessage());
                });
    }


    public void deleteOffer(String offerId, ProcessCallback callback) {
        CollectionReference offersCollection = FirebaseFirestore.getInstance().collection("offers");

        offersCollection.document(offerId).delete()
                .addOnSuccessListener(aVoid -> {
                    callback.onSuccess("تم حذف العرض بنجاح.");
                })
                .addOnFailureListener(e -> {
                    callback.onFailure("فشل في حذف العرض. الخطأ: " + e.getMessage());
                });
    }


    public void updateOffer(Offer offer, ProcessCallback callback) {
        CollectionReference offersCollection = FirebaseFirestore.getInstance().collection("offers");

        String offerId = offer.getId();

        if (offerId != null) {
            DocumentReference offerRef = offersCollection.document(offerId);

            offerRef.set(offer)
                    .addOnSuccessListener(aVoid -> {
                        callback.onSuccess("تم تحديث العرض بنجاح.");
                    })
                    .addOnFailureListener(e -> {
                        callback.onFailure("فشل في تحديث العرض. الخطأ: " + e.getMessage());
                    });
        } else {
            callback.onFailure("لا يوجد معرف صالح للعرض.");
        }
    }


    public void getOffersByProviderId(String providerId, OnOfferFetchListener callback) {
        offersCollection.whereEqualTo("product.provider.id", providerId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Offer> offersList = queryDocumentSnapshots.toObjects(Offer.class);
                    callback.onGetOffersByServiceProviderIdSuccess(offersList);
                })
                .addOnFailureListener(e -> {
                    callback.onGetOffersByServiceProviderIdFailure(e.getMessage());
                });
    }


}
