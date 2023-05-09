package com.example.chanzyhebat.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chanzyhebat.API.APIRequestData;
import com.example.chanzyhebat.API.RetroServer;
import com.example.chanzyhebat.Adapter.AdapterJava;
import com.example.chanzyhebat.Model.ProductRequest;
import com.example.chanzyhebat.Model.ProductResponse;
import com.example.chanzyhebat.R;
import com.example.chanzyhebat.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private RecyclerView rv;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private FragmentDashboardBinding binding;

    private List<ProductRequest> productRequests = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_dashboard,container,false);
        rv = root.findViewById(R.id.rv);
        lmData = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(lmData);
        retrieve();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void retrieve(){
        APIRequestData ardData = RetroServer.getCon().create(APIRequestData.class);
        String token = getActivity().getIntent().getStringExtra("token");
        Call<ProductResponse> retrieve = ardData.getProduct("Bearer "+token);
        retrieve.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                productRequests = response.body().getProductRequests();
                adData = new AdapterJava(getActivity(),productRequests);
                rv.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });

    }
}