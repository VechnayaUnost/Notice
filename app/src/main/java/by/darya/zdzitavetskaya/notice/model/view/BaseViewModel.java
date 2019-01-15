package by.darya.zdzitavetskaya.notice.model.view;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.darya.zdzitavetskaya.notice.R;
import by.darya.zdzitavetskaya.notice.ui.holder.BaseViewHolder;

public abstract class BaseViewModel {

    public abstract LayoutTypes getType();

    public BaseViewHolder createViewHolder(ViewGroup parent) {
        return onCreateViewHolder(LayoutInflater.from(parent.getContext()).inflate(getType().getResId(), parent, false));
    }

    protected abstract BaseViewHolder onCreateViewHolder(View view);

    public enum LayoutTypes {
        CurrentNotice(R.layout.notice_item);

        private final int id;

        LayoutTypes(int resId) {
            this.id = resId;
        }

        @LayoutRes
        public int getResId() {
            return id;
        }
    }

    public boolean isItemDecorator() {
        return false;
    }
}
