package com.ns.greg.thesword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ns.greg.thesword_annotations.Info;
import com.ns.greg.thesword_annotations.Status;

/**
 * @author gregho
 * @since 2018/3/21
 */

@Info @Status(childBeans = { "time", "job" }) public class DemoData {

  @Expose @SerializedName("job") private DemoData_InfoDraw demoData_infoDraw;
  @Expose @SerializedName("status") private DemoData_StatusDraw demoData_statusDraw;

  public DemoData() {
    demoData_infoDraw = new DemoData_InfoDraw();
    demoData_statusDraw = new DemoData_StatusDraw();
  }

  public DemoData_InfoDraw getDemoData_infoDraw() {
    return demoData_infoDraw;
  }

  public void setDemoData_infoDraw(DemoData_InfoDraw demoData_infoDraw) {
    this.demoData_infoDraw = demoData_infoDraw;
  }

  public DemoData_StatusDraw getDemoData_statusDraw() {
    return demoData_statusDraw;
  }

  public void setDemoData_statusDraw(DemoData_StatusDraw demoData_statusDraw) {
    this.demoData_statusDraw = demoData_statusDraw;
  }
}
