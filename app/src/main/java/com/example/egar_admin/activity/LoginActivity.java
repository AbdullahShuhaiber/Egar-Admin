package com.example.egar_admin.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.egar_admin.BroadcastReceivers.NetworkChangeListiners;
import com.example.egar_admin.FirebaseManger.FirebaseAuthController;
import com.example.egar_admin.FirebaseManger.FirebaseFetchingDataController;
import com.example.egar_admin.SharedPreferences.AppSharedPreferences;
import com.example.egar_admin.controllers.LocationUtilsController;
import com.example.egar_admin.interfaces.OnLocationFetchedListener;
import com.example.egar_admin.interfaces.ProviderTypeCallback;
import com.example.egar_admin.R;
import com.example.egar_admin.databinding.ActivityLoginBinding;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.example.egar_admin.ui.MainActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding binding;
    NetworkChangeListiners networkChangeListiners = new NetworkChangeListiners();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        Toast.makeText(this, AppSharedPreferences.getInstance().getSharedPreferences().getString("isFirstRun","no")+"", Toast.LENGTH_SHORT).show();
        screenOperations();
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        LocationUtilsController locationUtilsController = LocationUtilsController.getInstance(manager, geocoder);
        locationUtilsController.getCurrentLocation(new OnLocationFetchedListener() {
            @Override
            public void onLocationFetched(String location) {
                Snackbar.make(binding.getRoot(), location, Snackbar.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(), location, Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        // Create an exit dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setIcon(R.drawable.baseline_exit_to_app_24);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Close the application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog and continue with the application
                dialog.dismiss();
            }
        });
        // Create the dialog and show it
        AlertDialog dialog = builder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                // Do nothing
            }
        });
        dialog.show();
    }


    private void screenOperations() {
        setOnClick();
        setTitle("LOGIN");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.active)));
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this, R.color.active));
        setDataInInputFieldFromRegister();

    }

    private void operationsSccren() {

    }


    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(this, FirebaseAuth.getInstance().getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListiners, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkChangeListiners);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private boolean setDataInInputFieldFromRegister() {
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");
        if (password == null && email == null) {
            return false;
        } else {
            binding.etEmail.setText(intent.getStringExtra("email"));
            binding.etPassword.setText(intent.getStringExtra("password"));
            return true;
        }
    }

    private boolean dataCheck() {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        if (email.isEmpty()) {
            binding.etEmail.setError("Email field is Required");
            return false;
        } else if (password.isEmpty()) {
            binding.etPassword.setError("Password field is Required");
            return false;
        }
        return true;
    }

    private void setOnClick() {
        binding.btnLogin.setOnClickListener(this::onClick);
        binding.tvCreateAccount.setOnClickListener(this::onClick);
        binding.tvForgotPassword.setOnClickListener(this::onClick);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (dataCheck()) {
                    binding.buttonAnimation.setVisibility(View.VISIBLE);
                    binding.buttonAnimation.playAnimation();
                    binding.buttonText.setVisibility(View.GONE);
                    loginAndCheckProviderType();
                } else {
                    Snackbar.make(binding.getRoot(), "Please enter Data , The Input Filed is Required", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this, R.color.bronze)).show();
                }

                break;
            case R.id.tv_createAccount:
                Intent intent1 = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_forgotPassword:
                Intent intent = new Intent(getApplicationContext(), ResetPassword.class);
                startActivity(intent);
                break;


        }
    }

    private void loginAndCheckProviderType() {
        FirebaseAuthController.getInstance().signIn(
                binding.etEmail.getText().toString(),
                binding.etPassword.getText().toString(),
                new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
                        checkProviderTypeAndRedirectToActivity();
                    }

                    @Override
                    public void onFailure(String message) {
                        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    private void checkProviderTypeAndRedirectToActivity() {
        FirebaseFetchingDataController.getInstance().checkProviderTypeAndRedirectToActivity(binding.etEmail.getText().toString().trim(), new ProviderTypeCallback() {
            @Override
            public void onProviderTypeChecked(String providerType) {
                Toast.makeText(LoginActivity.this, providerType, Toast.LENGTH_SHORT).show();
                if (providerType != null) {
                    if (providerType.equals("Delivery")) {
                        Intent intent = new Intent(LoginActivity.this, DeliveryActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    binding.buttonAnimation.pauseAnimation();
                    binding.buttonAnimation.setVisibility(View.GONE);
                    binding.buttonText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onProviderTypeNull(String message) {
                Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onProviderTypeFailure(String message) {
                Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
            }
        });
    }


}