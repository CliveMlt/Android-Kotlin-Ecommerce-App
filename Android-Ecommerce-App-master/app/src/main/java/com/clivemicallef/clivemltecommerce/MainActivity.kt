package com.clivemicallef.clivemltecommerce

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, MainFragment())
                .commit()

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionHome -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, MainFragment())
                            .commit()
                }

                R.id.actionAboutus -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, AboutusFragment())
                            .commit()
                }

                R.id.actionTokens -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, TokensFragment())
                            .commit()
                }

                R.id.actionCart -> {
                    val intent = Intent(this.applicationContext, CartActivity::class.java)
                    startActivity(intent)
                }

            }
            it.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)






        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }
}
