package com.ripigo.kalkulatorsederhana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.ripigo.kalkulatorsederhana.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var OPERATOR : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListener()
    }

    private fun calculator(value1:Int,value2: Int): String{
        var result : Int = 0
        when (OPERATOR){
            "+" -> {
                result = value1 + value2
            } "-" -> {
                result = value1 - value2
            } "x" -> {
            result = value1 * value2
           }
            ":" -> {
                result = value1 / value2
            }
        }

        return result.toString()
    }

    private fun validate():Boolean{
        if (binding.etValueOne.text.isEmpty() || binding.etValueTwo.text.isEmpty()){
            return false
        } else if (OPERATOR.isNullOrEmpty()){
            return false
        }

        return true
    }

    private fun setOnClickListener(){

        binding.btnCalculate.setOnClickListener() {
                if(validate()){
                    val value1: Int = binding.etValueOne.text.toString().toInt()
                    val value2: Int = binding.etValueTwo.text.toString().toInt()
                    binding.tvResult.text = calculator(value1, value2)
                } else {
                    showMessage("Isikan Formnya")
                }
        }

        binding.rgOperator.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = findViewById<RadioButton>(group.checkedRadioButtonId)

            OPERATOR = radioButton.text.toString()
            binding.tvResult.text ="Result"
        }


    }



    private fun showMessage(message:String){

        val contextView = binding.root

        Snackbar.make(contextView,message, Snackbar.LENGTH_SHORT)
            .show()
    }






}