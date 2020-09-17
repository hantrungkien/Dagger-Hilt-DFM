package com.kienht.dagger.hilt.feature.list

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.kienht.dagger.hilt.core.DFMSavedStateViewModelFactory
import com.kienht.dagger.hilt.core.UserModel
import com.kienht.dagger.hilt.core.di.UserModelSingletonQualifier
import com.kienht.dagger.hilt.feature.FeatureActivityViewModel
import com.kienht.dagger.hilt.feature.FeatureSharedNavViewModel
import com.kienht.dagger.hilt.feature.R
import timber.log.Timber
import javax.inject.Inject

/**
 * @author kienht
 * @since 15/09/2020
 */
class FeatureListFragment : Fragment(R.layout.feature_list_fragment) {

    @Inject
    @UserModelSingletonQualifier
    lateinit var singletonUserModel: UserModel

    @Inject
    lateinit var savedStateViewModelFactory: DFMSavedStateViewModelFactory

    private val featureActivityViewModel by activityViewModels<FeatureActivityViewModel> { savedStateViewModelFactory }

    private val featureListViewModel by viewModels<FeatureListViewModel> { savedStateViewModelFactory }

    private val featureSharedNavViewModel by navGraphViewModels<FeatureSharedNavViewModel>(R.id.feature_nav_graph) { savedStateViewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.e("singleton userModel = $singletonUserModel")
        singletonUserModel.value += " => FeatureListFragment"

        Timber.e("userModel of Activity= ${featureActivityViewModel.userModel}")
        Timber.e("userModel of Fragment = ${featureListViewModel.userModel}")

        view.findViewById<Button>(R.id.button_detail)
            .setOnClickListener {
                findNavController().navigate(FeatureListFragmentDirections.goToFeatureDetailFragment())
            }
    }
}