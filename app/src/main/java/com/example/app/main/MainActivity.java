package com.example.app.main;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.app.R;
import com.example.app.base.activity.ExitActivity;
import com.example.app.common.MySortedListAdapterCallback;
import com.example.app.common.OnItemEventListener;
import com.example.app.common.SortedAdapter;
import com.example.app.item.SimpleCardItem;
import com.example.app.item.SortedItem;

/**
 * created by wyh in 2021/11/15
 */
public class MainActivity extends ExitActivity {

    private static final String TAG = "MainActivity";
    public static final int INDEX_GO_ACTIVITY_REGISTER = 0;
    public static final int INDEX_LIGHT_MODE_NIGHT = 1;
    public static final int INDEX_LIGHT_MODE_DAY = 2;

    RecyclerView recyclerView;
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
    }

    private void initData() {
        sortedAdapter = new SortedAdapter();
        mData = new SortedList<SortedItem>(SortedItem.class, new MySortedListAdapterCallback(sortedAdapter));
        sortedAdapter.setSortedList(mData);
        mData.add(new SimpleCardItem("立即注册").setIndex(INDEX_GO_ACTIVITY_REGISTER));
        mData.add(new SimpleCardItem("黑夜模式").setIndex(INDEX_LIGHT_MODE_NIGHT));
        mData.add(new SimpleCardItem("白天模式").setIndex(INDEX_LIGHT_MODE_DAY));
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
}
