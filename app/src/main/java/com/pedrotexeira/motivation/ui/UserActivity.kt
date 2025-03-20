package com.pedrotexeira.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pedrotexeira.motivation.infrastructure.MotivationConstants
import com.pedrotexeira.motivation.R
import com.pedrotexeira.motivation.infrastructure.SecurityPreferences
import com.pedrotexeira.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonSave.setOnClickListener(this)

        verifyUserName()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            handlesave()
        }
    }

    private fun verifyUserName(){
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)

        if(name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Irá destruir a UserActivity e tirar de memória
        }
    }

    private fun handlesave() {

        val name = binding.editYourName.text.toString() // Pegar a informação
        if (name != "") {

            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Irá destruir a UserActivity e tirar de memória
        } else {
            Toast.makeText(this, "Insira um nome!", Toast.LENGTH_SHORT).show()
        }
    }



}