package com.thefinestartist.finestwebview.sample;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;

import com.thefinestartist.finestwebview.FinestWebView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        show();
    }

    @OnClick(R.id.show)
    public void show() {
        new FinestWebView.Builder(this)
                .titleDefault("FinestWebView")
                .toolbarScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS)
//                .toolbarScrollFlags(0) // 0 for not collapsing
//                .gradientDivider(false)
//                .dividerHeight(100)
//                .toolbarColorRes(R.color.accent)
//                .dividerColorRes(R.color.black_30)
//                .iconDefaultColorRes(R.color.accent)
//                .iconDisabledColorRes(R.color.gray)
//                .iconPressedColorRes(R.color.black)
//                .progressBarHeight(DipPixelHelper.getPixel(this, 3))
//                .progressBarColorRes(R.color.accent)
                .backPressToClose(false)
                .show("http://thefinestartist.com");
        overridePendingTransition(R.anim.modal_activity_open_enter, R.anim.modal_activity_open_exit);
    }
}
