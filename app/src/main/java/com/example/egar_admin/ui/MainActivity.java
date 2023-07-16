package com.example.egar_admin.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import android.view.Menu;
import android.view.View;
import android.widget.Toast;


import com.example.egar_admin.BroadcastReceivers.NetworkChangeListiners;
import com.example.egar_admin.FirebaseManger.FirebaseAuthController;
import com.example.egar_admin.R;


import com.example.egar_admin.activity.LoginActivity;
import com.example.egar_admin.databinding.ActivityMainBinding;
import com.example.egar_admin.dialog.LoadingDialog;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    NetworkChangeListiners networkChangeListiners = new NetworkChangeListiners();

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new LoadingDialog(this);
        setUpDialog();
//        String uid = FirebaseAuth.getInstance().getUid();
//        if (uid != null) {
//            Snackbar.make(binding.getRoot(), uid, Snackbar.LENGTH_LONG).show();
//        } else {
//            Snackbar.make(binding.getRoot(), "User ID not available", Snackbar.LENGTH_LONG).show();
//        }
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_orders , R.id.nav_add_product,R.id.nav_settings,R.id.nav_Conditions,R.id.nav_WhoAreWe)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }
    private void setUpDialog(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.cancel();

            }
        },3500);
    }


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dialog.show();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListiners, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkChangeListiners);

    }
    /*    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logOut) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }*/


    private void logout() {
        FirebaseAuthController.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}