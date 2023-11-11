package com.example.b14

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendInfor()
    }
    public fun sendInfor(){
        val fullName: EditText = findViewById(R.id.editFName)
        val cmnd: EditText = findViewById(R.id.editCMND)
        val tc: RadioButton = findViewById(R.id.radioTC)
        val cd:RadioButton = findViewById(R.id.radioCD)
        val dh:RadioButton = findViewById(R.id.radioDH)
        val db:CheckBox =findViewById(R.id.checkBoxDB)
        val ds:CheckBox = findViewById(R.id.checkBoxDS)
        val dc:CheckBox = findViewById(R.id.checkBoxCD)
        val inforAdd:EditText = findViewById(R.id.editAdd)
        val send:Button = findViewById(R.id.btnInfor)
        var bc: String = ""
        var st: String=""
        send.setOnClickListener(){
            var cout:Int = 0
            var name = fullName.text.toString()
            var namep = name.split(' ')
            var cccd:String = cmnd.text.toString()
            if(namep.size<3)
                Toast.makeText(this, "Họ và tên không được để trống và phải nhiều hơn 2 từ", Toast.LENGTH_SHORT).show()
            else if(cccd.isEmpty() || cccd.length < 9){
                Toast.makeText(this, "CMND phải có 9 chữ số", Toast.LENGTH_SHORT).show()
            }
            else{
                if(tc.isChecked){
                    bc = "Trung cấp"
                }else if(cd.isChecked){
                    bc = "Cao đẳng"
                }else{
                    bc = "Đại học"
                }

                if(db.isChecked){
                    st += "Đọc báo" + "\n"
                }
                if(ds.isChecked){
                    st += "Đọc sách" + "\n"
                }
                if(dc.isChecked){
                    st += "Đọc coding" + "\n"
                }
                var addInfor = inforAdd.text.toString()
                val builder = AlertDialog.Builder(this)
                val title = builder.setTitle("Thông tin cá nhân")
                builder.setMessage(name.toString()+"\n"+cccd+"\n"+bc+"\n"+st+"-------------------"+"\n"+"Thông tin bổ sung"+"\n"+addInfor)
                builder.apply {
                    setNegativeButton("Đóng",
                        DialogInterface.OnClickListener { dialog, id ->
                            dialog.dismiss()
                        })
                }
                builder.create().show()
            }
        }
    }
}