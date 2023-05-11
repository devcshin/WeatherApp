package com.devc.fitpettest.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.devc.fitpettest.BR
import com.devc.fitpettest.R
import com.devc.fitpettest.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseDataBindingFragment<FragmentMainBinding>(R.layout.fragment_main) {

    companion object {
        fun newInstance() = MainFragment()
    }

    override val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            setVariable(BR.viewModel, this@MainFragment.viewModel)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.rvForecast.adapter = viewModel.viewData.value?.let { it1 -> ForecastAdapter(it1) }
            binding.lottieLoading.pauseAnimation()
            binding.lottieLoading.visibility = View.GONE

        }
    }
}