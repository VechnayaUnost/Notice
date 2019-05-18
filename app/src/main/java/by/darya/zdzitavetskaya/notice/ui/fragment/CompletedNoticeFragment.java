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
import by.darya.zdzitavetskaya.notice.common.interfaces.Listener;
import by.darya.zdzitavetskaya.notice.model.view.BaseViewModel;
import by.darya.zdzitavetskaya.notice.presentation.completedNoticePresentation.presenter.CompletedNoticePresenter;
import by.darya.zdzitavetskaya.notice.presentation.completedNoticePresentation.view.CompletedNoticeView;
import by.darya.zdzitavetskaya.notice.ui.adapter.BaseAdapter;

public class CompletedNoticeFragment extends BaseFragment implements CompletedNoticeView, Listener {

    @InjectPresenter
    CompletedNoticePresenter completedNoticePresenter;

    @BindView(R.id.rv_notice)
    RecyclerView recyclerView;

    private BaseAdapter adapter;

    public CompletedNoticeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecycler();
        setupAdapter();
        completedNoticePresenter.getNoticesFromDatabase();
    }

    private void setupAdapter() {
        adapter = new BaseAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void setupRecycler() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        getRecycler().setLayoutManager(layoutManager);
    }

    @Override
    public RecyclerView getRecycler() {
        return recyclerView;
    }

    @Override
    public void onNoticesSuccess(List<BaseViewModel> notices) {
        adapter.setItems(notices);
    }

    @Override
    public void onItemClick(String id) {

    }

    @Override
    public void addToSolved(String id, int position) {

    }

    public void clearList() {
        adapter.deleteItems();
    }
}
