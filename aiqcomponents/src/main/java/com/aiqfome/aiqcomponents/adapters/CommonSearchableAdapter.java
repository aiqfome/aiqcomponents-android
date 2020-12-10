package com.aiqfome.aiqcomponents.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aiqfome.aiqcomponents.databinding.ItemlistIconSubTitleBinding;
import com.aiqfome.aiqcomponents.databinding.ItemlistSubTitleBinding;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Multi-purpose Adapter that can be used for both BaseItem and IconItem Lists!
 * And it implements  Filterable too?! :0
 *
 * @param <Type> When using this class you need to specify the Type of the Object of this adapter
 * @author Bruno Cesar, bcesar.g6@gmail.com
 * @since 14/05/2020
 */
public abstract class CommonSearchableAdapter<Type>
        extends RecyclerView.Adapter<CommonSearchableAdapter.ItemViewHolder>
        implements Filterable {

    private boolean isIconAdapter;

    private List<BaseItem<Type>> baseItemList;
    private List<BaseItem<Type>> filteredBaseItemList;

    private List<IconItem<Type>> iconItemList;
    private List<IconItem<Type>> filteredIconItemList;

    private ItemFilter filter = new ItemFilter();

    protected CommonSearchableAdapter() {
    }

    public long getItemId(int position) {
        return position;
    }

    public void setBaseItemList(List<BaseItem<Type>> baseItemList) {
        this.baseItemList = baseItemList;
        this.filteredBaseItemList = baseItemList;
        this.isIconAdapter = false;
    }

    public void setIconItemList(List<IconItem<Type>> iconItemList) {
        this.iconItemList = iconItemList;
        this.filteredIconItemList = iconItemList;
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
            IconItem iconItem = filteredIconItemList.get(position);
            holder.bind(iconItem);
            holder.itemView.setOnClickListener(itemClickListener(iconItem));

        } else {
            BaseItem item = filteredBaseItemList.get(position);
            holder.bind(item);
            holder.itemView.setOnClickListener(itemClickListener(item));
        }
    }

    @Override
    public int getItemCount() {
        if (isIconAdapter) return filteredIconItemList != null ? filteredIconItemList.size() : 0;
        else return filteredBaseItemList != null ? filteredBaseItemList.size() : 0;
    }

    public Filter getFilter() {
        return filter;
    }

    public abstract View.OnClickListener itemClickListener(BaseItem<Type> object);

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<? extends BaseItem<Type>> list = isIconAdapter ? iconItemList : baseItemList;

            int count = list.size();

            final ArrayList<Object> filteredlist = new ArrayList<>(count);

            String filterableString;
            String filterableSubtitleString;

            for (int i = 0; i < count; i++) {
                filterableString = StringUtils.stripAccents(list.get(i).getTitle());
                filterString = StringUtils.stripAccents(filterString.toLowerCase());

                // If it's not an adapter list, we can search both title and subtitle.
                if (!isIconAdapter) {
                    filterableSubtitleString = StringUtils.stripAccents(list.get(i).getSubTitle());
                    if (filterableString.toLowerCase().contains(filterString)
                            || filterableSubtitleString.toLowerCase().contains(filterString))
                        filteredlist.add(list.get(i));
                } else {
                    if (filterableString.toLowerCase().contains(filterString))
                        filteredlist.add(list.get(i));
                }
            }

            results.values = filteredlist;
            results.count = filteredlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (isIconAdapter)
                filteredIconItemList = (List<IconItem<Type>>) results.values;
            else
                filteredBaseItemList = (List<BaseItem<Type>>) results.values;

            notifyDataSetChanged();
        }

    }

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
