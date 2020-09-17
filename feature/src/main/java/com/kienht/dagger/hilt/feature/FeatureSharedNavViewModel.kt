package com.kienht.dagger.hilt.feature

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * @author kienht
 * @since 16/09/2020
 */
class FeatureSharedNavViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
}