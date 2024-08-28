package com.example.jobhuntapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.jobhuntapp.ViewModels.BadgeViewModel
import com.example.jobhuntapp.ViewModels.BottomNavViewModel
import com.example.jobhuntapp.ViewModels.Tab
import com.example.jobhuntapp.ViewModels.VacancyViewModel
import com.example.jobhuntapp.databinding.ActivityMainBinding
import com.example.jobhuntapp.fragments.VacancyFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: BottomNavViewModel
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val badgeViewModel: BadgeViewModel by viewModels()
    private val vacancyViewModel: VacancyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this)[BottomNavViewModel::class.java]
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

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

        vacancyViewModel.isFavorite.observe(this) {
            binding.checkBoxFavorite.isChecked = it
        }

        binding.checkBoxFavorite.setOnCheckedChangeListener { _, isChecked ->
            vacancyViewModel.setFavorite(isChecked)
        }

        badgeViewModel.badgeNumber.observe(this) { number ->
            val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.favorites)
            badge.number = number
            badge.isVisible = number > 0
            badge.backgroundColor = ContextCompat.getColor(this@MainActivity, R.color.red)
            badge.badgeTextColor = ContextCompat.getColor(this@MainActivity, R.color.white)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.authFragment, R.id.authCodeFragment -> {
                    binding.bottomNavigationView.setOnItemSelectedListener(null)
                }
                else -> {
                    //binding.toolbar.visibility = View.GONE
                    binding.bottomNavigationView.setOnItemSelectedListener { item ->
                        when(item.itemId) {
                            R.id.search -> {
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
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()  // Вернуть на предыдущий экран
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_vacancy_menu, menu)
        return true
    }

    fun setToolbarVisibility(isVisible: Boolean) {
        if (isVisible) {
            binding.toolbar.visibility = View.VISIBLE
            binding.toolbar.title = ""
        } else {
            binding.toolbar.visibility = View.GONE
        }
    }
}