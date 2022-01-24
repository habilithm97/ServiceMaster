package com.example.servicemaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
*서비스 : 백그라운드에서 실행되는 앱의 구성 요소임
-서비스가 비정상적으로 종료되더라도 실행되어 있는 상태를 계속 유지하기 위해 시스템이 자동으로 재실행함

-서비스를 시작시킬 때는 startService()를 호출하는데, 시스템은 이 메소드로 서비스를 시작시킨 후, 인텐트 객체를 파라미터로 서비스에 전달함
(여기서 인텐트 객체는 어떤 서비스를 실행할 것인지에 대한 정보가 담겨져 있음. 단, 이미 서비스가 이미 실행 중일 때는 인텐트를 전달하는 목적으로도 사용됨)

-서비스가 이미 메모리에 만들어져 있는 상태에서는, 시스템이 onCreate()가 아니라 서비스로 전달된 인텐트 객체를 처리하는 onStartCommand()를 실행함
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edt = (EditText)findViewById(R.id.edt);
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = edt.getText().toString();

                Intent intent = new Intent(getApplicationContext(), ServiceTest.class);
                intent.putExtra("str", str);

                startService(intent);
            }
        });

        Intent passedIntent = getIntent(); // 액티비티가 새로 만들어질 때 전달된 인텐트 처리
        processIntent(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) { // 액티비티가 이미 만들어져 있을 때 전달된 인텐트 처리
        processIntent(intent);
        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        if(intent != null) {
            String str = intent.getStringExtra("str");

            Toast.makeText(getApplicationContext(), "전달 받은 문자열 : " + str, Toast.LENGTH_SHORT).show();
        }
    }
}