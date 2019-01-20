package by.darya.zdzitavetskaya.notice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import by.darya.zdzitavetskaya.notice.R;
import by.darya.zdzitavetskaya.notice.common.interfaces.UpdateListener;
import by.darya.zdzitavetskaya.notice.model.NoteModel;
import by.darya.zdzitavetskaya.notice.model.view.BaseViewModel;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.presenter.CurrentNoticePresenter;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.view.CurrentNoticeView;
import by.darya.zdzitavetskaya.notice.ui.adapter.BaseAdapter;

public class CurrentNoticeFragment extends BaseFragment implements CurrentNoticeView, UpdateListener {

    @InjectPresenter
    CurrentNoticePresenter currentNoticePresenter;

    @BindView(R.id.rv_notice)
    RecyclerView recyclerView;

    BaseAdapter adapter;

    public CurrentNoticeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecycler();
        currentNoticePresenter.getNoticesFromDatabase();
        setupAdapter();
    }

    public void setupAdapter() {
        adapter = new BaseAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void setupRecycler() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        getRecycler().setLayoutManager(layoutManager);
    }

    @Override
    public int getLayoutFragment() {
        return R.layout.fragment_current_notice;
    }

    @Override
    public RecyclerView getRecycler() {
        return recyclerView;
    }

    @Override
    public void onNoticesSuccess(final List<BaseViewModel> notices) {
        ((BaseAdapter) recyclerView.getAdapter()).setItems(notices);
    }

    @Override
    public void onNoticeSuccess(NoteModel notice) {
        //display notice on the screen
    }

    @Override
    public void update(String name, String description) {

    }
}
