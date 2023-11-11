package com.example.bai_101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var a: Double = 0.0
    var b: Double = 0.0
    var c: Int = 0
    var d: Char = ' '
    lateinit var tResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn9:Button = findViewById(R.id.so9)
        val btn8:Button = findViewById(R.id.so8)
        val btn7:Button = findViewById(R.id.so7)
        val btn6:Button = findViewById(R.id.so6)
        val btn5:Button = findViewById(R.id.so5)
        val btn4:Button = findViewById(R.id.so4)
        val btn3:Button = findViewById(R.id.so3)
        val btn2:Button = findViewById(R.id.so2)
        val btn1:Button = findViewById(R.id.so1)
        val btn0:Button = findViewById(R.id.so0)
        val btnSum:Button = findViewById(R.id.sum)
        val btnDiv:Button = findViewById(R.id.div)
        val btnMul:Button = findViewById(R.id.mul)
        val btnSub:Button = findViewById(R.id.sub)
        val btnB:Button = findViewById(R.id.dauB)
        val btnDlt:Button = findViewById(R.id.btnDelete)
        tResult= findViewById(R.id.textResult)
        btn9.setOnClickListener(myClickListener)
        btn8.setOnClickListener(myClickListener)
        btn7.setOnClickListener(myClickListener)
        btn6.setOnClickListener(myClickListener)
        btn5.setOnClickListener(myClickListener)
        btn3.setOnClickListener(myClickListener)
        btn2.setOnClickListener(myClickListener)
        btn1.setOnClickListener(myClickListener)
        btn0.setOnClickListener(myClickListener)
        btnSum.setOnClickListener(myClickListener)
        btnDiv.setOnClickListener(myClickListener)
        btnMul.setOnClickListener(myClickListener)
        btnSub.setOnClickListener(myClickListener)
        btnB.setOnClickListener(myClickListener)
        btnDlt.setOnClickListener(myClickListener)
    }

    val myClickListener = View.OnClickListener { View->
        when(View.id) {
            R.id.so9 -> {
                handleSo(9, c, d)
            }
            R.id.so8->{
                handleSo(8, c, d)
            }
            R.id.so7->{
                handleSo(7, c, d)
            }
            R.id.so6-> {
                handleSo(6, c, d)
            }
            R.id.so5->{
                handleSo(5, c, d)
            }
            R.id.so4->{
                handleSo(4, c, d)
            }
            R.id.so3->{
                handleSo(3, c, d)
            }
            R.id.so2->{
                handleSo(2, c, d)
            }
            R.id.so1->{
                handleSo(1, c, d)
            }
            R.id.so0->{
                handleSo(0, c, d)
            }
            R.id.div->{
                handleDau('÷')
                c++
            }
            R.id.mul->{
                handleDau('x')
                c++
            }
            R.id.sub->{
                handleDau('-')
                c++
            }
            R.id.sum->{
                handleDau('+')
                c++
            }
            R.id.dauB->{
                handleBang()
                c=0
            }
            R.id.btnDelete->{
                tResult.text = ""
                c=0
            }
        }
    }

    public fun handleSo(d:Int, c:Int, e:Char){
        if(c == 1) {
            b = d.toDouble()
            tResult.text = a.toInt().toString() + e + b.toInt().toString()
        }else {
            a = d.toDouble()
            tResult.text = a.toInt().toString()

        }
    }

    public fun handleDau(e:Char){
        d = e
        tResult.text = a.toInt().toString() + e
    }

    public fun handleBang(){
        var Kq: Int = 0
        if(d == '+'){
            Kq = a.toInt() + b.toInt()
        }
        if(d == '÷'){
            Kq = a.toInt() / b.toInt()
        }
        if(d == 'x'){
            Kq = a.toInt() * b.toInt()
        }
        if(d == '-'){
            Kq = a.toInt() - b.toInt()
        }
        tResult.text = a.toInt().toString() + d + b.toInt().toString() + '=' +Kq.toString()
    }
}