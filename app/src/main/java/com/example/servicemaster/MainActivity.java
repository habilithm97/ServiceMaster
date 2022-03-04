package com.example.servicemaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
*서비스 : 백그라운드에서 실행되는 앱의 구성 요소
-서비스가 비정상적으로 종료되더라도 실행되어 있는 상태를 계속 유지하기 위해 시스템이 자동으로 재실행함

*시작 타입 서비스
-서비스를 시작시킬 때는 startService()를 호출하는데, 시스템은 이 메소드로 서비스를 시작시킨 후, 인텐트 객체를 파라미터로 서비스에 전달함
(여기서 인텐트 객체는 어떤 서비스를 실행할 것인지에 대한 정보가 담겨져 있음. 단, 이미 서비스가 이미 실행 중일 때는 인텐트를 전달하는 목적으로도 사용됨)

-서비스가 이미 메모리에 만들어져 있는 상태에서는, 시스템이 onCreate()가 아니라 서비스로 전달된 인텐트 객체를 처리하는 onStartCommand()를 실행함

*연결 타입 서비스
-클라이언트와 서버에서 서버의 역할을 할 수 있음
-컴포넌트들은 서비스에 연결해서 요청을 보내고, 서비스는 요청에 대한 결과를 컴포넌트로 전송함.
-프로세스 간에 통신이 가능함
-컴포넌트와 연결이 되어 있는 동안에만 존재 가능하기 때문에 무한 실행되지 않음
-앱 안의 기능을 외부에 제공할 때 많이 사용함
 */

public class MainActivity extends AppCompatActivity {

    ServiceTest serviceTest; // 서비스를 하나의 객체로 생성
    boolean bound = true; // 연결 상태

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bound) {
                    int num = serviceTest.getRandomNumber();
                    Toast.makeText(getApplicationContext(), "난수 : " + num, Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    @Override
    protected void onStart() { // 화면이 생성될 때 호출, 액티비티가 시작되면 서비스에 연결함
        super.onStart();

        Intent intent = new Intent(this, ServiceTest.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE); // (옵션)첫 번째 바인드면 자동 생성
    }

    @Override
    protected void onStop() { // 액티비티가 종료되면 서비스 연결을 해제함
        super.onStop();

        if(bound) { // 연결되어 있으면
            unbindService(connection);
            bound = false; // 연결 끊음
        }
    }

    private ServiceConnection connection = new ServiceConnection() { // 서비스 연결 객체
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ServiceTest.BinderTest binder = (ServiceTest.BinderTest)iBinder;
            serviceTest = binder.getService();
            bound = true; // 연결됨
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bound = false; // 연결 끊음
        }
    };
}





























