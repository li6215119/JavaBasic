package com.kerwin.classloading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import dalvik.system.PathClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    public void test1(View view){





        Log.i("classLoadTest","getClassLoader："+getClassLoader());
        Log.i("classLoadTest","getParent："+ getClassLoader().getParent());
        Log.i("classLoadTest","getParent.getParent："+ getClassLoader().getParent().getParent());
//        Log.i("classLoadTest","getParent："+ getClassLoader().getParent().getParent().getParent());
//        Log.i("classLoadTest","getParent："+ getClassLoader().getParent().getParent().getParent().getParent());
    }




}
