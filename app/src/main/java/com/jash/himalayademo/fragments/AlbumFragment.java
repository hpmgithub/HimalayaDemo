package com.jash.himalayademo.fragments;


import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jash.himalayademo.R;
import com.jash.himalayademo.databinding.FragmentAlbumBinding;
import com.jash.himalayademo.entities.AlbumEntity;
import com.jash.himalayademo.util.DBUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {


    private AlbumEntity album;

    public AlbumFragment() {
        // Required empty public constructor
    }

    public static AlbumFragment newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong("album_id", id);
        AlbumFragment fragment = new AlbumFragment();
        fragment.setArguments(args);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragment.setSharedElementEnterTransition(new TransitionSet()
                    .setOrdering(TransitionSet.ORDERING_TOGETHER)
                    .addTransition(new ChangeBounds())
                    .addTransition(new ChangeClipBounds())
                    .addTransition(new ChangeTransform()));
            fragment.setSharedElementReturnTransition(new TransitionSet()
                    .setOrdering(TransitionSet.ORDERING_TOGETHER)
                    .addTransition(new ChangeBounds())
                    .addTransition(new ChangeClipBounds())
                    .addTransition(new ChangeTransform()));
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long album_id = getArguments().getLong("album_id");
        album = DBUtil.getSession().getAlbumEntityDao().load(album_id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentAlbumBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_album, container, false);
        ViewCompat.setTransitionName(binding.albumCover, album.getTitle());
        binding.setAlbum(album);
        return binding.getRoot();
    }

}
