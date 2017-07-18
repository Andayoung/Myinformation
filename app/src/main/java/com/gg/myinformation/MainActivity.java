package com.gg.myinformation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.user_name_uu)
    TextView userNameUu;
    @BindView(R.id.user_pwd_uu)
    TextView userPwdUu;
    @BindView(R.id.user_bir_uu)
    TextView userBirUu;
    @BindView(R.id.user_sex_uu)
    TextView userSexUu;
    @BindView(R.id.btn_exit)
    Button btnExit;
    private SerialNumberHelper serialNumberHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (serialNumberHelper == null) {
            serialNumberHelper = new SerialNumberHelper(getApplicationContext());
        }
        String serialNumber=serialNumberHelper.read4File();
        Log.e("MainActivity","onResume s="+serialNumber);
        if (serialNumber==null|| serialNumber.equals("")) {
            Intent intent = new Intent(MainActivity.this, LogOrRegActivity.class);
            startActivity(intent);
        } else {
            String[] s = serialNumber.split(" ");
            userNameUu.setText(s[2]);
            userPwdUu.setText(s[3]);
            userBirUu.setText("1998.08.08");
            userSexUu.setText("女");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();


    }



    @OnClick({R.id.btn_exit})
    void exit(Button btn) {
        if (serialNumberHelper == null) {
            serialNumberHelper = new SerialNumberHelper(getApplicationContext());
        }
        serialNumberHelper.deleteFile();
        finish();

    }

    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("还没有登录？");
        builder.setTitle("提示");
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MainActivity.this.finish();
            }
        });
        builder.setNegativeButton("去登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
