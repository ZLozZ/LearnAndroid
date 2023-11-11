package com.example.bai_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText edta, edtb;
    int result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai7);
    }

    private void HandleSum(){
        edta=(EditText) findViewById(R.id.soa);
        int a= Integer.parseInt(edta.getText()+"");
        edtb=(EditText) findViewById(R.id.sob);
        int b = Integer.parseInt(edtb.getText()+"");
        result = a + b;
        TextView t_result = (TextView) findViewById(R.id.text_result);
        t_result.setText(result);
    }

}