package com.ns.greg.thesword;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.ns.greg.thesword_annotations.Draw;
import com.thesword.drawsword.DrawSword;

/**
 * @author gregho
 * @since 2018/3/12
 */

@Draw
public class SwordActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    DrawSword.draw("DragonSlave");
  }
}
