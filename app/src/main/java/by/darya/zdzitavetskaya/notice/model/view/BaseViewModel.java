package by.darya.zdzitavetskaya.notice.model.view;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.darya.zdzitavetskaya.notice.R;
import by.darya.zdzitavetskaya.notice.common.interfaces.Listener;
import by.darya.zdzitavetskaya.notice.ui.holder.BaseViewHolder;

public abstract class BaseViewModel {

    public abstract LayoutTypes getType();

    public BaseViewHolder createViewHolder(Listener listener, ViewGroup parent) {
        return onCreateViewHolder(listener, LayoutInflater.from(parent.getContext()).inflate(getType().getResId(), parent, false));
    }

    protected abstract BaseViewHolder onCreateViewHolder(Listener listener, View view);

    public enum LayoutTypes {
        NoticeItem(R.layout.notice_item);

        private final int id;

        LayoutTypes(int resId) {
            this.id = resId;
        }

        @LayoutRes
        public int getResId() {
            return id;
        }
    }
}
