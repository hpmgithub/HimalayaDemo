package com.jash.himalayademo.fragments;


import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jash.himalayademo.BR;
import com.jash.himalayademo.R;
import com.jash.himalayademo.adapters.BindingAdapter;
import com.jash.himalayademo.adapters.FocusImageAdapter;
import com.jash.himalayademo.dao.AlbumEntityDao;
import com.jash.himalayademo.databinding.FocusImagesItemBinding;
import com.jash.himalayademo.databinding.FragmentHomeBinding;
import com.jash.himalayademo.entities.DiscoveryColumnEntity;
import com.jash.himalayademo.entities.FocusImageEntity;
import com.jash.himalayademo.entities.FocusImages;
import com.jash.himalayademo.entities.HomeEntity;
import com.jash.himalayademo.entities.RecommendEntity;
import com.jash.himalayademo.util.DBUtil;
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
public class HomeFragment extends Fragment implements Callback<HomeEntity> {


    private BindingAdapter adapter;

    public HomeFragment() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(new TransitionSet()
                    .setOrdering(TransitionSet.ORDERING_TOGETHER)
                    .addTransition(new ChangeBounds())
                    .addTransition(new ChangeClipBounds())
                    .addTransition(new ChangeTransform()));
            setSharedElementReturnTransition(new TransitionSet()
                    .setOrdering(TransitionSet.ORDERING_TOGETHER)
                    .addTransition(new ChangeBounds())
                    .addTransition(new ChangeClipBounds())
                    .addTransition(new ChangeTransform()));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Map<Type, BindingAdapter.BindingTool> map = new HashMap<>();
        map.put(RecommendEntity.class, new BindingAdapter.BindingTool(R.layout.recommend_item, BR.recommend));
        map.put(DiscoveryColumnEntity.class, new BindingAdapter.BindingTool(R.layout.discovery_column_item, BR.column));
        map.put(FocusImages.class, new BindingAdapter.BindingTool(R.layout.focus_images_item, BR.focusImages));
        adapter = new BindingAdapter(getContext(), map, new ArrayList<>());
        NetworkUtil.getService().getHome().enqueue(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.homeRecycler.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onResponse(Response<HomeEntity> response) {
        HomeEntity body = response.body();
        adapter.add(body.getFocusImages());
        adapter.add(body.getEditorRecommendAlbums());
        adapter.add(body.getDiscoveryColumns());
        adapter.addAll(body.getHotRecommends().getList());
        AlbumEntityDao dao = DBUtil.getSession().getAlbumEntityDao();
        dao.insertOrReplaceInTx(body.getEditorRecommendAlbums().getList());
        List<RecommendEntity> list = body.getHotRecommends().getList();
        for (RecommendEntity entity : list) {
            dao.insertOrReplaceInTx(entity.getList());
        }
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
    }

    @android.databinding.BindingAdapter("bind:init")
    public static void initFocusPager(View view, List<FocusImageEntity> list){
        final FocusImagesItemBinding binding = DataBindingUtil.getBinding(view);
        if (binding.focusPager.getAdapter() == null) {
            binding.focusPager.setAdapter(new FocusImageAdapter(list));
            binding.focusPager.post(new Runnable() {
                @Override
                public void run() {
                    ViewGroup.LayoutParams params = binding.focusPager.getLayoutParams();
                    params.height = (int) (binding.focusPager.getWidth() / 2.13f);
                    binding.focusPager.setLayoutParams(params);
                }
            });
            binding.focusGroup.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(view.getContext());
            for (int i = 0; i < list.size(); i++) {
                View inflate = inflater.inflate(R.layout.indicator, binding.focusGroup, false);
                inflate.setId(i);
                binding.focusGroup.addView(inflate);
            }
            binding.focusGroup.check(0);
            binding.focusPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    binding.focusGroup.check(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    int i = binding.focusPager.getCurrentItem()+1;
                    if (i >= binding.focusGroup.getChildCount()){
                        i = 0;
                    }
                    binding.focusPager.setCurrentItem(i);
                    sendEmptyMessageDelayed(0, 1000);
                }
            };
            handler.sendEmptyMessageDelayed(0, 1000);
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    int i = binding.focusPager.getCurrentItem()+1;
//                    if (i >= binding.focusGroup.getChildCount()){
//                        i = 0;
//                    }
//                    final int finalI = i;
//                    binding.focusPager.post(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    });
//                }
//            }, 1000, 1000);
        }
    }
}
