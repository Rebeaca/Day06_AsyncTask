package com.example.user.day06_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button b;
    private TextView tv;
    private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //开启异步任务
               new Download().execute("开始下载");
            }
        });
    }

    private void init() {
        b= (Button) findViewById(R.id.button1);
        tv= (TextView) findViewById(R.id.tv);
        pb= (ProgressBar) findViewById(R.id.progressBar);
    }
    private class Download extends AsyncTask<String,Integer,String>{


        @Override
        protected String doInBackground(String... params) {//返回类型与第三个泛型数据类型一致
            //参数类型与第一个泛型的数据类型一致
            for (int i=0;i<100;i++){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);//与第二个泛型数据类型一致
            }
            return "下载完成";
        }
        /*
        * 在异步之后调用的方法
        * onPostExec()
        * onProgressUpdate()
        * */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }


}
