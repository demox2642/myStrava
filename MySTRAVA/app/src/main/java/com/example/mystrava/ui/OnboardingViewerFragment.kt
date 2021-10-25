package com.example.mystrava.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mystrava.databinding.FragmentOnboardingViewerBinding
import com.example.mystrava.plugins.ViewBindingFragment
import com.example.mystrava.ui.onboarding.OnboardingOne
import com.example.mystrava.ui.onboarding.OnboardingToo
import com.example.mystrava.ui.onboarding.OnboardingViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_onboarding_viewer.*

class OnboardingViewerFragment : ViewBindingFragment<FragmentOnboardingViewerBinding>(FragmentOnboardingViewerBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onboardingFragmentList = arrayListOf<Fragment>(OnboardingOne(), OnboardingToo())
        onboarding_viewer.adapter = OnboardingViewPagerAdapter(onboardingFragmentList, requireActivity().supportFragmentManager, lifecycle)
    }
}
