package com.dev.spdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_pass)
    EditText etPass;
    @Bind(R.id.cb)
    CheckBox cb;
    @Bind(R.id.btn)
    Button btn;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sp=getSharedPreferences("data",MODE_PRIVATE);//文件存储的名字和模式
        boolean rember = sp.getBoolean("rember", false);
        if(rember){
            readData();
            Toast.makeText(this,etName.getText().toString()+"----"+etPass.getText().toString(),Toast.LENGTH_SHORT).show();
        }

    }

    private void readData() {
        String name = sp.getString("name", "");
        String pass = sp.getString("pass", "");
        etPass.setText(pass);
        etName.setText(name);
        cb.setChecked(true);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        if(cb.isChecked()){
            //如果记住密码选中，就将数据存储到sp中
            String name = etName.getText().toString().trim();
            String pass = etPass.getText().toString().trim();
            if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pass)){

                SharedPreferences.Editor edit = sp.edit();
                edit.putString("name",name);
                edit.putString("pass",pass);//讲数据进行存储
                edit.putBoolean("rember",true);
                edit.commit();
                Toast.makeText(this,"记住了密码，请退出试一下",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"用户名密码不能为空",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "轻轻选中记住密码", Toast.LENGTH_SHORT).show();
        }
    }
}
