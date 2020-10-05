package com.aiqfome.aiqcomponents.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aiqfome.aiqcomponents.databinding.ItemlistIconSubTitleBinding;
import com.aiqfome.aiqcomponents.databinding.ItemlistSubTitleBinding;

import java.util.List;

/**
 * Multi-purpose Adapter that can be used for both BaseItem and IconItem Lists!
 *
 * @author Bruno Cesar, bcesar.g6@gmail.com
 * @since 14/05/2020
 * @param <Type> When using this class you need to specify the Type of the Object of this adapter
 */
public abstract class CommonAdapter<Type>
        extends RecyclerView.Adapter<CommonAdapter.ItemViewHolder> {

    private boolean isIconAdapter;

    private List<BaseItem<Type>> baseItemList;
    private List<IconItem<Type>> iconItemList;

    protected CommonAdapter() { }

    public void setBaseItemList(List<BaseItem<Type>> baseItemList) {
        this.baseItemList = baseItemList;
        this.isIconAdapter = false;
    }

    public void setIconItemList(List<IconItem<Type>> iconItemList) {
        this.iconItemList = iconItemList;
        this.isIconAdapter = true;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (isIconAdapter)
            return new ItemViewHolder(ItemlistIconSubTitleBinding.inflate(inflater));
        else
            return new ItemViewHolder(ItemlistSubTitleBinding.inflate(inflater));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (isIconAdapter) {
            IconItem iconItem = iconItemList.get(position);
            holder.bind(iconItem);
            holder.itemView.setOnClickListener(itemClickListener(iconItem));

        } else {
            BaseItem item = baseItemList.get(position);
            holder.bind(item);
            holder.itemView.setOnClickListener(itemClickListener(item));
        }
    }

    @Override
    public int getItemCount() {
        if (isIconAdapter) return iconItemList != null ? iconItemList.size() : 0;
        else return baseItemList != null ? baseItemList.size() : 0;
    }

    public abstract View.OnClickListener itemClickListener(BaseItem<Type> object);

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        ItemlistSubTitleBinding binding;
        ItemlistIconSubTitleBinding iconBinding;

        ItemViewHolder(ItemlistSubTitleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        ItemViewHolder(ItemlistIconSubTitleBinding iconBinding) {
            super(iconBinding.getRoot());
            this.iconBinding = iconBinding;
        }

        void bind(BaseItem viewObject) {
            binding.setViewObject(viewObject);

            binding.tvSubtitle.setVisibility
                    (viewObject.getSubTitle().isEmpty() ? View.GONE : View.VISIBLE);

            binding.executePendingBindings();
        }

        void bind(IconItem viewObject) {
            iconBinding.setViewObject(viewObject);

            iconBinding.tvSubtitle.setVisibility
                    (viewObject.getSubTitle().isEmpty() ? View.INVISIBLE : View.VISIBLE);

            iconBinding.ivIcon.setImageDrawable(viewObject.getIcon());

            iconBinding.executePendingBindings();
        }
    }
}
