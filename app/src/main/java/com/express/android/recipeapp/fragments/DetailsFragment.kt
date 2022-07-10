package com.express.android.recipeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.express.android.recipeapp.R
import com.express.android.recipeapp.databinding.FragmentDetailsBinding
import com.express.android.recipeapp.models.Result

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    private lateinit var recipe: Result

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipe = args.recipe

        populateUI()
    }

    private fun populateUI(){
        binding.apply {
            tvIngredients.text = recipe.ingredients
            tvTitleRecipe.text = recipe.title
            ivDetails.load(recipe.thumbnail) {
                crossfade(true)
                crossfade(1000)
            }

            btnOpenWebView.setOnClickListener { mView ->
                val direction = DetailsFragmentDirections
                    .actionDetailFragment2ToWebViewFragment(recipe)
                mView.findNavController().navigate(direction)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}