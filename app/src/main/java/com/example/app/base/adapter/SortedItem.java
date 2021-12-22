package com.example.app.base.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class SortedItem<Holder extends BaseViewHolder, BindingView extends ViewBinding> implements Cloneable {
    private long sortedIndex;

    public Holder bindViewHolder(ViewGroup parent) {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class cls = (Class) type.getActualTypeArguments()[1];
        BindingView viewBinding = null;
        try {
            //View view = LayoutInflater.from(parent.getContext()).inflate(layoutID(), parent, false);
            Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            viewBinding = (BindingView) inflate.invoke(null, LayoutInflater.from(parent.getContext()), parent, false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        Holder viewHolder = createViewHolder(viewBinding);
        viewHolder.rootBinding = viewBinding;
        return viewHolder;
    }

    protected abstract Holder createViewHolder(BindingView viewBinding);

    public long getSortedIndex() {
        return sortedIndex;
    }

    public SortedItem setSortedIndex(long index) {
        this.sortedIndex = index;
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(sortedIndex);
    }

    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
