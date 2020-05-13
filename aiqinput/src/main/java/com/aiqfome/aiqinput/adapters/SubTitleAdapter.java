package com.aiqfome.aiqinput.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aiqfome.aiqinput.databinding.ItemlistSubTitleBinding;

import java.util.List;

public abstract class SubTitleAdapter<Type>
        extends RecyclerView.Adapter<SubTitleAdapter.SubTitleViewHolder> {

    private List<SubTitleItem<Type>> itemList;

    public SubTitleAdapter(List<SubTitleItem<Type>> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public SubTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemlistSubTitleBinding binding = ItemlistSubTitleBinding.inflate(layoutInflater);
        return new SubTitleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubTitleViewHolder holder, int position) {
        SubTitleItem item = itemList.get(position);
        holder.bind(item);

        holder.itemView.setOnClickListener(itemClickListener(item));
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    public abstract View.OnClickListener itemClickListener(SubTitleItem<Type> object);

    static class SubTitleViewHolder extends RecyclerView.ViewHolder {

        ItemlistSubTitleBinding binding;

        SubTitleViewHolder(ItemlistSubTitleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(SubTitleItem viewObject) {
            binding.setViewObject(viewObject);

            binding.tvSubtitle.setVisibility
                    (viewObject.getSubTitle().isEmpty() ? View.GONE : View.VISIBLE);

            binding.executePendingBindings();
        }
    }
}
