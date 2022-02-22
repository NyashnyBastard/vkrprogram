package com.example.test1

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.test1.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    //actionBar
    private lateinit var actionBar: ActionBar
    //FirebaseAuth
    private  lateinit var firebaseAuth: FirebaseAuth
    private var email =""
    private var password =""
    //ProgressDialog
    private lateinit var progressDialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //configure actionbar
        actionBar = supportActionBar!!
        actionBar.title = "Добро пожаловать в сессию"
    //configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait...")
        progressDialog.setMessage("Logging in...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handle click, begin login
        binding.loginBtn.setOnClickListener{
        // before lodding in, validate data
            validateDate()
        }

    }

    private fun validateDate() {
        //get data
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()
        //validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //invalid email format
            binding.emailEt.error = "Invalid email format"
        }
            else if(TextUtils.isEmpty(password)){
                // no password entered
                binding.passwordEt.error = "Please enter password"
            }
            else {
                //data is validated, begin login
                firebaseLogin()
            }

    }

    private fun firebaseLogin() {
        //show progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //login success
                progressDialog.dismiss()
                //get uwer info
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "LoggedIn as $email", Toast.LENGTH_SHORT).show()
                //open menuActivity
                startActivity(Intent(this, MenuActivity::class.java))
                finish()
            }
            .addOnFailureListener { e->
                //login failed
                progressDialog.dismiss()
                Toast.makeText(this,"Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser(){
        //if user is already logged in do to menu
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser !=null){
            //user is logged in
            startActivity(Intent(this,MenuActivity::class.java))
        }
    }
}