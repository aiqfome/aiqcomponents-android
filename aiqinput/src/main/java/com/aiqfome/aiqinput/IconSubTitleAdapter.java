package com.aiqfome.aiqinput;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aiqfome.aiqinput.databinding.ItemlistIconSubTitleBinding;

import java.util.List;

public abstract class IconSubTitleAdapter <Type>
        extends RecyclerView.Adapter<IconSubTitleAdapter.IconSubTitleViewHolder> {

    private List<IconSubTitleItem<Type>> itemList;

    public IconSubTitleAdapter(List<IconSubTitleItem<Type>> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public IconSubTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemlistIconSubTitleBinding binding = ItemlistIconSubTitleBinding.inflate(layoutInflater);
        return new IconSubTitleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IconSubTitleViewHolder holder, int position) {
        IconSubTitleItem item = itemList.get(position);
        holder.bind(item);

        holder.itemView.setOnClickListener(itemClickListener(item));
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    public abstract View.OnClickListener itemClickListener(IconSubTitleItem<Type> object);

    static class IconSubTitleViewHolder extends RecyclerView.ViewHolder {

        ItemlistIconSubTitleBinding binding;

        IconSubTitleViewHolder(ItemlistIconSubTitleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(IconSubTitleItem viewObject) {
            binding.setViewObject(viewObject);

            binding.tvSubtitle.setVisibility
                    (viewObject.getSubTitle().isEmpty() ? View.INVISIBLE : View.VISIBLE);

            binding.ivIcon.setImageDrawable(viewObject.getIcon());

            binding.executePendingBindings();
        }
    }
}
