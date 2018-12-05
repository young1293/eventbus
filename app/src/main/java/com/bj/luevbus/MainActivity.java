package com.bj.luevbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button m_btn;
    private TextView m_tv;
    /**
     * 在OnCreate()函数中注册EventBus，在OnDestroy（）函数中反注册。所以整体的注册与反注册的代码如下：
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //第一步：
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        m_tv = (TextView) findViewById(R.id.main_tv);

        m_btn = (Button) findViewById(R.id.main_btn);
        m_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第3步：发布活动
           EventBus.getDefault().post(new MessageEvent("Hello everyone!"));
             
                Intent it=new Intent(getApplicationContext(),TooActivity.class);
                startActivity(it);
           
            }
        });

    }
    //第五步
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent event){
        String msg="接收到了消息："+event.message;
        Log.d("harvic",msg);
        m_tv.setText(msg);
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }

 /*  // Called in the same thread (default)
 //ThreadMode is optional here
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessage(MessageEvent event) {
        Log.e("---", event.message);
        m_tv.setText(event.message);

    }
*/
   /* // This method will be called when a MessageEvent is posted (in the UI thread for Toast)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Toast.makeText(MainActivity.this, event.message, Toast.LENGTH_SHORT).show();
    }*/




    @Override
    protected void onDestroy() {
        super.onDestroy();
        //反注册
        EventBus.getDefault().unregister(this);
    }
}
