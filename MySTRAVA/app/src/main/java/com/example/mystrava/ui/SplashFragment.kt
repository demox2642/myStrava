package com.example.mystrava.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mystrava.R
import com.example.mystrava.databinding.FragmentSplashBinding
import com.example.mystrava.plugins.ViewBindingFragment

class SplashFragment : ViewBindingFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed(
            {
                if (onBoardingFinished()) {
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_onboardingViewerFragment)
                }
            },
            2000
        )
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPreferences = requireActivity().getSharedPreferences(onBoarding, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(finish, false)
    }

    companion object {
        const val onBoarding = "OnBoarding"
        const val finish = "Finish"
    }
}
