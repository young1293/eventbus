package com.bj.luevbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * lucky 2018/12/5
 * 2018 15:14
 */
public class TooActivity extends AppCompatActivity {

    private TextView textField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.too);

        textField = (TextView) findViewById(R.id.too_tv);
        textField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第三步发送消息
                EventBus.getDefault().post(new MessageEvent("aaaaaaaaaaaaaaaaaaaaa"));
                
            }
        });
        
        
    

    }
 
}
