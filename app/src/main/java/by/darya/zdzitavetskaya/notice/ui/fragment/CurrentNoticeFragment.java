package by.darya.zdzitavetskaya.notice.ui.fragment;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.OnClick;
import by.darya.zdzitavetskaya.notice.R;
import by.darya.zdzitavetskaya.notice.common.interfaces.UpdateListener;
import by.darya.zdzitavetskaya.notice.model.NoticeModel;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.presenter.CurrentNoticePresenter;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.view.CurrentNoticeView;

public class CurrentNoticeFragment extends BaseFragment implements CurrentNoticeView, UpdateListener {

    @InjectPresenter
    CurrentNoticePresenter mCurrentNoticePresenter;

    public CurrentNoticeFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.btn_show_dialog)
    void showDialog() {
        //
    }

    @Override
    public int getLayoutFragment() {
        return R.layout.fragment_current_notice;
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
}
