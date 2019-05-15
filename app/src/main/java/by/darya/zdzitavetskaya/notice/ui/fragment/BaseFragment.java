package by.darya.zdzitavetskaya.notice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import by.darya.zdzitavetskaya.notice.App;

public abstract class BaseFragment extends MvpAppCompatFragment {

    private Unbinder unbinder;

    @LayoutRes
    protected abstract int getLayoutFragment();

    public abstract RecyclerView getRecycler();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(getLayoutFragment(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        refWatcher.watch(this);

        unbinder.unbind();
    }
}
