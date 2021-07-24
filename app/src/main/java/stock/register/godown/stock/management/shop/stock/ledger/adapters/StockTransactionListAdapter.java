package stock.register.godown.stock.management.shop.stock.ledger.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import stock.register.godown.stock.management.shop.stock.ledger.R;
import stock.register.godown.stock.management.shop.stock.ledger.models.StoreModelRequest;

public class StoreNameListAdapter extends RecyclerView.Adapter<StoreNameListAdapter.ViewHolder> {
    private final List<StoreModelRequest> storeModelList;
    private final Context context;
    private boolean isSelectedState = false;

    public StoreNameListAdapter(Context context, List<StoreModelRequest> storeModelList) {
        this.storeModelList = storeModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public StoreNameListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_name, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreNameListAdapter.ViewHolder holder, int position) {
        StoreModelRequest model = storeModelList.get(position);

        holder.storeName.setText(model.getName());
        if (isSelectedState) {

            holder.storeName.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
            holder.storeName.setTextColor(ContextCompat.getColor(context,R.color.primaryTextColor));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.storeName.setBackgroundColor(ContextCompat.getColor(context,R.color.primaryTextColor));
                holder.storeName.setTextColor(ContextCompat.getColor(context,R.color.white));
                isSelectedState = true;

            }
        });





    }

    @Override
    public int getItemCount() {
        return storeModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView storeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            storeName = itemView.findViewById(R.id.store_name);



        }


        @Override
        public void onClick(View v) {

        }

    }
}

