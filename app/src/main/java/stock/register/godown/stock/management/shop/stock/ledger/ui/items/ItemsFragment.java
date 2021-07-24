package stock.register.godown.stock.management.shop.stock.ledger.ui.items;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import stock.register.godown.stock.management.shop.stock.ledger.activities.MainActivity;
import stock.register.godown.stock.management.shop.stock.ledger.R;
import stock.register.godown.stock.management.shop.stock.ledger.databinding.FragmentItemsBinding;


public class ItemsFragment extends Fragment {

    private FragmentItemsBinding binding;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) requireActivity()).setActionBarTitle(getString(R.string.title_items));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentItemsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnAddItem.setOnClickListener(v -> {
//            Intent intent = new Intent(requireContext(), AddItemActivity.class);
//            startActivity(intent);
            Navigation.findNavController(requireView()).navigate(R.id.fragment_items_to_fragment_add_items);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}