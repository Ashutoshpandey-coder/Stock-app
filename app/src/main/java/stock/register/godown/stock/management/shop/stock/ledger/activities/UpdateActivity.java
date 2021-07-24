package stock.register.godown.stock.management.shop.stock.ledger.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import stock.register.godown.stock.management.shop.stock.ledger.R;
import stock.register.godown.stock.management.shop.stock.ledger.databinding.UpdateActivityBinding;
import stock.register.godown.stock.management.shop.stock.ledger.ui.items.ItemsFragment;

public class UpdateActivity extends AppCompatActivity {
    private UpdateActivityBinding binding;
    String name, purchase, selling, mrp, Unit, stockItemID, url;
    int availableStock;
    TextView unit,AvailableStockValue,AvailableStockValueUnit;
    ImageView UnitSelector;
    Dialog dialog;
    Button save;
    EditText NameValue,PurchaseValue,SellingValue,Mrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UpdateActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        name = getIntent().getStringExtra("name");
        purchase = getIntent().getStringExtra("purchasePrice");
        selling = getIntent().getStringExtra("sellingPrice");
        mrp = getIntent().getStringExtra("mrp");
        Unit = getIntent().getStringExtra("priceUnit");
        stockItemID = getIntent().getStringExtra("stockItemID");
        availableStock=getIntent().getIntExtra("availableStock",0);

        //setting up the value of the xml
        NameValue=binding.etEnterStoreName;
        PurchaseValue=binding.PurchasePrice;
        SellingValue=binding.sellingPrice;
        Mrp=binding.Mrp;
        unit=binding.PriceUnit;
        AvailableStockValue=binding.AvailableStockValue;
        AvailableStockValueUnit=binding.AvailableStockValueUnit;

        NameValue.setText(name);
        PurchaseValue.setText(purchase);
        SellingValue.setText(selling);
        Mrp.setText(mrp);
        unit.setText(Unit);
        AvailableStockValue.setText(availableStock);
        AvailableStockValueUnit.setText(Unit);

        setUpActionBar();

        //Below code is for adding the unit //
        //dialog for the unit selection
        dialog = new Dialog(UpdateActivity.this);
        unit = binding.PriceUnit;
        UnitSelector = binding.dropDownUnitSelector;
        UnitSelector.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                try {
                    openDialog();
                } catch (Exception e) {
                    Toast.makeText(UpdateActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    Log.d("Exception:", e.toString());
                }
            }
        });

        ///api calling using volley
        save = binding.btnSave;
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(UpdateActivity.this);
                url = "http://67c0ce478420.ngrok.io/api/v1/stockItem/updateStockItem";
                StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", String.valueOf(error));
                            }
                        }
                ) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", name);
                        params.put("purchasePrice", purchase);
                        params.put("sellingPrice", selling);
                        params.put("mrp", mrp);
                        params.put("priceUnit", Unit);
                        return params;
                    }

                };

                queue.add(putRequest);
                callItemActivity();
            }
        });
    }

    ///Below code is for going back to the itemFragment
    public void callItemActivity() {
        Intent intent=new Intent(getApplicationContext(), ItemsFragment.class);
        startActivity(intent);
        finish();
    }

    private void setUpActionBar() {
        setSupportActionBar(binding.toolbarAddItemActivity);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_pressed_arrow_white_24);

        binding.toolbarAddItemActivity.setNavigationOnClickListener(v -> onBackPressed());

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void openDialog() {
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        dialog.show();


        ImageView imageView = dialog.findViewById(R.id.Cross);
        imageView.setOnClickListener(v -> dialog.dismiss());

        TextView button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12;
        TextView button13, button14, button15, button16, button17, button18, button19, button20, button21, button22, button23, button24;
        TextView button25, button26, button27, button28, button29, button30, button31, button32, button33, button34;
        button1 = dialog.findViewById(R.id.button1);
        button2 = dialog.findViewById(R.id.button2);
        button3 = dialog.findViewById(R.id.button3);
        button4 = dialog.findViewById(R.id.button4);
        button5 = dialog.findViewById(R.id.button5);
        button6 = dialog.findViewById(R.id.button6);
        button7 = dialog.findViewById(R.id.button7);
        button8 = dialog.findViewById(R.id.button8);
        button9 = dialog.findViewById(R.id.button9);
        button10 = dialog.findViewById(R.id.button10);
        button11 = dialog.findViewById(R.id.button11);
        button12 = dialog.findViewById(R.id.button12);
        button13 = dialog.findViewById(R.id.button13);
        button14 = dialog.findViewById(R.id.button14);
        button15 = dialog.findViewById(R.id.button15);
        button16 = dialog.findViewById(R.id.button16);
        button17 = dialog.findViewById(R.id.button17);
        button18 = dialog.findViewById(R.id.button18);
        button19 = dialog.findViewById(R.id.button19);
        button20 = dialog.findViewById(R.id.button20);
        button21 = dialog.findViewById(R.id.button21);
        button22 = dialog.findViewById(R.id.button22);
        button23 = dialog.findViewById(R.id.button23);
        button24 = dialog.findViewById(R.id.button24);
        button25 = dialog.findViewById(R.id.button25);
        button26 = dialog.findViewById(R.id.button26);
        button27 = dialog.findViewById(R.id.button27);
        button28 = dialog.findViewById(R.id.button28);
        button29 = dialog.findViewById(R.id.button29);
        button30 = dialog.findViewById(R.id.button30);
        button31 = dialog.findViewById(R.id.button31);
        button32 = dialog.findViewById(R.id.button32);
        button33 = dialog.findViewById(R.id.button33);
        button34 = dialog.findViewById(R.id.button34);


        String current_unit = unit.getText().toString();

        if (current_unit.equals(button1.getText().toString())) {
            button1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button1.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button2.getText().toString())) {
            button2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button2.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button3.getText().toString())) {
            button3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button3.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button4.getText().toString())) {
            button4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button4.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button5.getText().toString())) {
            button5.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button5.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button6.getText().toString())) {
            button6.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button6.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button7.getText().toString())) {
            button7.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button7.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button8.getText().toString())) {
            button8.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button8.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button9.getText().toString())) {
            button9.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button9.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button10.getText().toString())) {
            button10.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button10.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button11.getText().toString())) {
            button11.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button11.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button12.getText().toString())) {
            button12.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button12.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button13.getText().toString())) {
            button13.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button13.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button14.getText().toString())) {
            button14.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button14.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button15.getText().toString())) {
            button15.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button15.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button16.getText().toString())) {
            button16.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button16.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button17.getText().toString())) {
            button17.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button17.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button18.getText().toString())) {
            button18.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button18.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button19.getText().toString())) {
            button19.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button19.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button20.getText().toString())) {
            button20.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button20.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button21.getText().toString())) {
            button21.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button21.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button22.getText().toString())) {
            button22.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button22.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button23.getText().toString())) {
            button23.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button23.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button24.getText().toString())) {
            button24.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button24.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button25.getText().toString())) {
            button25.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button25.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button26.getText().toString())) {
            button26.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button26.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button27.getText().toString())) {
            button27.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button27.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button28.getText().toString())) {
            button28.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button28.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button29.getText().toString())) {
            button29.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button29.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button30.getText().toString())) {
            button30.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button30.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button31.getText().toString())) {
            button31.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button31.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button32.getText().toString())) {
            button32.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button32.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button33.getText().toString())) {
            button33.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button33.setTextColor(Color.parseColor("#ffffff"));
        } else if (current_unit.equals(button34.getText().toString())) {
            button34.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#715AFF")));
            button34.setTextColor(Color.parseColor("#ffffff"));
        }

        Button clear_unit = dialog.findViewById(R.id.clear_unit);
        clear_unit.setOnClickListener(view -> {
            unit.setText(R.string.select_unit);
            dialog.dismiss();
        });


        button1.setOnClickListener(v -> {
            unit.setText(button1.getText().toString());
            dialog.dismiss();
        });
        button2.setOnClickListener(v -> {
            unit.setText(button2.getText().toString());
            dialog.dismiss();
        });
        button3.setOnClickListener(v -> {
            unit.setText(button3.getText().toString());
            dialog.dismiss();
        });
        button4.setOnClickListener(v -> {
            unit.setText(button4.getText().toString());

            dialog.dismiss();
        });
        button5.setOnClickListener(v -> {
            unit.setText(button5.getText().toString());

            dialog.dismiss();
        });
        button6.setOnClickListener(v -> {
            unit.setText(button6.getText().toString());

            dialog.dismiss();
        });
        button7.setOnClickListener(v -> {
            unit.setText(button7.getText().toString());

            dialog.dismiss();
        });
        button8.setOnClickListener(v -> {
            unit.setText(button8.getText().toString());

            dialog.dismiss();
        });
        button9.setOnClickListener(v -> {
            unit.setText(button9.getText().toString());

            dialog.dismiss();
        });
        button10.setOnClickListener(v -> {
            unit.setText(button10.getText().toString());

            dialog.dismiss();
        });
        button11.setOnClickListener(v -> {
            unit.setText(button11.getText().toString());

            dialog.dismiss();
        });
        button12.setOnClickListener(v -> {
            unit.setText(button12.getText().toString());

            dialog.dismiss();
        });

        button13.setOnClickListener(v -> {
            unit.setText(button13.getText().toString());

            dialog.dismiss();
        });
        button14.setOnClickListener(v -> {
            unit.setText(button14.getText().toString());

            dialog.dismiss();
        });
        button15.setOnClickListener(v -> {
            unit.setText(button15.getText().toString());

            dialog.dismiss();
        });
        button16.setOnClickListener(v -> {
            unit.setText(button16.getText().toString());

            dialog.dismiss();
        });
        button17.setOnClickListener(v -> {
            unit.setText(button17.getText().toString());

            dialog.dismiss();
        });
        button18.setOnClickListener(v -> {
            unit.setText(button18.getText().toString());

            dialog.dismiss();
        });
        button19.setOnClickListener(v -> {
            unit.setText(button19.getText().toString());

            dialog.dismiss();
        });
        button20.setOnClickListener(v -> {
            unit.setText(button20.getText().toString());

            dialog.dismiss();
        });
        button21.setOnClickListener(v -> {
            unit.setText(button21.getText().toString());

            dialog.dismiss();
        });
        button22.setOnClickListener(v -> {
            unit.setText(button22.getText().toString());

            dialog.dismiss();
        });
        button23.setOnClickListener(v -> {
            unit.setText(button23.getText().toString());

            dialog.dismiss();
        });
        button24.setOnClickListener(v -> {
            unit.setText(button24.getText().toString());

            dialog.dismiss();
        });
        button25.setOnClickListener(v -> {
            unit.setText(button25.getText().toString());

            dialog.dismiss();
        });
        button26.setOnClickListener(v -> {
            unit.setText(button26.getText().toString());

            dialog.dismiss();
        });
        button27.setOnClickListener(v -> {
            unit.setText(button27.getText().toString());

            dialog.dismiss();
        });
        button28.setOnClickListener(v -> {
            unit.setText(button28.getText().toString());

            dialog.dismiss();
        });
        button29.setOnClickListener(v -> {
            unit.setText(button29.getText().toString());

            dialog.dismiss();
        });
        button30.setOnClickListener(v -> {

            unit.setText(button30.getText().toString());

            dialog.dismiss();
        });
        button31.setOnClickListener(v -> {
            unit.setText(button31.getText().toString());

            dialog.dismiss();
        });
        button32.setOnClickListener(v -> {
            unit.setText(button32.getText().toString());

            dialog.dismiss();
        });
        button33.setOnClickListener(v -> {
            unit.setText(button33.getText().toString());

            dialog.dismiss();
        });
        button34.setOnClickListener(v -> {
            unit.setText(button34.getText().toString());

            dialog.dismiss();
        });
    }

}