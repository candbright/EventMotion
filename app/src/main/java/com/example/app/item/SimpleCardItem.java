package com.example.app.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;

/**
 * created by wyh in 2021/10/26
 */
public class SimpleCardItem extends SortedItem {
    private String explainText;

    public SimpleCardItem(String explainText) {
        this.explainText = explainText;
    }

    public String getExplainText() {
        return explainText;
    }

    public void setExplainText(String explainText) {
        this.explainText = explainText;
    }

    @Override
    public int layoutID() {
        return R.layout.item_card;
    }

    @Override
    public RecyclerView.ViewHolder obtainViewHolder(ViewGroup parent, boolean attachToRoot) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutID(), parent, false);
        if (null == view) {
            return null;
        }
        return new SimpleCardHolder(view);
    }
}
