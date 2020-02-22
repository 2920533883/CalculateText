package com.example.calculatetext;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.calculatetext.databinding.FragmentFinishBinding;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinishFragment extends Fragment {


    public FinishFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final MyViewModel myViewModel;
        myViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MyViewModel.class);
        final FragmentFinishBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_finish, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(getActivity());
        binding.imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.score = 0;
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_finishFragment_to_welcomeFragment);
            }
        });
        return binding.getRoot();
    }


}
