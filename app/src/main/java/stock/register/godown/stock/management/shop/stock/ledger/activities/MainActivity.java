package stock.register.godown.stock.management.shop.stock.ledger.activities;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;

import stock.register.godown.stock.management.shop.stock.ledger.R;

import stock.register.godown.stock.management.shop.stock.ledger.databinding.ActivityMainBinding;
import stock.register.godown.stock.management.shop.stock.ledger.models.PartyModelRequest;
import stock.register.godown.stock.management.shop.stock.ledger.models.StoreModelRequest;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    //BottomNavigationView bottomNavigationView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.primaryTextColor));

        //bottomNavigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navController = Navigation.findNavController(this, R.id.frame_layout);
        NavigationUI.setupWithNavController(binding.navView, navController);

//        assert navHostFragment != null;
//        NavController navController = navHostFragment.getNavController();
//        NavigationUI.setupWithNavController(binding.navView, navController);


        if (getIntent().hasExtra("addStoreToParty")) {
            boolean storeToParty = getIntent().getBooleanExtra("addStoreToParty", false);
            if (storeToParty) {
                PartyModelRequest model = getIntent().getParcelableExtra("partyModel");
                Bundle bundle = new Bundle();
                bundle.putParcelable("partyModel", model);
                bundle.putBoolean("isPartyOrStore",true);
                navController.navigate(R.id.navigation_party, bundle);
            } else {
                StoreModelRequest model = getIntent().getParcelableExtra("storeModel");
                Bundle bundle = new Bundle();
                bundle.putParcelable("storeModel", model);
                navController.navigate(R.id.navigation_party, bundle);

            }
        }
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}