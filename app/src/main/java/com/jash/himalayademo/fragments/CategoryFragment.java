package com.jash.himalayademo.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jash.himalayademo.BR;
import com.jash.himalayademo.R;
import com.jash.himalayademo.adapters.BindingAdapter;
import com.jash.himalayademo.databinding.FragmentCategoryBinding;
import com.jash.himalayademo.entities.Categories;
import com.jash.himalayademo.entities.CategoriesFirst;
import com.jash.himalayademo.entities.CategoryEntity;
import com.jash.himalayademo.util.NetworkUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements Callback<Categories> {


    private BindingAdapter adapter;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Map<Type, BindingAdapter.BindingTool> map = new HashMap<>();
        map.put(CategoriesFirst.class, new BindingAdapter.BindingTool(R.layout.category_first_item, BR.first));
        map.put(Categories.class, new BindingAdapter.BindingTool(R.layout.categories_item, BR.categories));
        adapter = new BindingAdapter(getContext(), map, new ArrayList<>());
        NetworkUtil.getService().getCategories().enqueue(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCategoryBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        binding.categoryRecycler.setAdapter(adapter);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onResponse(Response<Categories> response) {
        List<CategoryEntity> list = response.body().getList();
        CategoriesFirst first = new CategoriesFirst();
        first.setList(list.subList(0, 5));
        adapter.add(first);
        for (int i = 5; i < list.size(); i += 6) {
            Categories categories = new Categories();
            categories.setList(list.subList(i, Math.min(i + 6, list.size())));
            adapter.add(categories);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
    }
}
