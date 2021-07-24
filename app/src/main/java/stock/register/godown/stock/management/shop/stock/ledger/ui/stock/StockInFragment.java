package bhandar.stock.ledger.stock.management.ui.stock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bhandar.stock.ledger.stock.management.MainActivity;
import bhandar.stock.ledger.stock.management.R;
import bhandar.stock.ledger.stock.management.adapters.PartyRequestListAdapter;
import bhandar.stock.ledger.stock.management.adapters.StoreNameListAdapter;
import bhandar.stock.ledger.stock.management.databinding.FragmentStockBinding;
import bhandar.stock.ledger.stock.management.databinding.FragmentStockInBinding;
import bhandar.stock.ledger.stock.management.models.PartyModelRequest;
import bhandar.stock.ledger.stock.management.models.StoreModelRequest;

public class StockInFragment extends Fragment implements StoreNameListAdapter.OnStoreNameListener,PartyRequestListAdapter.OnPartyNameListener {

    private FragmentStockInBinding binding;
    final String TAG = "StockInFragment";
    private final ArrayList<StoreModelRequest> storeList = new ArrayList<>();
    private final ArrayList<PartyModelRequest> partyList = new ArrayList<>();
    private final String stockItemId="1234";
    private String txnEntityId;
    private String stockTxnEntity="";
    private final String txnType = "IN";
    private final String preTxnCnt = "400";
    private String postTxnCnt = "";
    int txnUnitPrice = 0;

    public StockInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) requireActivity()).setActionBarTitle(getString(R.string.toolbar_stock_in_self));
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStockInBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.storeListRv.setVisibility(View.GONE);
        binding.partyListRv.setVisibility(View.GONE);
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) root.findViewById(checkedId);
                Log.e(TAG,radioButton.getText().toString());
                String entity = radioButton.getText().toString();
                if(entity.equals("Self")){
                    stockTxnEntity = "STORE";
                    binding.stockInSelfLayout.setVisibility(View.VISIBLE);
                    binding.stockInCustomerLayout.setVisibility(View.GONE);
                    binding.storeNameDropdown.setOnClickListener(v -> {
                        binding.storeListRv.setVisibility(View.VISIBLE);
                        getStoreList();
                    });
                }
                else if(entity.equals("Customer")){
                    stockTxnEntity = "PARTY";
                    binding.stockInSelfLayout.setVisibility(View.GONE);
                    binding.stockInCustomerLayout.setVisibility(View.VISIBLE);
                    binding.customerName.setOnClickListener(v -> {
                        binding.partyListRv.setVisibility(View.VISIBLE);
                        getPartyList();
                    });
                }
                else{
                    stockTxnEntity = "";
                    binding.stockInSelfLayout.setVisibility(View.GONE);
                    binding.stockInCustomerLayout.setVisibility(View.GONE);
                }
            }
        });
//        if(binding.radioGroup.getCheckedRadioButtonId()!=-1){
//        int selectedId = binding.radioGroup.getCheckedRadioButtonId();
//        RadioButton radioButton = (RadioButton) root.findViewById(selectedId);
//        Log.e(TAG,radioButton.getText().toString());}

        binding.addStock.setOnClickListener(v -> {
            if (stockTxnEntity.equals("STORE")) {
                if (!binding.quantitySelf.getText().toString().isEmpty()) {
                    int post = Integer.parseInt(preTxnCnt) + Integer.parseInt(binding.quantitySelf.getText().toString());
                    postTxnCnt = String.valueOf(post);
                    txnUnitPrice = 0;
                    String transactionTs = String.valueOf(System.currentTimeMillis());
                    if(binding.storeNameDropdown.getText().toString().equals("Add store name")){
                        Toast.makeText(getActivity(),"Select a store",Toast.LENGTH_SHORT).show();
                    }
                    else
                        createTransaction(stockItemId, transactionTs, txnType, preTxnCnt, postTxnCnt, txnUnitPrice, stockTxnEntity, txnEntityId);
                } else
                    binding.quantityCust.setError("Cannot be empty");
            }
            else if(stockTxnEntity.equals("PARTY")){
                if (!binding.quantityCust.getText().toString().isEmpty()) {
                int post = Integer.parseInt(preTxnCnt)+Integer.parseInt(binding.quantityCust.getText().toString());
                postTxnCnt = String.valueOf(post);
                txnUnitPrice = Integer.parseInt(binding.purchasePrice.getText().toString());
                String transactionTs = String.valueOf(System.currentTimeMillis());
                if(binding.customerName.getText().toString().equals("Enter customer name here")){
                    Toast.makeText(getActivity(),"Select a party",Toast.LENGTH_SHORT).show();
                }
                else
                    createTransaction(stockItemId,transactionTs,txnType,preTxnCnt,postTxnCnt,txnUnitPrice,stockTxnEntity,txnEntityId);
                }
                else
                    binding.quantityCust.setError("Cannot be empty");
            }
            else{
                if(getActivity()!=null)
                Toast.makeText(getActivity(),"Choose one",Toast.LENGTH_SHORT).show();
            }
        });

        binding.purchasePrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    int price = Integer.parseInt(s.toString());
                    if (binding.quantityCust.getText().toString().equals(""))
                        binding.quantityCust.setError("Enter a quantity");
                    else {
                        int quantity = Integer.parseInt(binding.quantityCust.getText().toString());
                        int total = price*quantity;
                        binding.totalAmount.setText(String.valueOf(total));
                    }
                }
                else{
                    binding.purchasePrice.setError("Enter price");
                    binding.totalAmount.setText("0");

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.quantityCust.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    int quantity = Integer.parseInt(s.toString());
                    if (binding.purchasePrice.getText().toString().equals(""))
                        binding.purchasePrice.setError("Enter price");
                    else {
                        int price = Integer.parseInt(binding.purchasePrice.getText().toString());
                        int total = price*quantity;
                        binding.totalAmount.setText(String.valueOf(total));
                    }
                }
                else{
                    binding.quantityCust.setError("Enter a quantity");
                    binding.totalAmount.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return root;
    }

    private void createTransaction(String stockItemId, String transactionTs, String txnType, String preTxnCnt, String postTxnCnt, int txnUnitPrice, String stockTxnEntity, String txnEntityId) {
        if (getActivity() != null) {

            RequestQueue queue = Volley.newRequestQueue(getActivity());
            String url = "http://b4cfbff35b88.ngrok.io/api/v1/stockTransaction/createTransaction";

            JSONObject params = new JSONObject();
            try {
                params.put("stockItemId",stockItemId);
                params.put("transactionTs",transactionTs);
                params.put("txnType",txnType);
                params.put("preTxnStockCount",preTxnCnt);
                params.put("postTxnStockCount",postTxnCnt);
                params.put("txnUnitPrice",txnUnitPrice);
                params.put("stockTxnEntity",stockTxnEntity);
                params.put("sourceTxnEntityId",txnEntityId);

                Log.e("sending ", params.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //progressDialog.dismiss();
                    Log.e("RESPONSE ::", String.valueOf(response));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("newDoc2", error.toString());
                    Log.e("Error :::", String.valueOf(error));
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
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
        Navigation.findNavController(requireView()).navigate(R.id.fragment_stock_in_to_fragment_stock);
    }

    private void showStoreListInUI(ArrayList<StoreModelRequest> storeList) {
        if (storeList.size() > 0) {
            binding.storeListRv.setVisibility(View.VISIBLE);

            binding.storeListRv.setLayoutManager(new LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false));
            binding.storeListRv.setHasFixedSize(true);

            StoreNameListAdapter storeListAdapter = new StoreNameListAdapter(requireActivity(),storeList,this);
            binding.storeListRv.setAdapter(storeListAdapter);
        }else{
            binding.storeListRv.setVisibility(View.GONE);
        }
    }
    private void showPartyListInUI(ArrayList<PartyModelRequest> partyList) {
        if (partyList.size() > 0) {
            binding.partyListRv.setVisibility(View.VISIBLE);

            binding.partyListRv.setLayoutManager(new LinearLayoutManager(requireActivity()));
            binding.partyListRv.setHasFixedSize(true);

            PartyRequestListAdapter partyListAdapter = new PartyRequestListAdapter(requireActivity(),partyList,this);
            binding.partyListRv.setAdapter(partyListAdapter);
        }else{
            binding.partyListRv.setVisibility(View.GONE);
        }
    }
    private void getStoreList() {
        storeList.clear();
        String url = "http://b4cfbff35b88.ngrok.io/api/v1/store/getStore/11111";

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

                            String storeId = tutorialsObject.getString("storeId");
                            String storeName = tutorialsObject.getString("name");
//                            String businessId = tutorialsObject.getString("businessId");

                            //creating a tutorial object and giving them the values from json object
                            StoreModelRequest model = new StoreModelRequest(storeId,storeName,"11111");

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
    private void getPartyList() {
        partyList.clear();
        String url = "http://b4cfbff35b88.ngrok.io/api/v1/party/getParty/11111";

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
                            String partyId = tutorialsObject.getString("partyId");
                            PartyModelRequest model = new PartyModelRequest(partyId,partyName,"123","11111");

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

    @Override
    public void onStoreClick(int position) {
        binding.storeNameDropdown.setText(storeList.get(position).getName());
        txnEntityId = storeList.get(position).getStoreId();
        Log.e(TAG,"Stock ID: "+txnEntityId);
        binding.storeListRv.setVisibility(View.GONE);
    }

    @Override
    public void onPartyClick(int position) {
        binding.customerName.setText(partyList.get(position).getPartyName());
        txnEntityId = partyList.get(position).getPartyId();
        binding.partyListRv.setVisibility(View.GONE);
    }
}