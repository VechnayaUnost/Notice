package by.darya.zdzitavetskaya.notice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import by.darya.zdzitavetskaya.notice.App;
import by.darya.zdzitavetskaya.notice.R;
import by.darya.zdzitavetskaya.notice.common.interfaces.UpdateListener;
import by.darya.zdzitavetskaya.notice.model.NoticeModel;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.presenter.CurrentNoticePresenter;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.view.CurrentNoticeView;

public class CurrentNoticeFragment extends MvpAppCompatFragment implements CurrentNoticeView, UpdateListener {

    @InjectPresenter
    CurrentNoticePresenter mCurrentNoticePresenter;

    public CurrentNoticeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_notice, container, false);
    }

    @Override
    public void onNoticesSuccess(final List<NoticeModel> notices) {
        //display all notices on the screen
    }

    @Override
    public void onNoticeSuccess(NoticeModel notice) {
        //display notice on the screen
    }

    @Override
    public void update(String name, String description) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
