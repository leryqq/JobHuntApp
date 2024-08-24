package com.example.jobhuntapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.jobhuntapp.ViewModels.BottomNavViewModel
import com.example.jobhuntapp.ViewModels.Tab
import com.example.jobhuntapp.databinding.ActivityMainBinding
import com.example.jobhuntapp.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: BottomNavViewModel
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[BottomNavViewModel::class.java]
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        /*binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.search -> {
                    binding.toolbar.visibility = View.GONE
                    viewModel.selectTab(Tab.SEARCH)
                    true
                }
                R.id.favorites -> {
                    viewModel.selectTab(Tab.FAVORITE)
                    true
                }
                R.id.responses -> {
                    viewModel.selectTab(Tab.RESPONSES)
                    true
                }
                R.id.messages -> {
                    viewModel.selectTab(Tab.MESSAGES)
                    true
                }
                R.id.profile -> {
                    viewModel.selectTab(Tab.PROFILE)
                    true
                }
                else -> false
            }
        }*/

        lifecycleScope.launch {
            viewModel.selectedTab.collect {
                when(it) {
                    Tab.SEARCH -> navController.navigate(R.id.homeFragment)
                    Tab.FAVORITE -> navController.navigate(R.id.favoriteFragment)
                    Tab.RESPONSES -> navController.navigate(R.id.homeFragment)
                    Tab.MESSAGES -> navController.navigate(R.id.homeFragment)
                    Tab.PROFILE -> navController.navigate(R.id.homeFragment)
                    else -> {}
                }
            }
        }

        binding.bottomNavigationView.getOrCreateBadge(R.id.favorites).apply {
            number = 1
            isVisible = true
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.authFragment || destination.id == R.id.authCodeFragment) {
                binding.toolbar.visibility = View.GONE
                binding.bottomNavigationView.setOnItemSelectedListener(null)
            } else {
                binding.bottomNavigationView.setOnItemSelectedListener { item ->
                    when(item.itemId) {
                        R.id.search -> {
                            binding.toolbar.visibility = View.GONE
                            viewModel.selectTab(Tab.SEARCH)
                            true
                        }
                        R.id.favorites -> {
                            binding.toolbar.visibility = View.VISIBLE
                            viewModel.selectTab(Tab.FAVORITE)
                            true
                        }
                        R.id.responses -> {
                            binding.toolbar.visibility = View.VISIBLE
                            viewModel.selectTab(Tab.RESPONSES)
                            true
                        }
                        R.id.messages -> {
                            binding.toolbar.visibility = View.VISIBLE
                            viewModel.selectTab(Tab.MESSAGES)
                            true
                        }
                        R.id.profile -> {
                            binding.toolbar.visibility = View.VISIBLE
                            viewModel.selectTab(Tab.PROFILE)
                            true
                        }
                        else -> false
                    }
                }
            }
        }
    }
}