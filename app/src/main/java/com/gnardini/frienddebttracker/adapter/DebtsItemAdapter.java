package com.gnardini.frienddebttracker.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gnardini.frienddebttracker.R;
import com.gnardini.frienddebttracker.databinding.DebtsItemBinding;
import com.gnardini.frienddebttracker.screen.debts.DebtsPresenter;
import com.gnardini.frienddebttracker.model.Debt;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DebtsItemAdapter extends RecyclerView.Adapter<DebtsItemAdapter.DebtItemViewHolder> {

    private final DebtsPresenter debtsPresenter;
    private final List<Debt> list;

    public DebtsItemAdapter(DebtsPresenter debtsPresenter) {
        this.debtsPresenter = debtsPresenter;
        this.list = new LinkedList<>();
    }

    @Override
    public DebtItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DebtsItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.debts_item, parent, false);
        return new DebtItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DebtItemViewHolder holder, int position) {
        holder.debt = list.get(position);
        holder.binding.setDebt(holder.debt);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItems(List<Debt> debts) {
        this.list.clear();
        this.list.addAll(debts);
        notifyDataSetChanged();
    }

     class DebtItemViewHolder extends RecyclerView.ViewHolder {

        Debt debt;
        DebtsItemBinding binding;

        public DebtItemViewHolder(DebtsItemBinding binding) {
            super(binding.getRoot());
            ButterKnife.bind(this, binding.getRoot());
            this.binding = binding;
        }

        @OnClick(R.id.edit)
        public void onEditClicked() {
            DebtsItemAdapter.this.debtsPresenter.editDebt(debt);
        }

        @OnClick(R.id.delete)
        public void onDeleteClicked() {
            DebtsItemAdapter.this.debtsPresenter.deleteDebt(debt);
        }

    }

}
