package com.example.happid.UI

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.chaos.view.PinView
import com.example.happid.MainActivity
import com.example.happid.R
import com.example.happid.databinding.ActivityLoginScreenBinding
import com.example.happid.viewModels.LoginScreenViewModel


class LoginScreenActivity : AppCompatActivity() {
    val Tag="LoginScreenActivity"
    private val viewModel: LoginScreenViewModel by viewModels()
    lateinit var binding: ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.etMobileNo.addTextChangedListener(viewModel.textWatcher)
        viewModel.formattedText.observe(this) { formattedText ->
            if (binding.etMobileNo.text.toString() != formattedText) {
                binding.etMobileNo.setText(formattedText)
                binding.etMobileNo.setSelection(formattedText.length)
            }
        }

        binding.btnRequestOtp.setOnClickListener {
            Log.d(Tag,binding.etMobileNo.text.toString().trim())
            val stringWithoutSpaces = binding.etMobileNo.text.toString().replace("\\s".toRegex(), "")
            val firstTwo = stringWithoutSpaces.take(2)
            val lastTwo = stringWithoutSpaces.takeLast(2)
            val otp = "$firstTwo$lastTwo"
            showCustomOtpDialog(otp)
        }


        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }

    }


    private fun showCustomOtpDialog(mobileNumber: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_otp, null)
        val editTextOtp = dialogView.findViewById<PinView>(R.id.pinView)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("Verify") { dialog, which ->
                val enteredOtp = editTextOtp.text.toString()
                if(enteredOtp.equals(mobileNumber)){
                    val i =Intent(this,OtpVerificationActivity::class.java)
                    i.putExtra("mobile",binding.etMobileNo.text.toString())
                    i.putExtra("otp",mobileNumber)
                    startActivity(i)
                    finish()
                }
                else{
                    Toast.makeText(this,"Please Enter Valid OTP",Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,IntroAppActivity::class.java))
        finish()
    }
}