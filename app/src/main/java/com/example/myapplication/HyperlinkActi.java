package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HyperlinkActi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyperlink);

        TextView tv = (TextView) findViewById(R.id.tv);
        String html = "「非著名程序员」可能是东半球最好的技术分享公众号。每天，每周定时推送一些有关移动开发的原创文章和教程。 不信你可以\n";
        html += "<a href='http://www.baidu.com'>百度一下</a> 哈哈，有意思吧！记住微信号是：smart_android 哦";
        CharSequence charSequence = Html.fromHtml(html);
        SpannableStringBuilder builder = new SpannableStringBuilder(charSequence);
        URLSpan[] urlSpans = builder.getSpans(0, charSequence.length(), URLSpan.class);
        for (URLSpan span : urlSpans) {
            int start = builder.getSpanStart(span);
            int end = builder.getSpanEnd(span);
            int flag = builder.getSpanFlags(span);
            final String link = span.getURL();
            builder.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                // 捕获<a>标签点击事件，及对应超链接link
                    Log.d("Hyperlink", "Clicked " + link);
                }
            }, start, end, flag);
            builder.removeSpan(span);
        }
        tv.setLinksClickable(true);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(charSequence);
    }
}
