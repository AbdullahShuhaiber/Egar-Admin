package com.example.egar_admin.FirebaseManger;
import com.example.egar_admin.Model.Provider;
import com.example.egar_admin.interfaces.SignInStatusListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


import androidx.annotation.NonNull;


import com.example.egar_admin.interfaces.ProcessCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;



public class FirebaseAuthController {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private static FirebaseAuthController instance;

    private FirebaseAuthController() {
    }

    public static synchronized FirebaseAuthController getInstance() {
        if(instance == null) {
            instance = new FirebaseAuthController();
        }
        return instance;
    }

    public void createAccount(String name, String email, String password, String phoneNumber, String providerType,String address,String city,String bio, ProcessCallback callback) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    auth.getCurrentUser().sendEmailVerification();

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    CollectionReference usersRef = db.collection("serviceproviders");

                    Provider provider = new Provider(name, email, phoneNumber, providerType);
                    provider.setAddress(address);
                    provider.setCity(city);
                    provider.setBio(bio);

                    usersRef.document(auth.getCurrentUser().getUid())
                            .set(provider)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    callback.onSuccess("Account created successfully");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    callback.onFailure(e.getMessage());
                                }
                            });
                } else {
                    callback.onFailure(task.getException().getMessage());
                }
            }
        });
    }


    public void signIn(String email, String password, ProcessCallback callback) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (Objects.requireNonNull(auth.getCurrentUser()).isEmailVerified()) {
                        //TODO: Login success, Navigate to home screen (FROM UI)
                        callback.onSuccess("Logged in successfully");
                    } else {
                        auth.signOut();
                        callback.onFailure("Verify email to login");
                    }
                } else {
                    callback.onFailure(Objects.requireNonNull(task.getException()).getMessage());
                }
            }
        });
    }

    public void forgetPassword(String email, ProcessCallback callback) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess("Reset email sent successfully");
                } else {
                    callback.onFailure(task.getException().getMessage());
                }
            }
        });
    }

    public void signOut() {
        auth.signOut();

    }


    public void isSignedIn(final SignInStatusListener listener) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            DocumentReference userRef = db.collection("serviceproviders").document(userId);
            userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        String role = documentSnapshot.getString("providerType");
                        if (role != null) {
                            if (role.equals("Delivery")) {
                                listener.onUserSignedInAsDeliveryProvider();
                            } else {
                                listener.onUserSignedInAsRegularProvider();
                            }
                        }
                    } else {
                        listener.onUserNotSignedIn();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Handle the error
                }
            });
        } else {
            listener.onUserNotSignedIn();
        }
    }


}
