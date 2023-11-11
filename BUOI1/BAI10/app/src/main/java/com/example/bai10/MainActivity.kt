package com.example.bai10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.lang.Math.pow

abstract class MainActivity : AppCompatActivity(){
//    var a: Double = 0.0
//    var b: Double = 0.0
//    var c: Int = 0
//    val tResult:TextView = findViewById(R.id.textResult)
//    val myClickListener = View.OnClickListener {View->
//        when(View.id){
//            R.id.so9->a = 0.0
////            R.id.so8->
////            R.id.so7->
////            R.id.so6->
////            R.id.so5->
////            R.id.so4->
////            R.id.so3->a
////            R.id.so2->a
////            R.id.so1->a
////            R.id.so0->a
//        }
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    public fun handleSo(a: Double, c:Int): Double{
//        var result:Double
//        result = a*pow(10.0, c.toDouble())
//        var resultP: Double = result
//        tResult.text = resultP.toInt().toString()
//        return result
//    }
}