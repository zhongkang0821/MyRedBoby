package com.hhzmy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login_btn,register_bt;
    private EditText edit_account,edit_password;
    private ImageView login_back,vip_login,delete_edit1,delete_edit2
            ,display_pass,hidden_pass;
    private TextView forgetpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        display_pass= (ImageView) findViewById(R.id.display_pass);
        hidden_pass= (ImageView) findViewById(R.id.hidden_pass);
        login_back = (ImageView) findViewById(R.id.login_back);
        vip_login = (ImageView) findViewById(R.id.vip_login);
        login_btn = (Button) findViewById(R.id.login_btn);
        register_bt = (Button) findViewById(R.id.register_bt);
        edit_account = (EditText) findViewById(R.id.edit_account);
        edit_password = (EditText) findViewById(R.id.edit_password);
        forgetpass = (TextView) findViewById(R.id.forget_pass);
        delete_edit1 = (ImageView) findViewById(R.id.delete_edit1);
        delete_edit2 = (ImageView) findViewById(R.id.delete_edit2);

        login_back.setOnClickListener(this);
        vip_login.setOnClickListener(this);
        login_btn.setOnClickListener(this);
        register_bt.setOnClickListener(this);
        forgetpass.setOnClickListener(this);
        delete_edit1.setOnClickListener(this);
        delete_edit2.setOnClickListener(this);
        forgetpass.setOnClickListener(this);
        display_pass.setOnClickListener(this);
        hidden_pass.setOnClickListener(this);

        edit_password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});

        edit_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() == 0){
                    return;
                }
                StringBuilder sb=new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    if (i != 3 && i != 8 && s.charAt(i) == ' '){
                        continue;
                    }else{
                        sb.append(s.charAt(i));
                        if ((sb.length() == 4 || sb.length() == 9)
                                && sb.charAt(sb.length() - 1) != ' '){
                            sb.insert(sb.length() - 1, ' ');
                        }
                    }
                }
                if (!sb.toString().equals(s.toString())){
                    int index = start + 1;
                    if (sb.charAt(start) == ' '){
                        if (before == 0){
                            index++;
                        }else {
                            index--;
                        }
                    }else{
                        if (before == 1){
                            index--;
                        }
                    }
                    edit_account.setText(sb.toString());
                    edit_account.setSelection(index);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0){
                    login_btn.setBackgroundColor(getResources().getColor(R.color.login_bt_back1));
                    login_btn.setTextColor(getResources().getColor(R.color.login_bt_text1));
                    delete_edit1.setVisibility(View.INVISIBLE);
                }else{
                    delete_edit1.setVisibility(View.VISIBLE);
                    String ps=edit_password.getText().toString();
                    int length=ps.length();
                    if (length > 0){
                        login_btn.setBackgroundColor(getResources().getColor(R.color.login_register));
                        login_btn.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });

        edit_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0){
                    login_btn.setBackgroundColor(getResources().getColor(R.color.login_bt_back1));
                    delete_edit2.setVisibility(View.INVISIBLE);
                    login_btn.setTextColor(getResources().getColor(R.color.login_bt_text1));
                }else{
                    delete_edit2.setVisibility(View.VISIBLE);
                    String pas=edit_account.getText().toString();
                    int l=pas.length();
                    if (l>0){
                        login_btn.setBackgroundColor(getResources().getColor(R.color.login_register));
                        login_btn.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_back:
                finish();
                break;
            case R.id.login_btn:
                String pass=edit_password.getText().toString();
                String mobile_phone=edit_account.getText().toString().trim();
                if (TextUtils.isEmpty(pass) || TextUtils.isEmpty(mobile_phone)){
                    Toast.makeText(LoginActivity.this, "账号和密码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    int pas=pass.length();
                    if (pas<6){
                        Toast.makeText(LoginActivity.this, "请输入6-20位易购密码", Toast.LENGTH_SHORT).show();
                    }else{
                        String phone=mobile_phone.replace(" ","");
                        boolean flag=isMobileNo(phone);
                        if (flag==false){
                            Toast.makeText(LoginActivity.this, "对不起，该号码不合格", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this, mobile_phone+"可以使用", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case R.id.register_bt:
                Intent m=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(m);
                break;
            case R.id.delete_edit1:
                edit_account.setText("");
                break;
            case R.id.delete_edit2:
                edit_password.setText("");
                break;
            case R.id.forget_pass:
                Toast.makeText(LoginActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.vip_login:
                Toast.makeText(LoginActivity.this, "第三方登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.display_pass:
                hidden_pass.setVisibility(View.VISIBLE);
                display_pass.setVisibility(View.INVISIBLE);
                edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                guangbiao();
                break;
            case R.id.hidden_pass:
                hidden_pass.setVisibility(View.INVISIBLE);
                display_pass.setVisibility(View.VISIBLE);
                edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                guangbiao();
                break;
        }
    }

    private boolean isMobileNo(String pphone) {

        String telRegex = "[1][3578]\\d{9}";
        if (TextUtils.isEmpty(pphone)){
            return false;
        }else{
            return pphone.matches(telRegex);
        }
    }

    private void guangbiao(){
        CharSequence charSequence = edit_password.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }
}
