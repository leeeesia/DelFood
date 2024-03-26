package t.project.delfood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import t.project.delfood.databinding.ItemLoadingBinding

class DishLoadingStateAdapter(
    private val retryListener: () -> Unit,
) : LoadStateAdapter<DishLoadingViewHolder>() {
    override fun onBindViewHolder(holder: DishLoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): DishLoadingViewHolder =
        DishLoadingViewHolder(
            ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retryListener,
        )
}

class DishLoadingViewHolder(
    private val itemLoadingBinding: ItemLoadingBinding,
    private val retryListener: () -> Unit,
) : RecyclerView.ViewHolder(itemLoadingBinding.root) {
    fun bind(loadState: LoadState){
        itemLoadingBinding.apply {
            progress.isVisible = loadState is LoadState.Loading
            retryButton.isVisible= loadState is LoadState.Error
            retryButton.setOnClickListener {
                retryListener()
            }
        }
    }
}