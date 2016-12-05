package com.hhzmy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text;
    private EditText edit_mobile;
    private Button register_next;
    private ImageView delete_edit3,register_back;
    private CheckBox checkBo;
    private boolean falg=true;
    private Switch ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        text = (TextView) findViewById(R.id.clause_text);
        edit_mobile = (EditText) findViewById(R.id.edit_mobile);
        register_next= (Button) findViewById(R.id.register_next);
        delete_edit3 = (ImageView) findViewById(R.id.delete_edit3);
        register_back= (ImageView) findViewById(R.id.register_back);
        checkBo = (CheckBox) findViewById(R.id.checkBo);
        checkBo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                falg=isChecked;
                if(falg==false){
                    Toast.makeText(RegisterActivity.this, "您必须同意服务条款才可以注册", Toast.LENGTH_SHORT).show();
                }
            }
        });
        edit_mobile.setOnClickListener(this);
        register_next.setOnClickListener(this);
        register_back.setOnClickListener(this);
        

        String s=text.getText().toString();
        edit_mobile.addTextChangedListener(new TextWatcher() {
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
                    edit_mobile.setText(sb.toString());
                    edit_mobile.setSelection(index);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==11){
                    register_next.setBackgroundColor(getResources().getColor(R.color.login_register));
                    register_next.setTextColor(getResources().getColor(R.color.white));
                    delete_edit3.setVisibility(View.VISIBLE);
                }else if(s.length()!=0){
                    delete_edit3.setVisibility(View.INVISIBLE);
                }else{
                    register_next.setBackgroundColor(getResources().getColor(R.color.login_bt_text1));
                    register_next.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        setText(s);

    }

    public void setText(String ss) {
        SpannableString span=new SpannableString(ss);
        Toast.makeText(RegisterActivity.this, ss, Toast.LENGTH_SHORT).show();
        span.setSpan(new URLSpan("https://pay.suning.com/epp-paycore/pay/static/quickPayRelatedServiceContract.htm"),2,8,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.login_register)),2,8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new URLSpan("http://res.m.suning.com/project/newuser_redpacket/member_agreement.html"),11,16,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.login_register)),11,16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setMovementMethod(new LinkMovementMethod());
        text.setText(span);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_mobile:
                edit_mobile.setText("");
                break;
            case R.id.register_next:
                if(TextUtils.isEmpty(edit_mobile.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "号码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    String mobile_phone=edit_mobile.getText().toString().trim();
                    String phone=mobile_phone.replace(" ","");
                    boolean ff=isMobileNo(phone);
                    if (ff==false){
                        Toast.makeText(RegisterActivity.this, "您所输入的号码不正确", Toast.LENGTH_SHORT).show();
                    }else{
                        if (falg==true){
                            Toast.makeText(RegisterActivity.this, "下一步", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterActivity.this, "您必须同意服务条款才可以注册", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case R.id.register_back:
                finish();
                break;
        }
    }
    private boolean isMobileNo(String ile_phone) {
        String telRegex = "[1][3578]\\d{9}";
        if (TextUtils.isEmpty(ile_phone)){
            return false;
        }else{
            return ile_phone.matches(telRegex);
        }
    }
}
