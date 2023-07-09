package com.example.egar_admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.egar_admin.Model.Offer;
import com.example.egar_admin.Model.Product;
import com.example.egar_admin.Model.Provider;
import com.example.egar_admin.R;
import com.example.egar_admin.adapters.SpinnerAdapter;
import com.example.egar_admin.adapters.productShowProvider.ProductSpinnerAdapter;
import com.example.egar_admin.controllers.OfferController;
import com.example.egar_admin.controllers.ProductController;

import com.example.egar_admin.databinding.ActivityOfferBinding;
import com.example.egar_admin.interfaces.OnProductFetchListener;
import com.example.egar_admin.interfaces.ProcessCallback;
import com.example.egar_admin.interfaces.ProductCallback;
import com.example.egar_admin.ui.MainActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OfferActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityOfferBinding binding;
    OfferController offerController = new OfferController();

    List<Product> products =new ArrayList<>();

    SpinnerAdapter adapter;
    Offer offer ;

    Product product;
    String productName;

    //private ProductSpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOfferBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeView();


    }

    private void initializeView() {
        setOnClickListeners();
        initializeRecyclerAdapter();
        gCalendar();
        getIdProduct();
        getProduct();

    }

    private void setOnClickListeners() {
        binding.btnAddProductView.setOnClickListener(this);
    }

    private void initializeRecyclerAdapter() {
        adapter =new SpinnerAdapter(products,getBaseContext()) ;
        binding.spinnerProduct.setAdapter(adapter);
    }

    private void performSave() {
        if (checkData()) {
            addProductView();
        } else {
            Snackbar.make(binding.getRoot(), "Please enter Data , The Input Filed is Required", Snackbar.LENGTH_LONG).setTextColor(ContextCompat.getColor(getBaseContext(), R.color.bronze)).show();
        }
    }

    private boolean checkData() {
        String startDate = binding.etOfferStartDate.getText().toString();
        String endDate = binding.etOfferEndDate.getText().toString();
        String price = binding.etNewPrice.getText().toString();
        //String imageUrl = binding.editProductImage.getText().toString();
        //String quantityInCart = binding.editQuantity.getText().toString();
        if (startDate.isEmpty()) {
            binding.etOfferStartDate.setError("startDate field is Required");
            return false;
        } else if (endDate.isEmpty()) {
            binding.etOfferEndDate.setError("endDate field is Required");
            return false;
        } else if (price.isEmpty()) {
            binding.etNewPrice.setError("description field is Required");
            return false;
        }/*else if (true*//*imageUrl.isEmpty()*//*) {
           // binding.editProductImage.setError("image field is Required");
            return false;*/ /*else if (quantityInCart.isEmpty()) {
            binding.editQuantity.setError("Quantity field is Required");
            return false;
        }*/
        return true;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_addProduct_View){
            performSave();
        }
    }

    private void getProduct(){
        ProductController.getInstance().getAllProducts(FirebaseAuth.getInstance().getCurrentUser().getUid(), new OnProductFetchListener() {
            @Override
            public void onFetchLListSuccess(ArrayList<Product> list, String id) {

            }

            @Override
            public void onFetchSuccess(Product product) {

            }

            @Override
            public void onFetchFailure(String message) {

            }

            @Override
            public void onFetchListSuccess(ArrayList<Product> productList, String providerId) {
                products.addAll(productList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFetchNamesSuccess(ArrayList<String> productNames, String providerId) {

            }

        });
    }

    private void addProductView(){
        String startDate = binding.etOfferStartDate.getText().toString();
        String endDate = binding.etOfferEndDate.getText().toString();
        String price = binding.etNewPrice.getText().toString();
        offer = new Offer(product,Double.parseDouble(price),1,startDate,endDate);
        offerController.addOffer(offer, new ProcessCallback() {
            @Override
            public void onSuccess(String message) {
                onBackPressed();

            }

            @Override
            public void onFailure(String message) {

            }
        });
    }


    private void gCalendar(){
        Calendar now = Calendar.getInstance();

        binding.etOfferStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        binding.etOfferStartDate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);

                    }},
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection

                );
                dpd.show(getSupportFragmentManager(),"Datepickerdialog");


            }
        });

        binding.etOfferEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                                                                        @Override
                                                                        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                                                            binding.etOfferEndDate.setText(dayOfMonth+"/"+monthOfYear+"/"+year);

                                                                        }
                                                                    },
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection

                );
                dpd.show(getSupportFragmentManager(),"Datepickerdialog");


            }
        });

    }

    private void getIdProduct(){

        binding.spinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(OfferActivity.this, ""+products.get(position).getId(), Toast.LENGTH_SHORT).show();
                product = products.get(position);
                //productName = products.get(position).getName();
//                String serviceProviderId = products.get(position).getServiceProviderId();
//                String productName = products.get(position).getName();
//                String description = products.get(position).getDescription();
//                double price = products.get(position).getPrice();
//                String imageUrl = products.get(position).getImageUrl();
//                int quantityInCart = products.get(position).getQuantityInCart();
//                String category = products.get(position).getCategory();
//                Provider provider = products.get(position).getProvider();
//                product =new Product(serviceProviderId,productName,description,price,imageUrl,quantityInCart,category,provider);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}