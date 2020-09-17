package com.kienht.dagger.hilt.dfm.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.kienht.dagger.hilt.dfm.databinding.SplashActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * @author kienht
 * @since 15/09/2020
 */
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val splashViewModel by viewModels<SplashViewModel>()

    private lateinit var binding: SplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashActivityBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        Timber.e("singleton userModel = ${splashViewModel.singletonUserModel}")
        splashViewModel.singletonUserModel.value += " => SplashActivity"

        splashViewModel.get("demo_key")
            .observe(this, Observer {
                Timber.e("demo_key = $it")
            })

        splashViewModel.put("demo_key", "demo_value")

        binding.buttonFeature.setOnClickListener {
            val clazz = Class.forName("com.kienht.dagger.hilt.feature.FeatureActivity")
            startActivity(Intent(this, clazz))
        }
    }
}