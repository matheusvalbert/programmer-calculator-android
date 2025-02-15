package com.matheusvalbert.programmercalculator.ui.history.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.matheusvalbert.programmercalculator.R;
import com.matheusvalbert.programmercalculator.core.domain.History;
import com.matheusvalbert.programmercalculator.databinding.HistoryItemBinding;
import com.matheusvalbert.programmercalculator.core.util.Base;
import com.matheusvalbert.programmercalculator.ui.history.mapper.HistoryMapper;
import com.matheusvalbert.programmercalculator.ui.listener.OnClickListener;
import com.matheusvalbert.programmercalculator.ui.util.Util;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private final OnClickListener mOnClickListener;

    public HistoryAdapter(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    private final AsyncListDiffer<HistoryItem> mAsyncListDiffer =
            new AsyncListDiffer<>(this, new DiffCallback());

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        HistoryItemBinding binding = HistoryItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mAsyncListDiffer.getCurrentList().get(position));
    }

    public void updateHistory(List<HistoryItem> historyItems) {
        mAsyncListDiffer.submitList(historyItems);
    }

    @Override
    public int getItemCount() {
        return mAsyncListDiffer.getCurrentList().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final HistoryItemBinding mBinding;
        private final OnClickListener mOnClickListener;

        public ViewHolder(HistoryItemBinding binding, OnClickListener onClickListener) {
            super(binding.getRoot());
            mBinding = binding;
            mOnClickListener = onClickListener;
        }

        public void bind(HistoryItem historyItem) {
            mBinding.expression.setText(historyItem.getExpression());
            mBinding.base.setText(Util.getBaseString(historyItem.getBase()));
            mBinding.result.setText(historyItem.getResult());
            mBinding.getRoot().setOnClickListener(v -> {
                History history = HistoryMapper.toHistory(historyItem);
                mOnClickListener.onClick(history);
            });
            mBinding.getRoot().setOnLongClickListener(v -> {
                History history = HistoryMapper.toHistory(historyItem);
                mOnClickListener.onLongClick(history);
                return true;
            });
        }
    }

    private static class DiffCallback extends DiffUtil.ItemCallback<HistoryItem> {
        @Override
        public boolean areItemsTheSame(@NonNull HistoryItem oldItem, @NonNull HistoryItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull HistoryItem oldItem, @NonNull HistoryItem newItem) {
            return oldItem.equals(newItem);
        }
    }
}
