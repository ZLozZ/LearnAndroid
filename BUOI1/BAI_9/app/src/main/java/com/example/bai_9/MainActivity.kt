package com.example.bai_9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var editC: EditText;
    lateinit var editF: EditText;
    lateinit var toF: Button;
    lateinit var toC: Button;
    lateinit var btnClr: Button;
    lateinit var textF: EditText;
    lateinit var textC: EditText;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleF()
        handleC()
        handleClear()
    }

    public fun handleF(){
        toC = findViewById(R.id.btnCel);
        textF = findViewById(R.id.txFar);
        textC = findViewById(R.id.txCel);
        toC.setOnClickListener{
            var f: Double= textC.text.toString().toDouble();
            var result: Double = (((f*9)/5)+32).toDouble()
            textF.setText(result.toString())
        }
    }

    public fun handleC(){
        toF = findViewById(R.id.btnFar);
        textF = findViewById(R.id.txFar);
        textC = findViewById(R.id.txCel);
        toF.setOnClickListener{
            var c :Double = textF.text.toString().toDouble();
            var result: Double = (((c-32)*5)/9).toDouble()
            textC.setText(result.toString())
        }
    }

    public fun handleClear(){
        btnClr = findViewById(R.id.btnClear);
        textF = findViewById(R.id.txFar);
        textC = findViewById(R.id.txCel);
        btnClr.setOnClickListener{
            textC.setText("")
            textF.setText("")
        }
    }
}