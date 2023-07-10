package com.example.egar_admin.controllers;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;


import com.example.egar_admin.Model.Order;
import com.example.egar_admin.enums.OrderStatus;
import com.example.egar_admin.interfaces.OnOrderByIdFetchListener;
import com.example.egar_admin.interfaces.OnOrderFetchListener;
import com.example.egar_admin.interfaces.OnOrderStatusFetchListener;
import com.example.egar_admin.interfaces.OnOrdersWithCountFetchListener;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderController {

    private static OrderController instance;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private OrderController() {
        // Private constructor to prevent direct instantiation
    }

    public static OrderController getInstance() {
        if (instance == null) {
            synchronized (OrderController.class) {
                if (instance == null) {
                    instance = new OrderController();
                }
            }
        }
        return instance;
    }


    public void getOrdersByServiceProviderId(String serviceProviderId,OnOrderFetchListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ordersCollection = db.collection("orders");

        Query query = ordersCollection.whereEqualTo("product.provider.id", serviceProviderId);

        query.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Order> orders = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Order order = document.toObject(Order.class);
                            orders.add(order);
                            listener.onGetOrdersByServiceProviderIdSuccess(orders);
                        }
                    } else {
                        Log.w(TAG, "Error getting orders by service provider ID", task.getException());
                        listener.onGetOrdersByServiceProviderIdFailure(task.getException().getMessage());
                    }
                });
    }
    public void getCompletedOrders(OnOrderStatusFetchListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ordersCollection = db.collection("orders");

        Query query = ordersCollection.whereEqualTo("orderStatus", OrderStatus.COMPLETED);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Order> completedOrders = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    Order order = document.toObject(Order.class);
                    completedOrders.add(order);
                }
                listener.onGetOrdersSuccess(completedOrders);
            } else {
                listener.onGetOrderStatusFailure(task.getException().getMessage());
            }
        });
    }

    public void getOrdersWithCountByStatusAndServiceProviderId(OrderStatus status, String serviceProviderId, OnOrdersWithCountFetchListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ordersCollection = db.collection("orders");

        Query query = ordersCollection.whereEqualTo("orderStatus", status)
                .whereEqualTo("product.provider.id", serviceProviderId);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Order> orders = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    Order order = document.toObject(Order.class);
                    orders.add(order);
                }
                int orderCount = orders.size();
                listener.onGetOrdersWithCountByStatusSuccess(orders, orderCount);
            } else {
                Log.w(TAG, "Error getting orders with count by status and service provider ID: " + status, task.getException());
                listener.onGetOrdersWithCountByStatusFailure(task.getException().getMessage());
            }
        });
    }


    public void updateOrderStatus(String orderId, OrderStatus newStatus, final ProcessCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ordersCollection = db.collection("orders");

        Query query = ordersCollection.whereEqualTo("orderId", orderId).limit(1);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (!querySnapshot.isEmpty()) {
                    DocumentSnapshot document = querySnapshot.getDocuments().get(0);
                    String documentId = document.getId();

                    DocumentReference orderRef = ordersCollection.document(documentId);
                    orderRef.update("orderStatus", newStatus.toString())
                            .addOnCompleteListener(updateTask -> {
                                if (updateTask.isSuccessful()) {
                                    callback.onSuccess("تم تحديث حالة الطلب بنجاح");
                                } else {
                                    callback.onFailure(updateTask.getException().getMessage());
                                }
                            });
                } else {
                    callback.onFailure("لم يتم العثور على الوثيقة المطابقة");
                }
            } else {
                // فشل في جلب الوثيقة
                callback.onFailure(task.getException().getMessage());
            }
        });
    }


    public void getOrderStatusById(String orderId, OnOrderStatusFetchListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ordersCollection = db.collection("orders");

        Query query = ordersCollection.whereEqualTo("orderId", orderId).limit(1);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (!querySnapshot.isEmpty()) {
                    DocumentSnapshot document = querySnapshot.getDocuments().get(0);
                    String orderStatus = document.getString("orderStatus");
                    listener.onGetOrderStatusSuccess(orderStatus);
                } else {
                    listener.onGetOrderStatusFailure("لم يتم العثور على الوثيقة المطابقة");
                }
            } else {
                listener.onGetOrderStatusFailure(task.getException().getMessage());
            }
        });
    }


}

