package br.com.fielddiary.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.fielddiary.R

class HomeActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}