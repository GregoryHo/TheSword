package com.ns.greg.thesword;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author gregho
 * @since 2018/3/12
 */

public class SwordActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Using @Info and @Status to build the demo data Json
    DemoData demoData = new DemoData();
    demoData.getDemoData_infoDraw().setUserName("Greg");
    demoData.getDemoData_infoDraw().setGender("Male");
    demoData.getDemoData_infoDraw().setAge("27");
    demoData.getDemoData_statusDraw().setJob("Android Developer");
    Gson gson = new GsonBuilder().create();
    String json = gson.toJson(demoData);
    System.out.println("Draw json: " + json);
  }
}
