package br.com.fielddiary.landing

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.fielddiary.R
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : AppCompatActivity() {

    private lateinit var viewModel: LandingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        viewModel = ViewModelProviders.of(this).get(LandingViewModel::class.java)

        viewModel.authUser.observe(this, Observer { authUser ->
            authUser?.let {
                signedIn()
            }
        })

        viewModel.authError.observe(this, Observer { authError ->
            authError?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        sign_in_btn.setOnClickListener {

            if (email_et.text.toString().isBlank()) {
                email_et.error = "invalid email"
                return@setOnClickListener
            }

            if (password_et.text.toString().isBlank()) {
                password_et.error = "invalid password"
                return@setOnClickListener
            }

            viewModel.signIn(email_et.text.toString(), password_et.text.toString())
        }


    }

    private fun signedIn() {

    }


}