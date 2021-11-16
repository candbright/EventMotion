package com.example.app.item;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;
import com.example.app.common.Viewable;

/**
 * Created by NiXing on 2018/11/27.
 */
public class GroupTitleItem extends SortedItem implements Viewable {
    private String text;
    private int gravity = Gravity.BOTTOM;
    private boolean refresh;

    public GroupTitleItem(String text) {
        this.text = text;
    }

    @Override
    public int layoutID() {
        return R.layout.item_group;
    }

    @Override
    public RecyclerView.ViewHolder obtainViewHolder(ViewGroup parent, boolean attachToRoot) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutID(), parent, false);
        if (null == view) {
            return null;
        }
        return new GroupTitleHolder(view);
    }

    public String getText() {
        return text;
    }

    public GroupTitleItem setText(String text) {
        this.text = text;
        return this;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getGravity() {
        return gravity;
    }

    public void showRefresh() {
        refresh = true;
    }

    public boolean isRefresh() {
        return refresh;
    }

    @Override
    public String toString() {
        return "GroupTitleItem{" +
                "text='" + text + '\'' +
                ", gravity=" + gravity +
                ", refresh=" + refresh +
                '}';
    }
}
