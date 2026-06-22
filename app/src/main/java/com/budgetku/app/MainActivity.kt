package com.budgetku.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController // 🟢 Pastikan baris import ini ada
import com.budgetku.app.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isKeyboardVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // 🟢 SOLUSI UTAMA: Hubungkan secara resmi. Karena ID XML kamu sudah sama,
        // fungsi ini akan mengatur perpindahan ikon & klik balik secara otomatis.
        binding.bottomNavigation.setupWithNavController(navController)

        // Deteksi kemunculan keyboard melalui tinggi layar global
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            val r = android.graphics.Rect()
            binding.root.getWindowVisibleDisplayFrame(r)
            val screenHeight = binding.root.rootView.height
            val keypadHeight = screenHeight - r.bottom
            val threshold = 200 * resources.displayMetrics.density

            val currentDestination = navController.currentDestination?.id
            val isAuthScreen = currentDestination == R.id.loginFragment || currentDestination == R.id.registerFragment

            if (keypadHeight > threshold) {
                isKeyboardVisible = true
                binding.bottomNavigation.visibility = View.GONE
            } else {
                isKeyboardVisible = false
                if (!isAuthScreen) {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }
            }
        }

        // Kontrol otomatis visibilitas halaman auth
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (!isKeyboardVisible) {
                binding.bottomNavigation.visibility = if (destination.id == R.id.loginFragment || destination.id == R.id.registerFragment) View.GONE else View.VISIBLE
            }
        }

        if (savedInstanceState == null && FirebaseAuth.getInstance().currentUser != null) {
            navController.navigate(R.id.dashboardFragment)
        }
    }
}