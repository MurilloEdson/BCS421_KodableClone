package edu.farmingdale.kodable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class Signup : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fstore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth = FirebaseAuth.getInstance()
        fstore = FirebaseFirestore.getInstance()
        val login = findViewById<Button>(R.id.login)
        val signup = findViewById<Button>(R.id.signup)
        val inputName = findViewById<EditText>(R.id.name)
        val inputEmail = findViewById<EditText>(R.id.email)
        val inputPass1 = findViewById<EditText>(R.id.pass1)
        val inputPass2 = findViewById<EditText>(R.id.pass2)
        val securityLvl = findViewById<RadioGroup>(R.id.securityLevel)
        var bool = findViewById<RadioButton>(R.id.parent).isActivated

        signup.setOnClickListener {
            val name = inputName.text.toString()
            val email = inputEmail.text.toString()
            val pass1 = inputPass1.text.toString()
            val pass2 = inputPass2.text.toString()
            val radBtnId = securityLvl.checkedRadioButtonId
            val radBtn = findViewById<RadioButton>(radBtnId)
            val secLvl = radBtn.tag

            if (name.isNotEmpty() && email.isNotEmpty() && pass1.isNotEmpty() && pass2.isNotEmpty()) {
                if(pass1 == pass2){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass1).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val curr = firebaseAuth.currentUser
                            if(secLvl == "PARENT"){
                                bool = true
                            }
                            val newUser = hashMapOf(
                                "Email" to email,
                                "Name" to name,
                                "Parent" to bool
                            )
                            curr?.let { it1 -> fstore.collection("Users").document(it1.uid).set(newUser) }
                            startActivity(Intent(this,LevelSelect::class.java))
                            Toast.makeText(this, "Your Account Was Created Successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            val mess = it.exception?.message.toString()
                            Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Passwords Must Match !!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
        login.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }
    }
}