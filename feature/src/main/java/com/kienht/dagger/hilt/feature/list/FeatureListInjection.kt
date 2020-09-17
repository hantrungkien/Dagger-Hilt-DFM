package com.kienht.dagger.hilt.feature.list

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
fun FeatureListFragment.inject(context: Context) {
    DaggerFeatureListFragmentComponent.factory()
        .featureListComponent(
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
    modules = [FeatureListFragmentModule::class]
)
interface FeatureListFragmentComponent {

    fun inject(fragment: FeatureListFragment)

    fun fragment(): Fragment

    @Component.Factory
    interface Factory {
        fun featureListComponent(
            @BindsInstance fragment: Fragment,
            loginModuleDependencies: CoreModuleDependencies
        ): FeatureListFragmentComponent
    }
}

@Module(
    includes = [
        FeatureListViewModel_HiltModule::class,
        FeatureSharedNavViewModel_HiltModule::class,
        FragmentViewModelModule::class]
)
@InstallIn(FragmentComponent::class)
abstract class FeatureListFragmentModule {

    companion object {

        @Provides
        @UserModelFeatureQualifier
        fun provideUserModel() = UserModel(value = "FeatureListFragment")
    }
}