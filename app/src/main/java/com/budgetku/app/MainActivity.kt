package com.budgetku.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
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

        // 🟢 SOLUSI UTAMA: Tidak memakai setupWithNavController bawaan karena fungsi itu
        // melakukan popUpTo ke startDestination graph (loginFragment), bukan ke
        // dashboardFragment. Sebagai gantinya, navigasi diatur manual di sini supaya
        // kelima tab (Dashboard, Riwayat, Tambah, Budget, Statistik) saling menggantikan
        // satu sama lain di back stack dengan benar, dan tetap satu thread yang sama
        // dengan navigasi tombol manual di Dashboard (ID destination-nya identik).
        val bottomNavDestinations = setOf(
            R.id.dashboardFragment,
            R.id.transactionHistoryFragment,
            R.id.addTransactionFragment,
            R.id.budgetFragment,
            R.id.statisticsFragment
        )

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            if (item.itemId == navController.currentDestination?.id) {
                true
            } else {
                val navOptions = NavOptions.Builder()
                    .setLaunchSingleTop(true)
                    .setPopUpTo(R.id.dashboardFragment, false)
                    .build()
                navController.navigate(item.itemId, null, navOptions)
                true
            }
        }

        // Sorot item navbar yang sesuai saat destination berubah dari jalur manapun
        // (klik navbar ATAU klik tombol/teks di Dashboard), supaya navbar selalu sinkron.
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in bottomNavDestinations) {
                binding.bottomNavigation.menu.findItem(destination.id)?.isChecked = true
            }
        }

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