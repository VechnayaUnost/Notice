package by.darya.zdzitavetskaya.notice.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import by.darya.zdzitavetskaya.notice.model.view.BaseViewModel;
import io.reactivex.annotations.NonNull;

public abstract class BaseViewHolder <Item extends BaseViewModel> extends RecyclerView.ViewHolder{

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(Item item);

    public abstract void unbindViewHolder();
}
