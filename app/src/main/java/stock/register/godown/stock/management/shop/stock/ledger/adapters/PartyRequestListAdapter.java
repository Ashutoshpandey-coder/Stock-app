package stock.register.godown.stock.management.shop.stock.ledger.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;


import stock.register.godown.stock.management.shop.stock.ledger.models.PartyModelRequest;

public class PartyRequestListAdapter extends RecyclerView.Adapter<PartyRequestListAdapter.ViewHolder> {
    private final List<PartyModelRequest> partyModelList;
    private OnPartyNameListener mOnPartyNameListener;

    public PartyRequestListAdapter(Context context, List<PartyModelRequest> partyModelList,OnPartyNameListener mOnPartyNameListener) {
        this.partyModelList = partyModelList;
        this.mOnPartyNameListener = mOnPartyNameListener;
    }

    @NonNull
    @Override
    public PartyRequestListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_party, parent, false);
        return new ViewHolder(view,mOnPartyNameListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PartyRequestListAdapter.ViewHolder holder, int position) {
        PartyModelRequest model = partyModelList.get(position);

        holder.partyName.setText(model.getPartyName());

    }

    @Override
    public int getItemCount() {
        return partyModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView partyName;
        OnPartyNameListener onPartyNameListener;


        public ViewHolder(@NonNull View itemView,OnPartyNameListener onPartyNameListener) {
            super(itemView);
            partyName = itemView.findViewById(R.id.tv_party_name);

            this.onPartyNameListener = onPartyNameListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onPartyNameListener.onPartyClick(getAdapterPosition());
        }
    }
    public interface OnPartyNameListener{
        void onPartyClick(int position);
    }
}
