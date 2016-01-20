package com.jash.himalayademo.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by jash
 * Date: 16-1-20
 * Time: 上午11:17
 */
public class BindingAdapter extends RecyclerView.Adapter<BindingAdapter.BindingViewHolder>{
    private Context context;
    private Map<Type, BindingTool> map;
    private List<Object> list;

    public BindingAdapter(Context context, Map<Type, BindingTool> map, List<Object> list) {
        this.context = context;
        this.map = map;
        this.list = list;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), viewType, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Object o = list.get(position);
        int variableId = map.get(o.getClass()).variableId;
        holder.binding.setVariable(variableId, o);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(Object o){
        int size = list.size();
        list.add(o);
        notifyItemInserted(size);
    }
    public void addAll(Collection<?> collection){
        int size = list.size();
        list.addAll(collection);
        notifyItemRangeInserted(size, collection.size());
    }
    @Override
    public int getItemViewType(int position) {
        return map.get(list.get(position).getClass()).layoutId;
    }

    public static class BindingViewHolder extends RecyclerView.ViewHolder{

        private ViewDataBinding binding;

        public BindingViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public static class BindingTool{
        private int layoutId;
        private int variableId;

        public BindingTool(int layoutId, int variableId) {
            this.layoutId = layoutId;
            this.variableId = variableId;
        }
    }
}
