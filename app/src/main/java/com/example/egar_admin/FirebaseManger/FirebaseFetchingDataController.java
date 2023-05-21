package com.example.egar_admin.FirebaseManger;

import androidx.annotation.NonNull;

import com.example.egar_admin.interfaces.ProcessCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseFetchingDataController {

    private static FirebaseFetchingDataController instance;

    private FirebaseFetchingDataController() {
        // تجنب إنشاء كائنات هنا
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

    public void getCurrentUserName(ProcessCallback callback) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference userRef = db.collection("serviceproviders").document(currentUser.getUid());

            userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        String name = documentSnapshot.getString("name");
                        callback.onSuccess(name);
                    } else {
                        callback.onFailure("User document does not exist");
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    callback.onFailure(e.getMessage());
                }
            });
        } else {
            callback.onFailure("No current user");
        }
    }


    public void checkProviderTypeAndRedirectToActivity(String userId, ProviderTypeCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("serviceproviders").document(userId);

        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        String providerType = document.getString("providerType");
                        callback.onProviderTypeChecked(providerType);
                    } else {
                        callback.onProviderTypeChecked(null);
                    }
                } else {
                    callback.onProviderTypeChecked(null);
                }
            }
        });
    }
}



