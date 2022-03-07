package com.example.servicemaster;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*
*메신저 클래스
-서비스가 외부의 프로세스와 통신을 해야할 경우에는 원칙적으로 AIDL(안드로이드 인터페이스 정의 언어)을
이용해서 인터페이스를 설계해야 하지만 메신저 클래스를 사용할 것을 권장함
-메신저를 사용한다면 대부분의 경우 AIDL을 이용할 필요가 없이 프로세스 간 통신 기능을 구현할 수 있음
*/

public class MessengerService extends Service {

    static final int MSG_TEST = 1; // 서비스에 대한 명령어

    // 서비스가 각 클라이언트 호출의 콜백을 받는 핸들러를 구현해야함 -> 다른 앱에서 전달되는 메시지를 화면에 출력함
    class MessengerHandler extends Handler { // 클라이언트로부터의 메시지 처리기
        @Override
        public void handleMessage(@NonNull Message msg) { // 서비스가 수신되는 메시지 객체를 받고
            switch (msg.what) { // what 으로 무엇을 할지 결정함
                case MSG_TEST:
                    Toast.makeText(getApplicationContext(), "메신저 서비스 테스트입니다. .", Toast.LENGTH_SHORT).show();
                    break;
                    default:
                        super.handleMessage(msg);
            }
        }
    }

    // 클라이언트가 메시지를 보내는 도구
    final Messenger messenger = new Messenger(new MessengerHandler()); // 핸들러를 참조해서 메신저 객체 생성

    @Nullable
    @Override
    public IBinder onBind(Intent intent) { // 서비스에 연결할 때 인터페이스를 반환함
        Toast.makeText(getApplicationContext(), "바인딩됨", Toast.LENGTH_SHORT).show();
        return messenger.getBinder(); // 메신저의 바인더를 꺼내서 줌
    }
}
