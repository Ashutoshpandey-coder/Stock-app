package stock.register.godown.stock.management.shop.stock.ledger.ui.stock;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import stock.register.godown.stock.management.shop.stock.ledger.activities.MainActivity;
import stock.register.godown.stock.management.shop.stock.ledger.R;

import stock.register.godown.stock.management.shop.stock.ledger.adapters.StoreNameListAdapter;
import stock.register.godown.stock.management.shop.stock.ledger.adapters.StoreRequestListAdapter;
import stock.register.godown.stock.management.shop.stock.ledger.databinding.FragmentStockBinding;
import stock.register.godown.stock.management.shop.stock.ledger.models.StoreModelRequest;
import stock.register.godown.stock.management.shop.stock.ledger.utils.Constants;

public class StockFragment extends Fragment {

    private FragmentStockBinding binding;
    private final ArrayList<StoreModelRequest> storeList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) requireActivity()).setActionBarTitle(getString(R.string.title_stock));

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentStockBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        getStoreList();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showStoreListInUI(ArrayList<StoreModelRequest> storeList) {
        if (storeList.size() > 0) {
            binding.rvStoreNameList.setVisibility(View.VISIBLE);

            binding.rvStoreNameList.setLayoutManager(new LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false));
            binding.rvStoreNameList.setHasFixedSize(true);

            StoreNameListAdapter storeListAdapter = new StoreNameListAdapter(requireActivity(),storeList);
            binding.rvStoreNameList.setAdapter(storeListAdapter);
        }else{
            binding.rvStoreNameList.setVisibility(View.GONE);
        }
    }

    private void getStoreList() {
        String url = Constants.BASE_URL + Constants.GET_STORE +Constants.BUSINESS_ID_VALUE;

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        //getting the whole json object from the response
                        JSONObject obj = new JSONObject(response);

                        Log.e("Error ::::","Hello I am there.!");
                        Log.e("Error ::",response);

                        //we have the array named tutorial inside the object
                        //so here we are getting that json array
                        JSONArray tutorialsArray = obj.getJSONArray("response");

                        //now looping through all the elements of the json array
                        Log.e("arrayLength", String.valueOf(tutorialsArray.length()));

                        for (int i = 0; i < tutorialsArray.length(); i++) {
                            //getting the json object of the particular index inside the array
                            JSONObject tutorialsObject = tutorialsArray.getJSONObject(i);

                            String storeName = tutorialsObject.getString("name");
//                            String businessId = tutorialsObject.getString("businessId");

                            //creating a tutorial object and giving them the values from json object
                            StoreModelRequest model = new StoreModelRequest(storeName);

                            //adding the tutorial to tutoriallist
                            storeList.add(model);

                        }
                        Log.e("length", String.valueOf(storeList.size()));
                        showStoreListInUI(storeList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("Error Error :::",e.getMessage());
                    } catch (Exception e){
                        e.printStackTrace();
                        Log.e("Error Error :::",e.getMessage());
                    }
                },
                error -> {
                    //displaying the error in toast if occur
                    Toast.makeText(requireContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}