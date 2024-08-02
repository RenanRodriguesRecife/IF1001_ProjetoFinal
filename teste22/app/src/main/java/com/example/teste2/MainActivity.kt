package com.example.teste2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.teste2.databinding.ActivityMainBinding
import com.example.teste2.home_fragment.ExploreFragment
import com.example.teste2.home_fragment.HomeFragment
import com.example.teste2.home_fragment.MapFragment
import com.example.teste2.notification_feature.Notification
import com.example.teste2.profile_feature.UserProfile

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

//      Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar);
//------------------------

        //Mudança de fragments
        replaceFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.explore -> replaceFragment(ExploreFragment())
                R.id.map -> replaceFragment(MapFragment())
                else->{
                }
            }
            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = getMenuInflater() as MenuInflater
        inflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.notification ->{
                val intent = Intent(this, Notification::class.java);
                startActivity(intent);
                true
            }
            R.id.user ->{
                val intent = Intent(this,UserProfile::class.java);
                startActivity(intent);
                true
            }
//            R.id.api ->{
//                val intent = Intent(this,ApiRequest::class.java);
//                startActivity(intent);
//                true
//            }
//            R.id.dm_internal ->{
//                val intent = Intent(this,DMinternal::class.java);
//                startActivity(intent);
//                true
//            }
//            R.id.dm_internal_cache ->{
//                val intent = Intent(this,DMinternalCache::class.java);
//                startActivity(intent);
//                true
//            }
//            R.id.dm_external->{
//                val intent = Intent(this,DMExternal::class.java);
//                startActivity(intent);
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }


    }


    //Mudança de fragments
    private fun replaceFragment(fragment: Fragment){
        val fragmentManeger = supportFragmentManager
        val fragmentTransaction = fragmentManeger.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
    //-------------------------

}