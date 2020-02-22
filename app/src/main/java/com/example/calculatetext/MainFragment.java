package com.example.calculatetext;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.calculatetext.databinding.FragmentMainBinding;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final MyViewModel myViewModel;
        myViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MyViewModel.class);
        final FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(getActivity());
        myViewModel.init();
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v){
                NavController controller = Navigation.findNavController(v);
                int key = myViewModel.commit();
                if (key == 1) {

                    Toast toast = Toast.makeText(getContext(), "答题正确！请继续作答！", Toast.LENGTH_SHORT);
                    // 显示位置
                    toast.setGravity(Gravity.CENTER, 0, -350);
                    // Toast显示图片
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_24dp);
                    LinearLayout layout = (LinearLayout) toast.getView();
                    layout.addView(imageView, 0);
                    // Toast背景
                    layout.setBackgroundColor(Color.alpha(0));
                    toast.show();

                    myViewModel.init();
                    controller.navigate(R.id.action_mainFragment_self);
                }
                else if (key == -1)
                {
                    Toast toast = Toast.makeText(getContext(), "答案不可为空！", Toast.LENGTH_SHORT);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp);
                    LinearLayout layout = (LinearLayout) toast.getView();
                    layout.addView(imageView, 0);
                    layout.setBackgroundColor(Color.alpha(0));
                    toast.setGravity(Gravity.CENTER, 0, -350);
                    toast.show();
                }
                else
                {
                    controller.navigate(R.id.action_mainFragment_to_finishFragment);
                    myViewModel.init();
                }
            }
        });
        return binding.getRoot();
    }

}
