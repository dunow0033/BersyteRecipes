package com.express.android.recipeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.express.android.recipeapp.R
import com.express.android.recipeapp.adapter.RecipeAdapter
import com.express.android.recipeapp.databinding.FragmentHomeBinding
import com.express.android.recipeapp.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val viewModel: RecipeViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRv()
    }

    private fun setUpRv() {

        recipeAdapter = RecipeAdapter()

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = recipeAdapter
        }

        viewModel.recipeResponse.observe(requireActivity(),
            { result ->
                binding.tvTitle.text = result.title
                recipeAdapter.recipe = result.results
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}