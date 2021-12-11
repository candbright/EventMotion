package com.example.app.main;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;
import com.example.app.base.activity.BaseActivity;
import com.example.app.base.adapter.SortedItem;
import com.example.app.common.adapter.MyDiffAdapter;
import com.example.app.common.item.ImageCardItem;
import com.example.app.common.listener.OnItemEventListener;
import com.example.app.databinding.ActivityMainBinding;
import com.example.app.databinding.NavigationBarBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>created by wyh in 2021/11/15</p>
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private static final String TAG = "MainActivity";
    public static final int INDEX_GO_ACTIVITY_REGISTER = 0;
    public static final int INDEX_LIGHT_MODE_NIGHT = 1;
    public static final int INDEX_LIGHT_MODE_DAY = 2;
    private NavigationBarBinding navigationBarBinding;
    private MyDiffAdapter sortedAdapter;
    private List<SortedItem> mData;

    @Override
    protected void onCreateViewModule() {
        bindView();
        initData();
    }

    private void bindView() {
        navigationBarBinding = NavigationBarBinding.bind(viewBinding.getRoot());
        navigationBarBinding.titleTv.setText(R.string.navigation_title_fancy);
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new ImageCardItem());
        mData.add(new ImageCardItem());
        mData.add(new ImageCardItem());
        mData.add(new ImageCardItem());
        mData.add(new ImageCardItem());
        sortedAdapter = new MyDiffAdapter(mData);
        viewBinding.rvDataList.setAdapter(sortedAdapter);
        viewBinding.rvDataList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void newExternalRelations() {
        new MainRelations(this);
    }

    public void setOnItemEventListener(OnItemEventListener listener) {
        sortedAdapter.setOnItemListener(listener);
    }

    public void setRlL(RecyclerView.OnFlingListener onFlingListener) {
        viewBinding.rvDataList.setOnFlingListener(onFlingListener);
    }
}
