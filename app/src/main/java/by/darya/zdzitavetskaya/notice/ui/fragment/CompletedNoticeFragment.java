package by.darya.zdzitavetskaya.notice.ui.fragment;

import android.support.v7.widget.RecyclerView;

import by.darya.zdzitavetskaya.notice.R;

public class CompletedNoticeFragment extends BaseFragment {

    public CompletedNoticeFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutFragment() {
        return R.layout.fragment_completed_notice;
    }

    @Override
    public RecyclerView getRecycler() {
        return null;
    }
}
