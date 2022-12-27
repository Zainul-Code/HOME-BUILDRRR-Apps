package com.zainul.buildrrr

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.zainul.buildrrr.mainhome.Home
import kotlinx.android.synthetic.main.activity_verify_otp.*
import java.util.concurrent.TimeUnit


class VerifyOTP : AppCompatActivity() {
    var et1: EditText? = null
    var et2: EditText? = null
    var et3: EditText? = null
    var et4: EditText? = null
    var et5: EditText? = null
    var et6: EditText? = null
    var btnsubmit: Button? = null
    var getbackendotp: String? = null
    var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)
        et1 = findViewById(R.id.inputotp1)
        et2 = findViewById(R.id.inputotp2)
        et3 = findViewById(R.id.inputotp3)
        et4 = findViewById(R.id.inputotp4)
        et5 = findViewById(R.id.inputotp5)
        et6 = findViewById(R.id.inputotp6)
        progressBar = findViewById(R.id.probar2)

        //get mobile number from mainActivty to this
        val textView = findViewById<TextView>(R.id.txtmobileno)
        textView.text = String.format(
            "+62-%S", intent.getStringExtra("mobile")
        )
        getbackendotp = intent.getStringExtra("backendotp")
        btnsubmit?.setOnClickListener(View.OnClickListener {
            if (!inputotp1.getText().toString().trim { it <= ' ' }.isEmpty() && !inputotp2.getText().toString()
                    .trim { it <= ' ' }
                    .isEmpty()
                && !inputotp3.getText().toString().trim { it <= ' ' }.isEmpty()
                && !inputotp4.getText().toString().trim { it <= ' ' }.isEmpty()
                && !inputotp5.getText().toString().trim { it <= ' ' }.isEmpty()
                && !inputotp6.getText().toString().trim { it <= ' ' }.isEmpty()
            ) {

                // marging user's input in a string
                val getuserotp = inputotp1.getText().toString() +
                        inputotp2.getText().toString() +
                        inputotp3.getText().toString() +
                        inputotp4.getText().toString() +
                        inputotp5.getText().toString() +
                        inputotp6.getText().toString()
                if (getbackendotp != null) {
                    probar2.setVisibility(View.VISIBLE)
                    btnsubmit?.setVisibility(View.INVISIBLE)
                    val phoneAuthCredential = PhoneAuthProvider.getCredential(
                        getbackendotp!!, getuserotp
                    )
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener { task ->
                            probar2.setVisibility(View.GONE)
                            btnsubmit?.setVisibility(View.VISIBLE)
                            if (task.isSuccessful) {
                                val intent = Intent(applicationContext, Home::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@VerifyOTP,
                                    "Enter corrent OTP",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(this@VerifyOTP, "Please check internet", Toast.LENGTH_SHORT)
                        .show()
                }

                //Toast.makeText(MainActivity2.this, "OTP Verify", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this@VerifyOTP, "Please fill all number", Toast.LENGTH_SHORT)
                    .show()
            }


            // movenumtonext();
        })
        findViewById<View>(R.id.sendotp_again).setOnClickListener {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+92" + intent.getStringExtra("mobile"),
                60,
                TimeUnit.SECONDS,
                this@VerifyOTP,
                object : OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {}
                    override fun onVerificationFailed(e: FirebaseException) {
                        Toast.makeText(this@VerifyOTP, e.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onCodeSent(
                        newbackendotp: String,
                        forceResendingToken: ForceResendingToken
                    ) {
                        getbackendotp = newbackendotp
                        Toast.makeText(
                            this@VerifyOTP,
                            "OTP Send Sucessfuly",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }
        movenumtonext() //move num to next
    }

    private fun movenumtonext() {
        et1!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (!charSequence.toString().trim { it <= ' ' }.isEmpty()) {
                    et2!!.requestFocus()
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        et2!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (!charSequence.toString().trim { it <= ' ' }.isEmpty()) {
                    et3!!.requestFocus()
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        et3!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (!charSequence.toString().trim { it <= ' ' }.isEmpty()) {
                    et4!!.requestFocus()
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        et4!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (!charSequence.toString().trim { it <= ' ' }.isEmpty()) {
                    et5!!.requestFocus()
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        et5!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (!charSequence.toString().trim { it <= ' ' }.isEmpty()) {
                    et6!!.requestFocus()
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }
}
