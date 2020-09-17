package com.kienht.dagger.hilt.feature.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kienht.dagger.hilt.core.UserModel
import com.kienht.dagger.hilt.core.di.UserModelFeatureQualifier

/**
 * @author kienht
 * @since 15/09/2020
 */
class FeatureDetailViewModel @ViewModelInject constructor(
    @UserModelFeatureQualifier val userModel: UserModel,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
}