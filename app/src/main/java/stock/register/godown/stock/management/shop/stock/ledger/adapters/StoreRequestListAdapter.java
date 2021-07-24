package stock.register.godown.stock.management.shop.stock.ledger.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import stock.register.godown.stock.management.shop.stock.ledger.R;
import stock.register.godown.stock.management.shop.stock.ledger.models.StoreModelRequest;

public class StoreRequestListAdapter extends RecyclerView.Adapter<StoreRequestListAdapter.ViewHolder> {
    private final List<StoreModelRequest> storeModelList;
    private final Context context;

    public StoreRequestListAdapter(Context context, List<StoreModelRequest> storeModelList) {
        this.storeModelList = storeModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public StoreRequestListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreRequestListAdapter.ViewHolder holder, int position) {
        StoreModelRequest model = storeModelList.get(position);

        holder.storeName.setText(model.getName());
        // TODO 1 :: To set the quantity of stock items also , model have no such field related to this

    }

    @Override
    public int getItemCount() {
        return storeModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView storeName;
        public TextView stockItemQuantities;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            storeName = itemView.findViewById(R.id.tv_store_name);
            stockItemQuantities = itemView.findViewById(R.id.tv_stock_quantity);



        }


        @Override
        public void onClick(View v) {

        }

    }
}
