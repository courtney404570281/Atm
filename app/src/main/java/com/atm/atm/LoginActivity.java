package com.atm.atm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText edUserid;
    private EditText edPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();

    }

    private void findViews() {

        edUserid = findViewById(R.id.edt_userid);
        edPasswd = findViewById(R.id.edt_passwd);

    }

    public void login(View view){
        String userid = edUserid.getText().toString();
        String passwd = edPasswd.getText().toString();

        if("jack".equals(userid) && "1234".equals(passwd)){
            setResult(RESULT_OK);
            finish();
        }
    }

    public void quit(View view){

    }
}
