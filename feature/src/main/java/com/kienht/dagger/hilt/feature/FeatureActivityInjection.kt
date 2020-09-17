package com.kienht.dagger.hilt.feature

import android.app.Activity
import com.kienht.dagger.hilt.core.UserModel
import com.kienht.dagger.hilt.core.di.ActivityViewModelModule
import com.kienht.dagger.hilt.core.di.CoreModuleDependencies
import com.kienht.dagger.hilt.core.di.UserModelFeatureQualifier
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent

/**
 * @author kienht
 * @since 15/09/2020
 */
fun FeatureActivity.inject() {
    DaggerFeatureActivityComponent.factory()
        .featureActivityComponent(
            this,
            EntryPointAccessors.fromApplication(
                applicationContext,
                CoreModuleDependencies::class.java
            )
        )
        .inject(this)
}

@Component(
    dependencies = [CoreModuleDependencies::class],
    modules = [FeatureActivityModule::class]
)
interface FeatureActivityComponent {

    fun inject(activity: FeatureActivity)

    fun activity(): Activity

    @Component.Factory
    interface Factory {
        fun featureActivityComponent(
            @BindsInstance activity: Activity,
            loginModuleDependencies: CoreModuleDependencies
        ): FeatureActivityComponent
    }
}

@Module(includes = [FeatureActivityViewModel_HiltModule::class, ActivityViewModelModule::class])
@InstallIn(ActivityComponent::class)
abstract class FeatureActivityModule {

    companion object {

        @Provides
        @UserModelFeatureQualifier
        fun provideUserModel() = UserModel(value = "FeatureActivity")
    }
}