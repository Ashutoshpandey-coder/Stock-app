package bhandar.stock.ledger.stock.management;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;


import bhandar.stock.ledger.stock.management.databinding.ActivityAddStoreAndPartyBinding;
import bhandar.stock.ledger.stock.management.models.PartyModelRequest;

public class AddStoreAndPartyActivity extends AppCompatActivity {
    private ActivityAddStoreAndPartyBinding binding;
    private Boolean isStoreOrParty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddStoreAndPartyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (getIntent().hasExtra("Add store boolean")){
            isStoreOrParty = getIntent().getBooleanExtra("Add store boolean",false);
        }

        if (getIntent().hasExtra("Add party boolean")){
            isStoreOrParty = getIntent().getBooleanExtra("Add party boolean",true);
        }
        if (isStoreOrParty){
            binding.llAddParty.setVisibility(View.VISIBLE);
            binding.llAddStore.setVisibility(View.GONE);
            setUpActionBar(getString(R.string.btn_text_add_party));
            binding.toolbarTitle.setText(getString(R.string.btn_text_add_party));

        }else{
            binding.llAddParty.setVisibility(View.GONE);
            binding.llAddStore.setVisibility(View.VISIBLE);
            setUpActionBar(getString(R.string.btn_text_add_store));
            binding.toolbarTitle.setText(getString(R.string.btn_text_add_store));
        }

        binding.btnSave.setOnClickListener(v -> {
            if (isStoreOrParty){
                if (validateAddPartyForm()){
                    String partyName = binding.etEnterCustomerName.getText().toString().trim();
                    String partyNumber = binding.etMobileNumber.getText().toString().trim();

                    PartyModelRequest model = new PartyModelRequest(partyName,partyNumber,"");
//                    Intent intent = new Intent(this, MainActivity.class);
//                    intent.putExtra("partyModel",model);
//                    setResult(Activity.RESULT_OK);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    finish();
//                    PartyFragment partyFragment = new PartyFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putParcelable("partyModel",model);
//                    setResult(Activity.RESULT_OK);
//                    partyFragment.setArguments(bundle);
//                    getSupportFragmentManager().beginTransaction()
//                            .add(R.id.container,partyFragment)
//                            .commit();
                    Intent intent = new Intent(AddStoreAndPartyActivity.this,MainActivity.class);
                    intent.putExtra("partyModel",model);
                    intent.putExtra("addStoreToParty",true);
                    setResult(Activity.RESULT_OK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }else{
                if (validateAddStoreForm()){
                    Toast.makeText(AddStoreAndPartyActivity.this, "Saved your store.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    private boolean validateAddStoreForm(){
        if (TextUtils.isEmpty(binding.etEnterStoreName.toString())){
            Toast.makeText(this, "Please enter store name.", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
    private boolean validateAddPartyForm(){
        if (TextUtils.isEmpty(binding.etEnterCustomerName.toString())){
            Toast.makeText(this, "Please enter store name.", Toast.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(binding.etMobileNumber.toString()) && binding.etMobileNumber.toString().length() != 10){
            Toast.makeText(this, "Please enter valid mobile number.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
    private void  setUpActionBar(String title){
        setSupportActionBar(binding.toolbarAddStoreAndPartyActivity);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_pressed_arrow_white_24);
        getSupportActionBar().setTitle(title);

        binding.toolbarAddStoreAndPartyActivity.setNavigationOnClickListener(v -> onBackPressed());

    }
}