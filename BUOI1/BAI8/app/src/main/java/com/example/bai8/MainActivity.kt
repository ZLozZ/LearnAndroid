package com.example.bai8

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var editA: EditText
    lateinit var editB: EditText
    lateinit var btnSum: Button
    lateinit var btnSub: Button
    lateinit var btnMul: Button
    lateinit var btnDiv: Button
    lateinit var btnExit: Button
    lateinit var btnGCD: Button
    lateinit var textResult: TextView
    val myClickListener = View.OnClickListener {
        handelDiv()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bai8)
        editA = findViewById(R.id.soa)
        editB = findViewById(R.id.sob)
        textResult = findViewById(R.id.text_result)
        handelSub()
        //Activity is listener
        btnMul = findViewById(R.id.mul)
        btnMul .setOnClickListener(this)
        btnDiv = findViewById(R.id.div)
        btnDiv.setOnClickListener(myClickListener)
        btnGCD =findViewById(R.id.UCLN)
        val gdcClickListener = GCD(editA, editB, textResult)
        btnGCD.setOnClickListener(gdcClickListener);
    }

    //Onclick In XML
    fun handelSum(view: View) {
        val soA = editA.text.toString().toInt()
        val soB = editB.text.toString().toInt()
        val resultSum: Int = soA + soB
        textResult.text = resultSum.toString()
    }

    //Inline anonymous listener
    public fun handelSub(){
        btnSub = findViewById(R.id.sub)
        btnSub.setOnClickListener{
            val soA = editA.text.toString().toInt()
            val soB = editB.text.toString().toInt()
            val resultSub: Int = soA - soB
            textResult.text = resultSub.toString()
        }
    }
    override fun onClick(v: View){
        btnMul.setOnClickListener(){
            val soA = editA.text.toString().toInt()
            val soB = editB.text.toString().toInt()
            val resultMul: Int = soA*soB
            textResult.text = resultMul.toString()
        }
    }

    public fun handelDiv(){
        btnDiv.setOnClickListener(){
            val soA = editA.text.toString().toInt()
            val soB = editB.text.toString().toInt()
            val resultDiv: Double = (soA/soB).toDouble()
            textResult.text = resultDiv.toString()
        }
    }

    class GCD(
        private val edtA: EditText,
        private val edtB: EditText,
        private val tvResult: TextView
    ) : View.OnClickListener {
        override fun onClick(v: View?) {
            val numA = edtA.text.toString().toInt()
            val numB = edtB.text.toString().toInt()
            val result = findGCD(numA, numB)
            tvResult.text = result.toString()
        }

        private fun findGCD(a: Int, b: Int): Int {
            var x = a
            var y = b
            while (y != 0) {
                val temp = y
                y = x % y
                x = temp
            }
            return x
        }
    }

    class ExitButton : AppCompatButton {
        constructor(context: Context) : super(context) {
            init()
        }

        constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
            init()
        }

        constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
            init()
        }

        private fun init() {
            setOnClickListener {
                val activity = context as AppCompatActivity
                activity.finish()
            }
        }
    }

}