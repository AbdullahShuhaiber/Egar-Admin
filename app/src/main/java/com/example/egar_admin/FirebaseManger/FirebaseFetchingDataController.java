package com.example.egar_admin.FirebaseManger;

import androidx.annotation.NonNull;

import com.example.egar_admin.interfaces.DataCallBackUser;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.example.egar_admin.interfaces.ProviderTypeCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseFetchingDataController {

    private static FirebaseFetchingDataController instance;

    private FirebaseFetchingDataController() {
    }

    public static FirebaseFetchingDataController getInstance() {
        if (instance == null) {
            synchronized (FirebaseFetchingDataController.class) {
                if (instance == null) {
                    instance = new FirebaseFetchingDataController();
                }
            }
        }
        return instance;
    }

    public void getCurrentUserData(DataCallBackUser callback) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference userRef = db.collection("serviceproviders").document(currentUser.getUid());

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference profileImageRef = storage.getReference().child("profile_images_providers").child(currentUser.getUid());

            userRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String name = documentSnapshot.getString("name");
                    String address = documentSnapshot.getString("address");
                    String number = documentSnapshot.getString("phoneNumber");

                    profileImageRef.getDownloadUrl().addOnSuccessListener(downloadUri -> {
                        String profileImageUrl = downloadUri.toString();
                        callback.onSuccess(name,address,number,profileImageUrl);
                    }).addOnFailureListener(e -> {
                        callback.onFailure(e.getMessage());
                    });
                } else {
                    callback.onFailure("User document does not exist");
                }
            }).addOnFailureListener(e -> {
                callback.onFailure(e.getMessage());
            });
        } else {
            callback.onFailure("No current user");
        }
    }



    public void checkProviderTypeAndRedirectToActivity(String email, ProviderTypeCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference providersRef = db.collection("serviceproviders");
        Query query = providersRef.whereEqualTo("email",email);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && task.isComplete()) {
                    QuerySnapshot snapshot = task.getResult();
                    if (snapshot != null && !snapshot.isEmpty()) {
                        DocumentSnapshot document = snapshot.getDocuments().get(0); // Assuming there's only one document per email
                        String providerType = document.getString("providerType");
                        callback.onProviderTypeChecked(providerType);
                    } else {
                        callback.onProviderTypeNull("null Type");
                    }
                } else {
                    callback.onProviderTypeFailure("Failure Type");
                }
            }
        });
    }
    public void getProviderTypeForCurrentUser(String id, ProcessCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference providersRef = db.collection("serviceproviders");

        Query query = providersRef.whereEqualTo("id", id);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot != null && !querySnapshot.isEmpty()) {
                        DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                        String providerType = documentSnapshot.getString("providerType");
                        callback.onSuccess(providerType);
                    } else {
                        callback.onFailure("User document does not exist");
                    }
                } else {
                    callback.onFailure(task.getException().getMessage());
                }
            }
        });
    }

}



