package com.example.ecommercemobileapp2hand.Views.Homepage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecommercemobileapp2hand.Controllers.CategoriesHandler;
import com.example.ecommercemobileapp2hand.Models.FakeModels.Category;
import com.example.ecommercemobileapp2hand.Models.FakeModels.Product;
import com.example.ecommercemobileapp2hand.Models.ProductCategory;
import com.example.ecommercemobileapp2hand.R;
import com.example.ecommercemobileapp2hand.Views.Adapters.CategoriesAdapter;
import com.example.ecommercemobileapp2hand.Views.Adapters.ProductCardAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private TextView tvSeeAll;
    private RecyclerView recyclerViewCategories;
    private List<ProductCategory> categoryList;
    private CategoriesAdapter categoriesAdapter;

    private RecyclerView recyclerViewNewIn;
    private ArrayList<Product> lstProNewIn;
    private ProductCardAdapter NewInAdapter;
    private TextView tvNewInSeeAll,tvTopSellingSeeAll;
    private RecyclerView recyclerViewTopSelling;
    private ArrayList<Product> lstProTopSelling;
    private ProductCardAdapter TopSellingAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        addControl(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCategoriesData();
        loadTopSellingProductsData();
        loadNewInProductsData();
        addEvent();
    }

    private void addControl(View view){
        recyclerViewCategories = view.findViewById(R.id.recyclerViewCategories);
        tvSeeAll = view.findViewById(R.id.tvSeeAll);

        recyclerViewNewIn = view.findViewById(R.id.recyclerViewNewIn);
        recyclerViewTopSelling = view.findViewById(R.id.recyclerViewTopSelling);

        tvNewInSeeAll = view.findViewById(R.id.tvNewInSeeAll);
        tvTopSellingSeeAll = view .findViewById(R.id.tvTopSellingSeeAll);

    }

    private void loadCategoriesData(){
        categoryList = new ArrayList<>();
        categoryList = CategoriesHandler.getData().subList(0,5);
        categoriesAdapter = new CategoriesAdapter(categoryList,getContext(),R.layout.custom_recycle_categories_homepage);
         RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerViewCategories.setLayoutManager(layoutManager);
       recyclerViewCategories.setAdapter(categoriesAdapter);

    }
    private void loadTopSellingProductsData(){
        lstProTopSelling = new ArrayList<>();
        lstProTopSelling = Product.initProduct();
        TopSellingAdapter = new ProductCardAdapter(lstProTopSelling,getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewTopSelling.setLayoutManager(layoutManager);
        recyclerViewTopSelling.setAdapter(TopSellingAdapter);
    }
    private void loadNewInProductsData(){
        lstProNewIn = new ArrayList<>();
        lstProNewIn = Product.initProduct();
        NewInAdapter = new ProductCardAdapter(lstProNewIn,getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewNewIn.setLayoutManager(layoutManager);
        recyclerViewNewIn.setAdapter(NewInAdapter);
    }
    private void addEvent(){
        tvSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoriesActivity.class);
                startActivity(intent);

            }
        });
    }
}