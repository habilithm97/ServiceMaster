package com.example.servicemaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
    }
}