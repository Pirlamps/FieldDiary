package br.com.fielddiary.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.fielddiary.R
import br.com.fielddiary.model.Growth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }

    lateinit var homeAdapter: HomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val model = ViewModelProviders.of(this).get(HomeViewModel::class.java)


        homeAdapter = HomeAdapter()
        val linearLayoutManager = LinearLayoutManager(this)

        home_rv.apply {
            adapter = homeAdapter
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        }


        model.getGrowths().observe(this, Observer {
            growths -> homeAdapter.setData(growths)
        })

        model.getError().observe(this, Observer { error ->
            error?.let {
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

        add_fab.setOnClickListener {
            model.addGrowth(Growth(
                    title = "tomate",
                    desc = "plantação 1"))
        }

    }
}