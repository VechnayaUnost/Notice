package by.darya.zdzitavetskaya.notice.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import by.darya.zdzitavetskaya.notice.common.interfaces.Listener;
import by.darya.zdzitavetskaya.notice.model.view.BaseViewModel;
import by.darya.zdzitavetskaya.notice.ui.holder.BaseViewHolder;

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseViewModel>> {

    private final List<BaseViewModel> list = new ArrayList<>();

    private final ArrayMap<Integer, BaseViewModel> typeInstances = new ArrayMap<>();

    Listener listener;

    public BaseAdapter(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder<BaseViewModel> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return typeInstances.get(i).createViewHolder(listener, viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<BaseViewModel> baseViewModelBaseViewHolder, int i) {
        baseViewModelBaseViewHolder.bindViewHolder(getItem(i));
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder<BaseViewModel> holder) {
        super.onViewRecycled(holder);
        holder.unbindViewHolder();
    }

    @Override
    public int getItemCount() {
        if(list == null)
            return 0;
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType().getResId();
    }

    private BaseViewModel getItem(int position) {
        return list.get(position);
    }

    private void registerTypeInstance(BaseViewModel item) {
        if (!typeInstances.containsKey(item.getType().getResId())) {
            typeInstances.put(item.getType().getResId(), item);
        }
    }

    public void addItems(List<? extends BaseViewModel> newItems) {
        for (BaseViewModel newItem : newItems) {
            registerTypeInstance(newItem);
        }

        list.addAll(newItems);

        notifyDataSetChanged();
    }

    public void setItems(List<BaseViewModel> items) {
        clearList();
        addItems(items);
    }

    private void clearList() {
        list.clear();
    }

    public void deleteItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void deleteItems() {
        clearList();
        notifyDataSetChanged();
    }

    public void insertItem(BaseViewModel newItem) {
        registerTypeInstance(newItem);

        list.add(newItem);
        notifyItemInserted(getItemCount() - 1);
    }
}
