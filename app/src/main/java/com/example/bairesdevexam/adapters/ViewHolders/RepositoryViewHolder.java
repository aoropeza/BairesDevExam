package com.example.bairesdevexam.adapters.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.bairesdevexam.databinding.ItemRepositoryBinding;
import com.example.bairesdevexam.entities.Repository;
import com.example.bairesdevexam.interfaces.IRowClicked;

public class RepositoryViewHolder extends RecyclerView.ViewHolder {

    private ItemRepositoryBinding mBinding;
    private IRowClicked rowClicked;

    public RepositoryViewHolder(ItemRepositoryBinding binding, IRowClicked rowClicked) {
        super(binding.getRoot());
        this.mBinding = binding;
        this.rowClicked = rowClicked;

    }

    public void bind(final Repository model) {
        mBinding.name.setText( model.name);
        mBinding.homepage.setText( model.homepage);
        mBinding.description.setText( model.description);


        mBinding.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowClicked.rowClicked(getAdapterPosition(), model);
            }
        });
    }
}