package com.atm.atm;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText edUserid;
    private EditText edPasswd;
    private CheckBox cbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSharedPreferences("atm", MODE_PRIVATE)
                .edit()
                .putInt("LEVEL", 3)
                .putString("NAME", "Tom")
                .commit();

        int level = getSharedPreferences("atm", MODE_PRIVATE)
                .getInt("LEVEL", 0);
        Log.d(TAG, "onCreate: " + level);

        findViews();

        String userid = getSharedPreferences("atm", MODE_PRIVATE)
                .getString("USERID", "");
        edUserid.setText(userid);
    }

    private void findViews() {

        edUserid = findViewById(R.id.edt_userid);
        edPasswd = findViewById(R.id.edt_passwd);
        cbRemember = findViewById(R.id.cb_rem_userid);

        cbRemember.setChecked(getSharedPreferences("atm", MODE_PRIVATE)
                .getBoolean("REMEMBER_USERID", false));

        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                getSharedPreferences("atm", MODE_PRIVATE)
                        .edit()
                        .putBoolean("REMEMBER_USERID", b)
                        .apply();
            }
        });

    }

    public void login(View view){
        final String userid = edUserid.getText().toString();
        final String passwd = edPasswd.getText().toString();

        FirebaseDatabase.getInstance().getReference("users")
                .child(userid)
                .child("password")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String pw = (String) dataSnapshot.getValue();

                        if (pw.equals(passwd)){
                            boolean remember = getSharedPreferences("atm", MODE_PRIVATE)
                                    .getBoolean("REMEMBER_USERID", false);

                            if (remember){
                                //save userid
                                getSharedPreferences("atm", MODE_PRIVATE)
                                        .edit()
                                        .putString("USERID", userid)
                                        .apply();
                            }

                            setResult(RESULT_OK);
                            finish();
                        } else {
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("登入結果")
                                    .setMessage("登入失敗")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

       /* if("jack".equals(userid) && "1234".equals(passwd)){

        }*/
    }

    public void quit(View view){

    }
}
