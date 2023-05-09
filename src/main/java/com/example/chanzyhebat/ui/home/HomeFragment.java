package com.example.chanzyhebat.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chanzyhebat.API.APIRequestData;
import com.example.chanzyhebat.API.RetroServer;
import com.example.chanzyhebat.Model.PofileRequest;
import com.example.chanzyhebat.R;
import com.example.chanzyhebat.databinding.FragmentHomeBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    TextView tvname,tvaddres;
    ImageView imgusr;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home,container,false);
        tvname = root.findViewById(R.id.tv_name);
        tvaddres = root.findViewById(R.id.tv_address);
        imgusr = root.findViewById(R.id.img_user);
        String token = getActivity().getIntent().getStringExtra("token");
        APIRequestData  ardData = RetroServer.getCon().create(APIRequestData.class);
        Call<PofileRequest> user = ardData.getUser("Bearer "+token);
        user.enqueue(new Callback<PofileRequest>() {
            @Override
            public void onResponse(Call<PofileRequest> call, Response<PofileRequest> response) {
                PofileRequest pr = response.body();
                tvname.setText(pr.getName());
                tvaddres.setText(pr.getAddress());
                Picasso.get().load(pr.getImage()).fit().centerCrop().into(imgusr);
            }

            @Override
            public void onFailure(Call<PofileRequest> call, Throwable t) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}