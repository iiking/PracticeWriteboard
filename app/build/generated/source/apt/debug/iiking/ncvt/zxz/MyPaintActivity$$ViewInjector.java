// Generated code from Butter Knife. Do not modify!
package iiking.ncvt.zxz;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MyPaintActivity$$ViewInjector {
  public static void inject(Finder finder, final iiking.ncvt.zxz.MyPaintActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2130968594, "field 'ivAnimView'");
    target.ivAnimView = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2130968585, "field 'fontlinearLayout'");
    target.fontlinearLayout = (android.widget.LinearLayout) view;
    view = finder.findRequiredView(source, 2130968582, "field 'btnOneShot' and method 'onViewClicked'");
    target.btnOneShot = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2130968583, "field 'btnStartAnim' and method 'onViewClicked'");
    target.btnStartAnim = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2130968581, "field 'btnClear' and method 'onViewClicked'");
    target.btnClear = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2130968584, "field 'btnretAnim' and method 'onViewClicked'");
    target.btnretAnim = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onViewClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2130968597, "field 'linearLayout3'");
    target.linearLayout3 = (android.widget.LinearLayout) view;
  }

  public static void reset(iiking.ncvt.zxz.MyPaintActivity target) {
    target.ivAnimView = null;
    target.fontlinearLayout = null;
    target.btnOneShot = null;
    target.btnStartAnim = null;
    target.btnClear = null;
    target.btnretAnim = null;
    target.linearLayout3 = null;
  }
}
