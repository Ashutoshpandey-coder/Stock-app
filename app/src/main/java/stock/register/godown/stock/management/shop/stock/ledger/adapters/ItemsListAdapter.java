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
import stock.register.godown.stock.management.shop.stock.ledger.models.StockItemReportModel;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ViewHolder> {
    private final List<StockItemReportModel> itemsList;
    private final Context context;

    public ItemsListAdapter(Context context, List<StockItemReportModel> itemsList) {
        this.itemsList = itemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_items_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsListAdapter.ViewHolder holder, int position) {
        StockItemReportModel model = itemsList.get(position);

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemName, sellingPrice, stockValue, availableStock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            itemName = itemView.findViewById(R.id.tv_items_name);
            sellingPrice = itemView.findViewById(R.id.tv_items_selling_price);
            stockValue = itemView.findViewById(R.id.items_stock_value);
            availableStock = itemView.findViewById(R.id.tv_items_available_stock);

        }


        @Override
        public void onClick(View v) {

        }

    }
}

