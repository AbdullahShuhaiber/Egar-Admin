package com.example.egar_admin.activity;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;


import com.example.egar_admin.FirebaseManger.FirebaseAuthController;
import com.example.egar_admin.adapters.MyFragmentAdapter;
import com.example.egar_admin.databinding.ActivityRegisterBinding;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private MyFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





    }

    private boolean isValidPalestinianPhoneNumber() {
        String phoneNumber = binding.etPhoneNumber.getText().toString().trim();
        if (phoneNumber.isEmpty()){
            Snackbar.make(binding.getRoot(), "Please enter a  phone number", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this,R.color.bronze)).show();
            return false;
        } else if (phoneNumber.matches("^\\+970(59|56)\\d{7}$")) {
            return true;
        }else {
            Snackbar.make(binding.getRoot(), "Please enter a valid Palestinian phone number starting with 059 or 056.", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this,R.color.bronze)).show();
            return false;
        }

    }

    public boolean isValidEmail() {
        String email = binding.etEmail.getText().toString().trim();
        boolean isValid = false;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            isValid = false;
        } else {
            Matcher matcher = pattern.matcher(email);
            isValid = matcher.matches();
        }
        if (!isValid) {
            Snackbar.make(binding.getRoot(), "Invalid email address", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this,R.color.bronze)).show();

        }
        return isValid;
    }

    private boolean dataCheck (){
        String name = binding.etUserName.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (name.isEmpty()) {
            binding.etUserName.setError("UserName field is Required");
            return false;
        } else if (email.isEmpty()  ) {
            binding.etEmail.setError("Email field is Required");
            return false;
        } else if (password.isEmpty()) {
            binding.etPhoneNumber.setError("Password field is Required");
            return false;
        }else if(!binding.checked.isChecked()) {
            Snackbar.make(binding.getRoot(),"You must agree to the terms and conditions",Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this,R.color.bronze)).show();
            return false;
        }

        return true;
    }
    private void register() {
        FirebaseAuthController.getInstance().createAccount(binding.etUserName.getText().toString(),
                binding.etEmail.getText().toString(),
                binding.etPassword.getText().toString(),
                new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
                        onBackPressed();
                    }

                    @Override
                    public void onFailure(String message) {
                        Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
                    }
                });
    }


}