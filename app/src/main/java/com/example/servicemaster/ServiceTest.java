package com.example.servicemaster;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

/*
-바인딩(Binding) : 서비스가 서버 역할을 하면서 액티비티와 연결될 수 있도록 만듬
 */

public class ServiceTest extends Service {

    private static final String TAG = "ServiceTest";

    public ServiceTest() {}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { // 인텐트는 여기서 처리함

        if(intent == null) {
            return Service.START_STICKY; // 서비스가 강제 종료되었을 경우 시스템이 서비스의 인텐트 값을 null로 초기화해서 재시작시킴
        } else {
            processIntent(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processIntent(Intent intent) {
        String str = intent.getStringExtra("str");

        Log.d(TAG, "입력한 문자열 : " +  str);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
