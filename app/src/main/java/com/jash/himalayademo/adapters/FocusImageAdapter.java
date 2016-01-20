package com.jash.himalayademo.adapters;

import android.databinding.DataBindingUtil;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jash.himalayademo.R;
import com.jash.himalayademo.databinding.FocusImageItemBinding;
import com.jash.himalayademo.entities.FocusImageEntity;

import java.util.List;

/**
 * Created by jash
 * Date: 16-1-20
 * Time: 下午2:17
 */
public class FocusImageAdapter extends PagerAdapter{
    private List<FocusImageEntity> list;
    private Pools.Pool<View> pool;

    public FocusImageAdapter(List<FocusImageEntity> list) {
        this.list = list;
        pool = new Pools.SimplePool<>(4);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        FocusImageItemBinding binding = DataBindingUtil.getBinding(view);
        return binding.getFocusImage().equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = pool.acquire();
        if (view == null) {
            view = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.focus_image_item, container, false).getRoot();
        }
        FocusImageItemBinding binding = DataBindingUtil.getBinding(view);
        binding.setFocusImage(list.get(position));
        container.addView(view);
        view.setId(position);
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = container.findViewById(position);
        container.removeView(view);
        pool.release(view);
    }
}
