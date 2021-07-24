package bhandar.stock.ledger.stock.management.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import stock.register.godown.stock.management.shop.stock.ledger.R;
import stock.register.godown.stock.management.shop.stock.ledger.models.ItemTransactionModel;

public class StockStoreTransactionListAdapter extends RecyclerView.Adapter<StockStoreTransactionListAdapter.ViewHolder> {
    private final List<ItemTransactionModel> transactionList;
    private final Context context;

    public StockStoreTransactionListAdapter(Context context, List<ItemTransactionModel> transactionList) {
        this.transactionList = transactionList;
        this.context = context;
    }

    @NonNull
    @Override
    public StockStoreTransactionListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock_store_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StockStoreTransactionListAdapter.ViewHolder holder, int position) {
        ItemTransactionModel model = transactionList.get(position);

        holder.productName.setText(model.getItemName());
        //TODO 2 :: ADD updated time but there is not field related to time in model
//        holder.updatedTime.setText(model.getTime);
        holder.totalIn.setText(model.getTotalIn());
        holder.totalOut.setText(model.getTotalOut());
        holder.openingStockQuantity.setText(model.getOpeningStock());
        holder.closingStockQuantity.setText(model.getClosingStock());

        holder.addIn.setOnClickListener(v -> Toast.makeText(context, "Please wait Add in ...", Toast.LENGTH_SHORT).show());

        holder.addOut.setOnClickListener(v -> Toast.makeText(context, "Please wait Add out ...", Toast.LENGTH_SHORT).show());

    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productName;
        TextView updatedTime;
        TextView totalIn;
        TextView totalOut;
        TextView openingStockQuantity;
        TextView closingStockQuantity;
        TextView addIn;
        TextView addOut;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            productName = itemView.findViewById(R.id.tv_product_name);
            updatedTime = itemView.findViewById(R.id.tv_updated_time);
            totalIn = itemView.findViewById(R.id.tv_total_in);
            totalOut = itemView.findViewById(R.id.tv_total_out);
            closingStockQuantity = itemView.findViewById(R.id.tv_closing_stock_quantity);
            openingStockQuantity = itemView.findViewById(R.id.tv_opening_stock_quantity);
            addIn = itemView.findViewById(R.id.tv_add_in);
            addOut = itemView.findViewById(R.id.tv_add_out);
        }


        @Override
        public void onClick(View v) {

        }

    }
}
