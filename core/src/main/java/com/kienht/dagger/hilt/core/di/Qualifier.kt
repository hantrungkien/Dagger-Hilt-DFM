package com.kienht.dagger.hilt.core.di

import javax.inject.Qualifier

/**
 * @author kienht
 * @since 15/09/2020
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserModelSingletonQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserModelFeatureQualifier
