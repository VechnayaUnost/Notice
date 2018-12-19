package by.darya.zdzitavetskaya.notice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import by.darya.zdzitavetskaya.notice.R;
import by.darya.zdzitavetskaya.notice.model.NoticeModel;
import by.darya.zdzitavetskaya.notice.presentation.presenter.NoticePresenter;
import by.darya.zdzitavetskaya.notice.presentation.view.NoticeView;

public class NoticeFragment extends MvpAppCompatFragment implements NoticeView{

    @InjectPresenter
    NoticePresenter noticePresenter;

    public NoticeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notice, container, false);
    }

    @Override
    public void onNoticesSuccess(final List<NoticeModel> notices) {
        //display all notices on the screen
    }

    @Override
    public void onNoticeSuccess(NoticeModel notice) {
        //display notice on the screen
    }
}
