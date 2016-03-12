package com.ityang.xutildemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class MainActivity extends AppCompatActivity {
    private DbUtils dbUtils;
    private HttpUtils httpUtils;
    private BitmapUtils bitmapUtils;
    private ImageView img;
    private String url = "http://192.168.1.115:8080/Spring/user_execute";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbUtils = DbUtils.create(this);
        httpUtils = new HttpUtils();
        bitmapUtils = new BitmapUtils(this, Environment.getExternalStorageDirectory().getAbsolutePath()+"/xutilDemo/ImgDemo",0.4f);
        img = (ImageView)findViewById(R.id.img);
    }

    public void save(View view){
        User user = new User();
        user.setUserName("杨杰");
        user.setPassword("5002yj");
        user.setIsVerify(false);
        try {
            dbUtils.save(user);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void query(View view){
        try {
            User user = dbUtils.findFirst(Selector.from(User.class).where(WhereBuilder.b("id","=",1)));
            Toast.makeText(this,user.toString(),Toast.LENGTH_SHORT).show();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public void update(View view){
        User user  = new User();
       // user.setId(1);
        user.setUserName("itYang");
        user.setPassword("helloWorld");
        user.setIsVerify(true);
        try {
            dbUtils.update(user, WhereBuilder.b("id", "=", 1), "userName", "password", "isVerify");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void upload(View view){
        RequestParams params = new RequestParams();
        params.addBodyParameter("user.userName", "ityang");
        params.addBodyParameter("user.password", "5002yj");
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Toast.makeText(MainActivity.this, responseInfo.result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void showImg(View view){
        bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_launcher);

        bitmapUtils.display(img,"http://192.168.1.115:8080/Spring/img/b.jpg");
    }
}
