package com.kienht.dagger.hilt.feature.detail

import android.content.Context
import androidx.fragment.app.Fragment
import com.kienht.dagger.hilt.core.UserModel
import com.kienht.dagger.hilt.core.di.CoreModuleDependencies
import com.kienht.dagger.hilt.core.di.FragmentViewModelModule
import com.kienht.dagger.hilt.core.di.UserModelFeatureQualifier
import com.kienht.dagger.hilt.feature.FeatureSharedNavViewModel_HiltModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.FragmentComponent

/**
 * @author kienht
 * @since 15/09/2020
 */
fun FeatureDetailFragment.inject(context: Context) {
    DaggerFeatureDetailFragmentComponent.factory()
        .featureDetailComponent(
            this,
            EntryPointAccessors.fromApplication(
                context.applicationContext,
                CoreModuleDependencies::class.java
            )
        )
        .inject(this)
}

@Component(
    dependencies = [CoreModuleDependencies::class],
    modules = [FeatureDetailFragmentModule::class]
)
interface FeatureDetailFragmentComponent {

    fun inject(fragment: FeatureDetailFragment)

    fun fragment(): Fragment

    @Component.Factory
    interface Factory {
        fun featureDetailComponent(
            @BindsInstance fragment: Fragment,
            loginModuleDependencies: CoreModuleDependencies
        ): FeatureDetailFragmentComponent
    }
}

@Module(
    includes = [
        FeatureDetailViewModel_HiltModule::class,
        FeatureSharedNavViewModel_HiltModule::class,
        FragmentViewModelModule::class
    ]
)
@InstallIn(FragmentComponent::class)
abstract class FeatureDetailFragmentModule {

    companion object {

        @Provides
        @UserModelFeatureQualifier
        fun provideUserModel() = UserModel(value = "FeatureDetailFragment")
    }
}