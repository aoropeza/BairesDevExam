package com.example.bairesdevexam.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.bairesdevexam.adapters.ViewHolders.RepositoryViewHolder;
import com.example.bairesdevexam.entities.Repository;
import com.example.bairesdevexam.R;
import com.example.bairesdevexam.databinding.ItemRepositoryBinding;
import com.example.bairesdevexam.interfaces.IRowClicked;
import java.util.List;

public class RepositoriesAdapter  extends RecyclerView.Adapter<RepositoryViewHolder> {
    public List<Repository> repositories;
    private LayoutInflater layoutInflater;
    private IRowClicked rowClickedImpl;

    public RepositoriesAdapter(Context context, List<Repository> repositories, IRowClicked rowClicked){
        this.repositories = repositories;
        this.layoutInflater = LayoutInflater.from( context );
        this.rowClickedImpl = rowClicked;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRepositoryBinding numberBinding = DataBindingUtil.inflate( layoutInflater, R.layout.item_repository, parent, false /* attachToRoot */);
        return new RepositoryViewHolder( numberBinding, rowClickedImpl);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        // Setting the binding reference
        Repository repository = repositories.get( position );
        holder.bind(repository);

    }

    @Override
    public int getItemCount() {
        return repositories == null ? 0 : repositories.size();
    }

}
