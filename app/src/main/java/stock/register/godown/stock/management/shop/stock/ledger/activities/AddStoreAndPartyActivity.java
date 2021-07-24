package stock.register.godown.stock.management.shop.stock.ledger.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import stock.register.godown.stock.management.shop.stock.ledger.R;

import stock.register.godown.stock.management.shop.stock.ledger.databinding.ActivityAddStoreAndPartyBinding;
import stock.register.godown.stock.management.shop.stock.ledger.utils.Constants;

public class AddStoreAndPartyActivity extends AppCompatActivity {
    private ActivityAddStoreAndPartyBinding binding;
    private Boolean isStoreOrParty;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddStoreAndPartyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent().hasExtra(Constants.ADD_STORE_BOOLEAN)) {
            isStoreOrParty = getIntent().getBooleanExtra(Constants.ADD_STORE_BOOLEAN, false);
        }

        if (getIntent().hasExtra(Constants.ADD_PARTY_BOOLEAN)) {
            isStoreOrParty = getIntent().getBooleanExtra(Constants.ADD_PARTY_BOOLEAN, true);
        }
        if (isStoreOrParty) {
            binding.llAddParty.setVisibility(View.VISIBLE);
            binding.llAddStore.setVisibility(View.GONE);
            setUpActionBar(getString(R.string.btn_text_add_party));
            binding.toolbarTitle.setText(getString(R.string.btn_text_add_party));

        } else {
            binding.llAddParty.setVisibility(View.GONE);
            binding.llAddStore.setVisibility(View.VISIBLE);
            setUpActionBar(getString(R.string.btn_text_add_store));
            binding.toolbarTitle.setText(getString(R.string.btn_text_add_store));
        }

        binding.btnSave.setOnClickListener(v -> {
            if (isStoreOrParty) {
                if (validateAddPartyForm()) {
                    String partyName = binding.etEnterCustomerName.getText().toString().trim();
                    String partyNumber = binding.etMobileNumber.getText().toString().trim();
                    createParty(partyName,partyNumber,Constants.BUSINESS_ID_VALUE);
                    onBackPressed();
                }
            } else {
                if (validateAddStoreForm()) {
                    String storeName = binding.etEnterStoreName.getText().toString().trim();
                    createStore(storeName,Constants.BUSINESS_ID_VALUE);
                    onBackPressed();
                }
            }
        });


        binding.btnAddFromContacts.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(AddStoreAndPartyActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                openContactList();
            } else {
                String[] requestPermission = {Manifest.permission.READ_CONTACTS};
                ActivityCompat.requestPermissions(AddStoreAndPartyActivity.this, requestPermission, Constants.CONTACTS_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.CONTACTS_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openContactList();
        } else {
            Toast.makeText(this, getString(R.string.error_message_denied_request), Toast.LENGTH_SHORT).show();
        }
    }


    @SuppressWarnings("deprecation")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.CONTACTS_RESULT_CODE) {
            if (Objects.requireNonNull(data).getData() != null) {
                Uri contactData = data.getData();
                Cursor c =  managedQuery(contactData, null, null, null, null);
                if (c.moveToFirst()) {

                    String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                    String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                    if (hasPhone.equalsIgnoreCase("1")) {
                        Cursor phones = getContentResolver().query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,
                                null, null);
                        phones.moveToFirst();
                        String cNumber = phones.getString(phones.getColumnIndex("data1"));
                        binding.etMobileNumber.setText(cNumber);
                    }
                    String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    binding.etEnterCustomerName.setText(name);


                }
            }
            }
        }


    @SuppressWarnings("deprecation")
    private void openContactList() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, Constants.CONTACTS_RESULT_CODE);
    }

    private boolean validateAddStoreForm() {
        if (TextUtils.isEmpty(binding.etEnterStoreName.toString())) {
            Toast.makeText(this, getString(R.string.error_mssg_enter_store_name), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateAddPartyForm() {
        if (TextUtils.isEmpty(binding.etEnterCustomerName.toString())) {
            Toast.makeText(this, getString(R.string.please_enter_party_name_mssg), Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(binding.etMobileNumber.toString()) && binding.etMobileNumber.toString().length() != 10) {
            Toast.makeText(this, getString(R.string.mssg_enter_valid_mobile_number), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void setUpActionBar(String title) {
        setSupportActionBar(binding.toolbarAddStoreAndPartyActivity);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_pressed_arrow_white_24);
        getSupportActionBar().setTitle(title);
        binding.toolbarAddStoreAndPartyActivity.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void createParty(String name,String mobile,String businessId) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + Constants.CREATE_PARTY;
        JSONObject params = new JSONObject();
        try {
            params.put(Constants.NAME, name);
            params.put(Constants.PARTY_MOBILE_NUMBER, mobile);
            params.put(Constants.BUSINESS_ID, businessId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, response -> {
            }, error -> {
            Log.e("Error :", String.valueOf(error));
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Connection", "close");
                return headers;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    private void createStore(String name,String businessId){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.BASE_URL + Constants.CREATE_STORE;
        JSONObject params = new JSONObject();
        try {
            params.put(Constants.NAME, name);
            params.put(Constants.BUSINESS_ID, businessId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, response -> {

        }, error -> {
            Log.e("Error :", String.valueOf(error));
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Connection", "close");
                return headers;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }
}

