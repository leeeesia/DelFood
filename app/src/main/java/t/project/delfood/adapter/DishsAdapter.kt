package t.project.delfood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import t.project.delfood.databinding.CardDishBinding
import t.project.delfood.dto.Dish


class DishAdapter() : ListAdapter<Dish, RecyclerView.ViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardDishBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DishViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is Dish -> (holder as? DishViewHolder)?.bind(item)
            else -> error("unknown item type")
        }
    }
    class DishViewHolder(
        private val binding: CardDishBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dish: Dish) {
            binding.apply {
                dishName.text = dish.name
                description.text = dish.content
                price.text = "от 350р"
                val url = dish.image
                    ?: "https://cdn2.iconfinder.com/data/icons/food-solid-icons-volume-2/128/059-1024.png"
                Glide.with(image)
                    .load(url)
                    .timeout(10_000)
                    .into(image)
            }
        }
    }


    class PostDiffCallback : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            if (oldItem::class != newItem::class) {
                return false
            }
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem == newItem
        }

    }
}