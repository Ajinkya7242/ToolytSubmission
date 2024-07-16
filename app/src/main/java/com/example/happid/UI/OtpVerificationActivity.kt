package com.example.happid.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.happid.MainActivity
import com.example.happid.R
import com.example.happid.databinding.ActivityOtpVerificationBinding

class OtpVerificationActivity : AppCompatActivity() {

    lateinit var binding:ActivityOtpVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityOtpVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val intent=intent
        val mobileNumber=intent.getStringExtra("mobile")
        val otp=intent.getStringExtra("otp")
        binding.tvMobileNumber.append(" $mobileNumber")


        binding.tvMobileNumber.setOnClickListener{
            startActivity(Intent(this,LoginScreenActivity::class.java))
            finish()
        }
        binding.imgBackArrow.setOnClickListener{
            onBackPressed()
        }

        binding.btnSubmit.setOnClickListener{
            if(otp.equals(binding.pinView.text.toString())){
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            else{
                Toast.makeText(this,"Please Enter Valid OTP", Toast.LENGTH_LONG).show()
            }
        }



    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,LoginScreenActivity::class.java))
        finish()
    }
}