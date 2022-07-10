package com.express.android.recipeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.express.android.recipeapp.databinding.RecipeLayoutAdapterBinding
import com.express.android.recipeapp.fragments.HomeFragment
import com.express.android.recipeapp.fragments.HomeFragmentDirections
import com.express.android.recipeapp.models.Result

class RecipeAdapter: RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            RecipeLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currRecipe = recipe[position]

        holder.binding.apply {
            tvTitleRecipe.text = currRecipe.title
            imageView.load(currRecipe.thumbnail) {
                crossfade(true)
                crossfade(1000)
            }
        }

        holder.itemView.setOnClickListener { mView ->
            val direction = HomeFragmentDirections
                .actionHomeFragment2ToDetailFragment2(currRecipe)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount() = recipe.size

    private val diffCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var recipe: List<Result>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    inner class RecipeViewHolder(val binding: RecipeLayoutAdapterBinding): RecyclerView.ViewHolder(binding.root)
}