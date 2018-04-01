package com.ray.test.testingspannablestringfortextview;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends ActionBarActivity {
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.txt); //創建SpannableString對象,內容不可修改
        SpannableString ss = new SpannableString("字體測試字體絕對大小相對大小縮放大小"
                + "前景顏色背景顏色字體樣式下劃線刪除線上標X2下標Y3連結"
                + "字體外表圖圖圖圖"); //使用setSpan方法設置標記對象
        // 使用removeSpan解綁標記對象
        // public void setSpan (Object what, int start, int end, int flags)
        // 綁定特定的標記對象（Object what）到起始為start，終止為end的text對象上(若已綁定標記對象則先移除)
        // 設置字體TypefaceSpan
        // 設置字體(default,default-bold,monospace,serif,sans-serif)
        ss.setSpan(new TypefaceSpan("sans-serif"),0,4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 設置字體大小(絕對值)AbsoluteSizeSpan
        ss.setSpan(new AbsoluteSizeSpan(30, true), 4, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 設置字體大小(相對值)RelativeSizeSpan
        ss.setSpan(new RelativeSizeSpan(0.5f), 10, 14, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        // 設置字體大小(相對值,默認字體寬度的倍數)ScaleXSpan
        ss.setSpan(new ScaleXSpan(1.5f), 14, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 設置字體的前景色ForegroundColorSpan
        ss.setSpan(new ForegroundColorSpan(Color.BLUE), 18, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 設置字體的背景色BackgroundColorSpan
        ss.setSpan(new BackgroundColorSpan(Color.YELLOW), 22, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 設置字體的樣式StyleSpan
        ss.setSpan(new StyleSpan(Typeface.BOLD), 26, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 設置下劃線UnderlineSpan
        ss.setSpan(new UnderlineSpan(), 30, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 刪除線StrikethroughSpan
        ss.setSpan(new StrikethroughSpan(), 33, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 上標SuperscriptSpan
        ss.setSpan(new SuperscriptSpan(), 39, 40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 下標SubscriptSpan
        ss.setSpan(new SubscriptSpan(), 43, 44, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 連結URLSpan-->需要添加setMovementMethod方法附加響應
        ss.setSpan(new URLSpan("http://www.google.com"), 44, 46, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 關於getResources().getXml()的用法
        // 詳細說明請看這篇文章 http://blog.csdn.net/lckj686/article/details/39474583
        XmlResourceParser parser = getResources().getXml(R.xml.color_selector);
        ColorStateList color = null;
        ColorStateList linkColor = null;

        try {
            color = ColorStateList.createFromXml(getResources(), parser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            linkColor = ColorStateList.createFromXml(getResources(), parser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 字體外觀設置（依次包括字體名稱，字體大小，字體樣式，字體顏色，連結顏色)TextAppearanceSpan
        ss.setSpan(new TextAppearanceSpan("serif", Typeface.BOLD_ITALIC, 40, color, linkColor), 46, 50, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 項目符號BulletSpan
        // 圖片ImageSpan
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ss.setSpan(new ImageSpan(drawable), 50, 54, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new BulletSpan(BulletSpan.STANDARD_GAP_WIDTH, Color.RED), 0, ss.length(), Spanned.SPAN_COMPOSING);
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        mTextView.setText(ss);
    }
}

