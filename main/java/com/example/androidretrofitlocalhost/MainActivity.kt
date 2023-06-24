package com.example.androidretrofitlocalhost

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidretrofitlocalhost.Model.User
import com.example.androidretrofitlocalhost.Retrofit.ApiClient
import com.example.androidretrofitlocalhost.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            var name=binding.etName.text.toString().trim()
            var email=binding.etEmail.text.toString().trim()
            var mobile=binding.etPassword.text.toString().trim()

            createAccount(name,email,mobile)
        }

    }

    private fun createAccount(name: String, email: String, mobile: String) {

        ApiClient.init().createAccount(1,name,email,mobile).enqueue(object :Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    var res=response.body()
                    res?.let {
                        Toast.makeText(applicationContext, "${it.message}", Toast.LENGTH_SHORT).show()

                        if (it.status=="success"){
                            //navigate home
                            //save user detail to sharedprefernce
                            // update login stuts
                            startActivity(Intent(applicationContext,HomeActivity::class.java))
                        }
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })



    }
}