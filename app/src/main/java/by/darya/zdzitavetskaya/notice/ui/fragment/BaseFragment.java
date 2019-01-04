package by.darya.zdzitavetskaya.notice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    public abstract int getLayoutFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutFragment(), container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        refWatcher.watch(this);

        unbinder.unbind();
    }
}
