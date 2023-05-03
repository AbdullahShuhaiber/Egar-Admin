package com.example.egar_admin.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.egar_admin.BroadcastReceivers.NetworkChangeListiners;
import com.example.egar_admin.R;
import com.example.egar_admin.databinding.ActivityLoginBinding;
import com.example.egar_admin.ui.MainActivity;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding binding;
    NetworkChangeListiners networkChangeListiners = new NetworkChangeListiners();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        screenOperations();

    }

        private void screenOperations (){
            setOnClick();
            setDataInInputFieldFromRegister();

        }

        @Override
        protected void onStart() {
            super.onStart();
            IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(networkChangeListiners,intentFilter);
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

        private  boolean setDataInInputFieldFromRegister (){
            Intent intent = getIntent();
            String phone = intent.getStringExtra("phone");
            String password =intent.getStringExtra("password");
            if (password == null && phone == null){
                return false;
            }else {
                binding.etPhone.setText(intent.getStringExtra("phone"));
                binding.etPassword.setText(intent.getStringExtra("password"));
                return true;
            }
        }
        private boolean dataCheck (){
            String phone = binding.etPhone.getText().toString();
            String password = binding.etPassword.getText().toString();
            if (phone.isEmpty()) {
                binding.etPhone.setError("PhoneNumber field is Required");
                return false;
            } else if (password.isEmpty()) {
                binding.etPassword.setError("Password field is Required");
                return false;
            }
            return true;
        }
        private  void setOnClick(){
            binding.btnLogin.setOnClickListener(this::onClick);
            binding.tvCreateAccount.setOnClickListener(this::onClick);
           // binding.btnBack.setOnClickListener(this::onClick);
            binding.tvForgotPassword.setOnClickListener(this::onClick);
        }
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_login:
                    if (dataCheck()){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(this, "The Input Fields Required", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.tv_createAccount:
                    Intent intent1 = new Intent(getApplicationContext(),RegisterActivity.class);
                    startActivity(intent1);
                    break;
       /*         case R.id.tv_forgotPassword:
                    Intent intent3 = new Intent(getApplicationContext(),ResetPassword.class);
                    startActivity(intent3);
                    break;*/

            }
        }

}