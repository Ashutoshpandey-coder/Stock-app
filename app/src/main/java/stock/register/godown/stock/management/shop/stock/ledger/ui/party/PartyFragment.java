package stock.register.godown.stock.management.shop.stock.ledger.ui.party;

import android.content.Intent;
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

import stock.register.godown.stock.management.shop.stock.ledger.R;
import stock.register.godown.stock.management.shop.stock.ledger.activities.AddStoreAndPartyActivity;
import stock.register.godown.stock.management.shop.stock.ledger.activities.MainActivity;
import stock.register.godown.stock.management.shop.stock.ledger.adapters.PartyRequestListAdapter;
import stock.register.godown.stock.management.shop.stock.ledger.adapters.StoreRequestListAdapter;

import stock.register.godown.stock.management.shop.stock.ledger.databinding.FragmentPartyBinding;
import stock.register.godown.stock.management.shop.stock.ledger.models.PartyModelRequest;
import stock.register.godown.stock.management.shop.stock.ledger.models.StoreModelRequest;
import stock.register.godown.stock.management.shop.stock.ledger.utils.Constants;

public class PartyFragment extends Fragment {

    private FragmentPartyBinding binding;
    private final ArrayList<PartyModelRequest> partyList = new ArrayList<>();
    private final ArrayList<StoreModelRequest> storeList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) requireActivity()).setActionBarTitle(getString(R.string.title_party));

//        getPartyList();
//        getStoreList();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPartyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.btnAddStore.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), AddStoreAndPartyActivity.class);
            intent.putExtra(Constants.ADD_STORE_BOOLEAN, false);
            startActivity(intent);
        });


        binding.btnAddParty.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), AddStoreAndPartyActivity.class);
            intent.putExtra(Constants.ADD_PARTY_BOOLEAN, true);
            startActivity(intent);
        });

        Bundle bundle = this.getArguments();
        if(bundle!=null){

            boolean isPartyOrStore  = bundle.getBoolean("isPartyOrStore",false);
            if (isPartyOrStore) {
                showPartyListInUI(partyList);
            }else{
                showStoreListInUI(storeList);

            }
        }
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showPartyListInUI(ArrayList<PartyModelRequest> partyList) {
        if (partyList.size() > 0) {
            binding.rvAllPartiesList.setVisibility(View.VISIBLE);
            binding.tvNoPartyYet.setVisibility(View.GONE);

            binding.rvAllPartiesList.setLayoutManager(new LinearLayoutManager(requireActivity()));
            binding.rvAllPartiesList.setHasFixedSize(true);

            PartyRequestListAdapter partyListAdapter = new PartyRequestListAdapter(requireActivity(),partyList);
            binding.rvAllPartiesList.setAdapter(partyListAdapter);
        }else{
            binding.rvAllPartiesList.setVisibility(View.GONE);
            binding.tvNoPartyYet.setVisibility(View.VISIBLE);
        }
    }

    private void showStoreListInUI(ArrayList<StoreModelRequest> storeList) {
        if (storeList.size() > 0) {
            binding.rvAllStoresList.setVisibility(View.VISIBLE);
            binding.tvNoStoreYet.setVisibility(View.GONE);

            binding.rvAllStoresList.setLayoutManager(new LinearLayoutManager(requireActivity()));
            binding.rvAllStoresList.setHasFixedSize(true);

            StoreRequestListAdapter storeListAdapter = new StoreRequestListAdapter(requireActivity(),storeList);
            binding.rvAllStoresList.setAdapter(storeListAdapter);
        }else{
            binding.rvAllStoresList.setVisibility(View.GONE);
            binding.tvNoStoreYet.setVisibility(View.VISIBLE);
        }
    }

    private void getStoreList() {
        String url  = Constants.BASE_URL + Constants.GET_STORE + Constants.BUSINESS_ID_VALUE;

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        //getting the whole json object from the response
                        JSONObject obj = new JSONObject(response);

                        //so here we are getting that json array
                        JSONArray tutorialsArray = obj.getJSONArray("response");

                        //now looping through all the elements of the json array
                        Log.e("arrayLength", String.valueOf(tutorialsArray.length()));

                        for (int i = 0; i < tutorialsArray.length(); i++) {
                            //getting the json object of the particular index inside the array
                            JSONObject tutorialsObject = tutorialsArray.getJSONObject(i);

                            String storeName = tutorialsObject.getString("name");

                           StoreModelRequest model = new StoreModelRequest(storeName);

                            storeList.add(model);

                        }
                        showStoreListInUI(storeList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("Error ::",e.getMessage());
                    } catch (Exception e){
                        e.printStackTrace();
                        Log.e("Error ::",e.getMessage());
                    }
                },
                error -> {
                    Toast.makeText(requireContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

    private void getPartyList() {
        String url = Constants.BASE_URL + Constants.GET_PARTY + Constants.BUSINESS_ID_VALUE;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        //getting the whole json object from the response
                        JSONObject obj = new JSONObject(response);
                        //so here we are getting that json array
                        JSONArray tutorialsArray = obj.getJSONArray("response");

                        //now looping through all the elements of the json array
                        for (int i = 0; i < tutorialsArray.length(); i++) {
                            //getting the json object of the particular index inside the array
                            JSONObject tutorialsObject = tutorialsArray.getJSONObject(i);

                            String partyName = tutorialsObject.getString("name");
                            PartyModelRequest model = new PartyModelRequest(partyName);

                            partyList.add(model);

                        }
                        showPartyListInUI(partyList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("Error Error :::",e.getMessage());
                    } catch (Exception e){
                        e.printStackTrace();
                        Log.e("Error Error :::",e.getMessage());
                    }
                },
                error -> {
                    Toast.makeText(requireContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }


}