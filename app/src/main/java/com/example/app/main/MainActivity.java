package com.example.app.main;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.app.R;
import com.example.app.base.activity.BaseExitActivity;
import com.example.app.base.adapter.SortedItem;
import com.example.app.common.adapter.MySortedListAdapterCallback;
import com.example.app.common.adapter.SortedAdapter;
import com.example.app.common.component.MainNavigationBar;
import com.example.app.common.listener.OnItemEventListener;
import com.example.app.global.GlobalApp;

/**
 * <p>created by wyh in 2021/11/15</p>
 */
public class MainActivity extends BaseExitActivity {

    private static final String TAG = "MainActivity";
    public static final int INDEX_GO_ACTIVITY_REGISTER = 0;
    public static final int INDEX_LIGHT_MODE_NIGHT = 1;
    public static final int INDEX_LIGHT_MODE_DAY = 2;

    RecyclerView recyclerView;
    MainNavigationBar navigationBar;
    SortedAdapter sortedAdapter;
    SortedList<SortedItem> mData;

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreateViewModule() {
        initView();
        initData();
    }

    private void initView() {
        recyclerView = findViewById(R.id.rl_group);
        navigationBar = findViewById(R.id.nb_main);
        navigationBar.setTitle(GlobalApp.getResString(R.string.navigation_title_fancy));
    }

    private void initData() {
        sortedAdapter = new SortedAdapter();
        mData = new SortedList<>(SortedItem.class, new MySortedListAdapterCallback(sortedAdapter));
        sortedAdapter.setSortedList(mData);
        recyclerView.setAdapter(sortedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void newExternalRelations() {
        new MainRelations(this);
    }

    public void setOnItemEventListener(OnItemEventListener listener) {
        sortedAdapter.setOnItemEventListener(listener);
    }

    public void setRlL(RecyclerView.OnFlingListener onFlingListener) {
        recyclerView.setOnFlingListener(onFlingListener);
    }
}
