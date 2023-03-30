package com.lordkajoc.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.lordkajoc.recyclerview.databinding.ActivityMainBinding


//Penerapan Single Activity
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Tampilan Pertama berupa List dengan bentuk Linear Vertical
        val fragmentListLinear = FragmentListLinearLayout()
        setCurrentFragment(fragmentListLinear)
    }

    //FragmentTransaction
    private fun setCurrentFragment(fragment: Fragment): FragmentTransaction =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fr_container, fragment)
            commit()
        }
}
