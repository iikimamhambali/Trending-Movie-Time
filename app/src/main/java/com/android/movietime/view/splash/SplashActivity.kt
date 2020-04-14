package com.android.movietime.view.splash

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import com.android.movietime.R
import com.android.movietime.base.BaseActivity
import com.android.movietime.view.home.HomeActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {

    companion object {
        private const val DELAY_SPLASH_SCREEN: Long = 3000
    }

    override fun getLayoutResId(): Int = R.layout.activity_splash

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        Handler().postDelayed({
            startActivity<HomeActivity>()
            this.finish()
        }, DELAY_SPLASH_SCREEN)
    }

    override fun onResume() {
        super.onResume()
        setupVersion()
    }

    private fun setupVersion() {
        tvVersion.text = loadVersionNumber()
    }

    private fun loadVersionNumber(): String =
        try {
            val info = packageManager.getPackageInfo(packageName, 0)
            "V." + info.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            ""
        }

}
