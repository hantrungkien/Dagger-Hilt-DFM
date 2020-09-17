package com.kienht.dagger.hilt.core.di

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.hilt.lifecycle.ViewModelAssistedFactory
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import com.kienht.dagger.hilt.core.DFMSavedStateViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Provider

/**
 * @author kienht
 * @since 15/09/2020
 */
@Module
@InstallIn(FragmentComponent::class)
object FragmentViewModelModule {

    @Provides
    fun provideSavedStateViewModelFactory(
        application: Application,
        fragment: Fragment,
        viewModelFactories: @JvmSuppressWildcards Map<String, Provider<ViewModelAssistedFactory<out ViewModel>>>,
    ): DFMSavedStateViewModelFactory {
        val defaultArgs = fragment.arguments
        val delegate = SavedStateViewModelFactory(application, fragment, defaultArgs)
        return DFMSavedStateViewModelFactory(fragment, defaultArgs, delegate, viewModelFactories)
    }
}