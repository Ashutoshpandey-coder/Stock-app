package stock.register.godown.stock.management.shop.stock.ledger.adapters;

        import android.content.Context;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.EditText;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;


        import java.util.List;

        import stock.register.godown.stock.management.shop.stock.ledger.R;
        import stock.register.godown.stock.management.shop.stock.ledger.models.AddMoreViewModel;
        import stock.register.godown.stock.management.shop.stock.ledger.models.PartyModelRequest;
        import stock.register.godown.stock.management.shop.stock.ledger.models.StockItemReportModel;
        import stock.register.godown.stock.management.shop.stock.ledger.models.StockStoreDetails;

public class AddMoreViewsListAdapter extends RecyclerView.Adapter<AddMoreViewsListAdapter.ViewHolder> {
    public static List<StockStoreDetails> itemsList;
    private final Context context;

    public AddMoreViewsListAdapter(Context context, List<StockStoreDetails> itemsList) {
        this.itemsList = itemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public AddMoreViewsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_stock_from_other_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddMoreViewsListAdapter.ViewHolder holder, int position) {
        StockStoreDetails model = itemsList.get(position);
        holder.openingStockCount.setText(model.getAvailableCount());
        holder.addStore.setText(model.getStoreId());
        holder.stockAddDate.setText(model.getStockAddDate());

        holder.openingStockCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                itemsList.get(position).setAvailableCount(holder.openingStockCount.getText().toString());
                itemsList.get(position).setStoreId(holder.addStore.getText().toString());
                itemsList.get(position).setStockAddDate(holder.stockAddDate.getText().toString());

//                holder.openingStockCount.setText(itemsList.get(position).getAvailableCount());
//                holder.addStore.setText(itemsList.get(position).getStoreId());
//                holder.stockAddDate.setText(itemsList.get(position).getStockAddDate());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.addStore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                itemsList.get(position).setAvailableCount(holder.openingStockCount.getText().toString());
                itemsList.get(position).setStoreId(holder.addStore.getText().toString());
                itemsList.get(position).setStockAddDate(holder.stockAddDate.getText().toString());

//                holder.openingStockCount.setText(itemsList.get(position).getAvailableCount());
//                holder.addStore.setText(itemsList.get(position).getStoreId());
//                holder.stockAddDate.setText(itemsList.get(position).getStockAddDate());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected EditText openingStockCount, addStore;
        protected TextView stockAddDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            openingStockCount = itemView.findViewById(R.id.et_opening_stock);
            addStore = itemView.findViewById(R.id.et_add_store);
            stockAddDate = itemView.findViewById(R.id.tv_date);

        }

        @Override
        public void onClick(View v) {

        }

    }
}


