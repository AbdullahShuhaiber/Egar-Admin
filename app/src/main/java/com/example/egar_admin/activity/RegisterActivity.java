package com.example.egar_admin.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;


import com.example.egar_admin.FirebaseManger.FirebaseAuthController;
import com.example.egar_admin.R;
import com.example.egar_admin.adapters.MyFragmentAdapter;


import com.example.egar_admin.databinding.ActivityRegisterBinding;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.example.egar_admin.ui.MainActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityRegisterBinding binding;
    private MyFragmentAdapter adapter;


    private Uri pickedImageUri;

    private ActivityResultLauncher<Void> cameraRL;
    private ActivityResultLauncher<String> permissionRL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        screenOperations();


    }

    private void screenOperations (){
        setOnClick();
        setTitle("REGISTER");
        setupActivityResults();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.active)));
        getWindow().setStatusBarColor(ContextCompat.getColor(RegisterActivity.this,R.color.active));
        List<String> gazaGovernorates = new ArrayList<>();
        gazaGovernorates.add("محافظة شمال غزة");
        gazaGovernorates.add(" محافظة غزة");
        gazaGovernorates.add("محافظة الوسطى");
        gazaGovernorates.add(" محافظة خان يونس");
        gazaGovernorates.add("محافظة رفح");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, gazaGovernorates);
        binding.spinner.setAdapter(adapter);

    }
    private boolean isValidPalestinianPhoneNumber() {
        String phoneNumber = binding.etPhone.getText().toString().trim();
        if (phoneNumber.matches("^(059|056)\\d{7}$")) {
            return true;
        } else {
            Toast.makeText(this, "Enter A Valid Palestinian Number", Toast.LENGTH_SHORT).show();
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
        String name = binding.etName.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPass.getText().toString().trim();

        if (name.isEmpty()) {
            binding.etName.setError("UserName field is Required");
            return false;
        } else if (email.isEmpty()  ) {
            binding.etEmail.setError("Email field is Required");
            return false;
        } else if (password.isEmpty()) {
            binding.etPhone.setError("Password field is Required");
            return false;
        }else if(!binding.checked.isChecked()) {
            Snackbar.make(binding.getRoot(),"You must agree to the terms and conditions",Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(this,R.color.bronze)).show();
            return false;
        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void setOnClick(){

        binding.bnRegister.setOnClickListener(this::onClick);
        binding.btnBack.setOnClickListener(this::onClick);
        binding.imageRegister.setOnClickListener(this::onClick);
        binding.animationView.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bn_register:
                if (dataCheck() && isValidPalestinianPhoneNumber() && isValidEmail()) {
                    register();

                }
                break;
            case R.id.btn_back:
                Intent intent2 = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent2);

                break;
            case R.id.animationView:
                selectImage();
                break;
        }
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
    public String getSelectedChipText() {

        int checkedChipId = binding.chipGroup.getCheckedChipId();

        if (checkedChipId != View.NO_ID) {
            Chip checkedChip = binding.chipGroup.findViewById(checkedChipId);
            return checkedChip.getText().toString();
        } else {
            Snackbar.make(binding.getRoot(),"Select The Store Type ",Snackbar.LENGTH_LONG).show();
            return null;
        }
    }


    private void register() {
        String selectedGovernorate = binding.spinner.getSelectedItem().toString();

        FirebaseAuthController.getInstance().createAccount(
                binding.etName.getText().toString().trim(),
                binding.etEmail.getText().toString().trim(),
                binding.etPass.getText().toString().trim(),
                binding.etPhone.getText().toString().trim(),
                getSelectedChipText(),
                selectedGovernorate,
                "Gaza",
                "End To The End Services And Products",
                pickedImageUri,
                new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                                String email = binding.etEmail.getText().toString().trim();
                                String pass = binding.etPass.getText().toString().trim();
                                intent1.putExtra("email", email);
                                intent1.putExtra("password", pass);
                                startActivity(intent1);
                            }
                        },1222);


                    }

                    @Override
                    public void onFailure(String message) {
                        Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
                    }
                });
    }



    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null) {
            pickedImageUri = data.getData();
            binding.imageRegister.setImageURI(pickedImageUri);
            binding.imageRegister.setVisibility(View.VISIBLE);
            binding.animationView.setVisibility(View.GONE);

        }
    }

    private void setupActivityResults() {
        permissionRL = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    cameraRL.launch(null);

                }
            }
        });

    }

    private void pickImage() {
        permissionRL.launch(Manifest.permission.CAMERA);
    }

}