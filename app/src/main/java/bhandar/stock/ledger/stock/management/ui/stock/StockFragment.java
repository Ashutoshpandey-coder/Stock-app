package bhandar.stock.ledger.stock.management.ui.stock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import bhandar.stock.ledger.stock.management.MainActivity;
import bhandar.stock.ledger.stock.management.R;
import bhandar.stock.ledger.stock.management.databinding.FragmentStockBinding;

public class StockFragment extends Fragment {

    private FragmentStockBinding binding;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) requireActivity()).setActionBarTitle(getString(R.string.title_stock));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentStockBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.leftDateBtn.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.fragment_stock_to_fragment_stock_in));
        binding.rightDateBtn.setOnClickListener(v -> Navigation.findNavController(requireView()).navigate(R.id.fragment_stock_to_fragment_stock_out));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}