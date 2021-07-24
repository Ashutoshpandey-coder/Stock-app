package stock.register.godown.stock.management.shop.stock.ledger.ui.setting;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
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

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import stock.register.godown.stock.management.shop.stock.ledger.R;
import stock.register.godown.stock.management.shop.stock.ledger.activities.MainActivity;
import stock.register.godown.stock.management.shop.stock.ledger.databinding.FragmentSettingBinding;


public class SettingFragment extends Fragment {
    private FragmentSettingBinding binding;
    boolean clicked = true;
    final String TAG = "SettingFragment";


    public SettingFragment() {
  }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) requireActivity()).setActionBarTitle(getString(R.string.title_setting));
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.editFullNameBtn.setOnClickListener(v-> editText(binding.fullNameProfile,binding.editFullNameBtn,v));
        binding.editBusinessNameBtn.setOnClickListener(v-> editText(binding.businessNameProfile,binding.editBusinessNameBtn,v));
        extract();
        return view;
    }

    private void editText(EditText nameProfile, ImageButton editBtn, View v) {
            if(clicked){
                nameProfile.setFocusable(true);
                nameProfile.setFocusableInTouchMode(true);
                nameProfile.requestFocus();
                nameProfile.setCursorVisible(true);
                editBtn.setImageResource(R.drawable.ic_done);
                clicked=false;
            }
            else{
                try {
                    if(getActivity()!=null){
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);}
                }catch (Exception e){
                    e.printStackTrace();
                }
                nameProfile.setFocusable(false);
                nameProfile.setFocusableInTouchMode(false);
                nameProfile.setCursorVisible(false);
                nameProfile.clearFocus();
                editBtn.setImageResource(R.drawable.ic_baseline_edit_24);
                clicked = true;
                Log.d(TAG,nameProfile.getText().toString());
                postDataBusinessDetails();
            }
    }

    private void extract() {
        if (getActivity() != null) {
            String url = "http://fa73b9512530.ngrok.io/api/v1/business/getBusinessInfo/112233";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                //getting the whole json object from the response
                                Log.d(TAG,"RESPONSE: "+response);
                                JSONObject obj = new JSONObject(response);
                                binding.fullNameProfile.setText(obj.getString("fullName"));
                                binding.businessNameProfile.setText(obj.getString("businessName"));
                                String mobile = obj.getString("currencyCode")+" "+obj.getString("mobile");
                                binding.mobileProfile.setText(mobile);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("Error Error :::", e.getMessage());
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("Error Error :::", e.getMessage());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //displaying the error in toast if occur
                            Toast.makeText(requireContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            //creating a request queue
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

            //adding the string request to request queue
            requestQueue.add(stringRequest);
        }
    }
    private void postDataBusinessDetails() {

        if (getActivity() != null) {

            RequestQueue queue = Volley.newRequestQueue(getActivity());
            String url = "http://fa73b9512530.ngrok.io/api/v1/business/create";

            JSONObject params = new JSONObject();
            try {
                params.put("businessId","112233");
                params.put("businessName",binding.businessNameProfile.getText().toString());
                params.put("fullName",binding.fullNameProfile.getText().toString());
                params.put("mobile","1234567890");
                params.put("currencyCode","+91");
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
    }
    @Override
    public void onResume() {
        super.onResume();
        extract();
    }
}