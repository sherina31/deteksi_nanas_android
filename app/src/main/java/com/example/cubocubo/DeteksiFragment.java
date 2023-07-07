package com.example.cubocubo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeteksiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeteksiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = DeteksiFragment.class.getSimpleName();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DeteksiAdapter adapter;
    private RecyclerView rvDeteksi;
    private DataService service;

    public DeteksiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeteksiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeteksiFragment newInstance(String param1, String param2) {
        DeteksiFragment fragment = new DeteksiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_deteksi,container,false);
       rvDeteksi = view.findViewById(R.id.rv_deteksi);
       return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        initViews();
        adapter = new DeteksiAdapter(getContext());
        rvDeteksi.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDeteksi.setAdapter(adapter);
        service = ServiceGenerator.createBaseService(getContext(),DataService.class);
        loadData();
    }
    private void loadData() {
        Call<BaseResponse<List<M_tabel_deteksi>>> call = service.apiReadDeteksi();
        call.enqueue(new Callback<BaseResponse<List<M_tabel_deteksi>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<M_tabel_deteksi>>> call,
                                   Response<BaseResponse<List<M_tabel_deteksi>>> response) {
                if (response.isSuccessful()) {
                    List<M_tabel_deteksi> deteksiList = response.body().getData();
                    adapter.addAll(deteksiList);
                } else {
                    Log.e(TAG, "Failed to load data: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<M_tabel_deteksi>>> call, Throwable t) {
                Log.e(TAG, "Failed to load data: " + t.getMessage());
            }
        });
    }

    private void initViews() {
        rvDeteksi = getView().findViewById(R.id.rv_deteksi);
    }


}
