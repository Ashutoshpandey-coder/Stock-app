package stock.register.godown.stock.management.shop.stock.ledger.ui.items;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import stock.register.godown.stock.management.shop.stock.ledger.R;
import stock.register.godown.stock.management.shop.stock.ledger.activities.MainActivity;
import stock.register.godown.stock.management.shop.stock.ledger.adapters.AddMoreViewsListAdapter;
import stock.register.godown.stock.management.shop.stock.ledger.databinding.FragmentAddItemsBinding;
import stock.register.godown.stock.management.shop.stock.ledger.databinding.FragmentItemsBinding;
import stock.register.godown.stock.management.shop.stock.ledger.models.StockStoreDetails;
import stock.register.godown.stock.management.shop.stock.ledger.utils.Constants;

public class AddItemsFragment extends Fragment {
    private FragmentAddItemsBinding binding;
    List<StockStoreDetails> stockStoreDetailsList = new ArrayList<>();
    List<StockStoreDetails> stockStoreDetailsListApi = new ArrayList<>();
    AddMoreViewsListAdapter addMoreAdapter;

    public AddItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) requireActivity()).setActionBarTitle(getString(R.string.title_add_items));
        //Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //Objects.requireNonNull(((MainActivity) requireActivity()).setHomeAsUpIndicator(R.drawable.ic_back_pressed_arrow_white_24);

        //binding.toolbarAddItemActivity.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddItemsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i < AddMoreViewsListAdapter.itemsList.size();i++){
                    StockStoreDetails stockDetails = AddMoreViewsListAdapter.itemsList.get(i);
                    Log.e("Id:: ",stockDetails.getStoreId());
                    Log.e("storeName:: ",stockDetails.getAvailableCount());
                    Log.e("date:: ",stockDetails.getStockAddDate());
                    String storeId = stockDetails.getStoreId();
                    String availableCount = stockDetails.getAvailableCount();
                    String date = stockDetails.getStockAddDate();
                    stockStoreDetailsListApi.add(new StockStoreDetails(storeId,availableCount,date));
//                    stockStoreDetailsListApi.add(new StockStoreDetails(stockDetails.getStoreId(),
//                            stockDetails.getAvailableCount(), stockDetails.getStockAddDate()));
                }
                Log.e("STOCK", stockStoreDetailsListApi.toString());
//                stockStoreDetailsListApi.add(new StockStoreDetails("1111","100","22 dec 2021"));
//                stockStoreDetailsListApi.add(new StockStoreDetails("1111","100","22 dec 2021"));
//                stockStoreDetailsListApi.add(new StockStoreDetails("1111","100","22 dec 2021"));
                createStockItem("ram","100","200","300","50",stockStoreDetailsListApi);

            }
        });
        if(getActivity()!=null) {
            binding.rvAddStockFromOtherStoreList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            binding.rvAddStockFromOtherStoreList.setHasFixedSize(true);
            binding.rvAddStockFromOtherStoreList.setNestedScrollingEnabled(false);

            addMoreAdapter = new AddMoreViewsListAdapter(getActivity(), stockStoreDetailsList);
            binding.rvAddStockFromOtherStoreList.setAdapter(addMoreAdapter);

            StockStoreDetails addMoreView = new StockStoreDetails("", "", "");
            stockStoreDetailsList.add(addMoreView);
            addMoreAdapter.notifyDataSetChanged();

            binding.tvAddStockFromAnotherStore.setOnClickListener(v -> {
                stockStoreDetailsList.add(new StockStoreDetails("", "", ""));
                addMoreAdapter.notifyDataSetChanged();
            });
        }
        return root;
    }

    private void createStockItem(String name,String purchasePrice,String sellingPrice,String mrp, String priceUnit,List<StockStoreDetails> stockStoreDetailsList) {

        if (getActivity() != null) {
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            String url = Constants.BASE_URL + Constants.CREATE_STOCK_ITEM;
            JSONObject params = new JSONObject();
            try {
                params.put(Constants.NAME, name);
                params.put(Constants.PURCHASE_PRICE, purchasePrice);
                params.put(Constants.SELLING_PRICE, sellingPrice);
                params.put(Constants.MRP, mrp);
                params.put(Constants.PRICE_UNIT, priceUnit);
                //params.put(Constants.STOCK_STORE_DETAILS, stockStoreDetailsList);
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < stockStoreDetailsList.size(); i++) {
                    JSONObject stockDets = new JSONObject();
                    stockDets.put("storeId", stockStoreDetailsList.get(i).getStoreId());
                    stockDets.put("availableCount", stockStoreDetailsList.get(i).getAvailableCount());
                    stockDets.put("stockAddDate", stockStoreDetailsList.get(i).getStockAddDate());
                    jsonArray.put(stockDets);
                }
                params.put(Constants.STOCK_STORE_DETAILS, jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Error :", String.valueOf(e));
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, response -> {
                Log.e("Response ::: ", response.toString());
                Toast.makeText(requireContext(), "data posted successfully.", Toast.LENGTH_SHORT).show();
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
        Navigation.findNavController(requireView()).navigate(R.id.fragment_add_items_to_fragment_items);
    }
}