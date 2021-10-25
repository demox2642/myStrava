package com.example.mystrava.ui.onboarding

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.mystrava.R
import com.example.mystrava.databinding.FragmentOnboardingOneBinding
import com.example.mystrava.plugins.ViewBindingFragment
import com.example.mystrava.ui.SplashFragment
import kotlinx.android.synthetic.main.fragment_onboarding_viewer.*

class OnboardingOne : ViewBindingFragment<FragmentOnboardingOneBinding>(FragmentOnboardingOneBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.onboarding_viewer)

        binding.nextButton.setOnClickListener {
            viewPager?.currentItem = viewPager?.currentItem?.plus(1)!!
        }

        binding.skipButton.setOnClickListener {
            onBoardingFinish()
            findNavController().navigate(R.id.action_onboardingViewerFragment_to_loginFragment)
        }
    }

    private fun onBoardingFinish() {
        val sharedPreferences = requireActivity().getSharedPreferences(SplashFragment.onBoarding, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(SplashFragment.finish, true)
        editor.apply()
    }
}
