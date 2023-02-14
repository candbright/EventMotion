package com.candbright.onestop.ui.home;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.candbright.onestop.R;
import com.candbright.onestop.base.adapter.SortedItemList;
import com.candbright.onestop.base.fragment.BaseFragment;
import com.candbright.onestop.base.fragment.FragmentLifecycleListener;
import com.candbright.onestop.dao.QuestionBankDaoHelper;
import com.candbright.onestop.databinding.FragmentQuestionBankBinding;
import com.candbright.onestop.databinding.NavigationBarBinding;
import com.candbright.onestop.app.GlobalApp;
import com.candbright.onestop.manager.widget.NavigationBarManager;
import com.candbright.onestop.model.adapter.MyDiffAdapter;
import com.candbright.onestop.model.data.QuestionBank;
import com.candbright.onestop.model.item.QuestionBankItem;

import java.util.List;

public class QuestionBankFragment extends BaseFragment<FragmentQuestionBankBinding> {
    private static final String TAG = "<QuestionBankFragment>";
    private NavigationBarBinding navigationBarTop;
    private NavigationBarManager navigationBarTopManager;

    MyDiffAdapter sortedAdapter;
    SortedItemList<QuestionBankItem> mData;

    @Override
    protected void initViewBinding() {
        navigationBarTop = NavigationBarBinding.bind(rootBinding.getRoot());
    }

    @Override
    protected FragmentLifecycleListener createLifecycleListener() {
        return new FragmentLifecycleListener() {
            @Override
            public void onViewCreated() {
                super.onViewCreated();
                initData();
            }
        };
    }

    @Override
    protected void initManager() {
        navigationBarTopManager = new NavigationBarManager(navigationBarTop);
        navigationBarTopManager.setTitle(R.string.title_question_bank);
        getRootBinding().refreshLayout.setOnRefreshListener(() -> {
            getRootBinding().refreshLayout.setRefreshing(false);
        });
    }

    private void initData() {
        //主界面数据
        mData = new SortedItemList(true);
        QuestionBankDaoHelper daoHelper = QuestionBankDaoHelper.getInstance(GlobalApp.getInstance());
        List<QuestionBank> data = daoHelper.searchAll();
        for (int i = 0; i < data.size(); i++) {
            QuestionBank item = data.get(i);
            mData.add((QuestionBankItem) new QuestionBankItem().setSubject(item.getSubject())
                    .setSortedIndex(item.getId().intValue()));
        }
        sortedAdapter = new MyDiffAdapter(mData.list());
        rootBinding.rvDataList.setAdapter(sortedAdapter);
        rootBinding.rvDataList.setLayoutManager(new LinearLayoutManager(getContext()));
        sortedAdapter.setOnItemListener((tag, switchValue, d) -> {
        });
    }

}
