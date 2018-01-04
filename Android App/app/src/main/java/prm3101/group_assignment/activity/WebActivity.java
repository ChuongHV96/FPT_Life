package prm3101.group_assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import prm3101.group_assignment.R;

import static android.view.View.SCROLLBARS_INSIDE_OVERLAY;

public class WebActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mToolbar = (Toolbar) findViewById(R.id.nav);
        mToolbar.setTitle("FPT Life");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String article = bundle.getString("ARTICLE");
        String more = bundle.getString("MORE");

        //Set url
        final WebView wv = (WebView) findViewById(R.id.content);
        wv.setFocusableInTouchMode(true);
        wv.setFocusable(true);
        wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv.getSettings().setDomStorageEnabled(true);
        wv.getSettings().setDatabaseEnabled(true);
        wv.getSettings().setAppCacheEnabled(true);
        wv.setScrollBarStyle(SCROLLBARS_INSIDE_OVERLAY);
        wv.getSettings().setJavaScriptEnabled(true);
        if (more != null) {
            wv.loadUrl(more);
                    Log.e("WebActivity - Success", more);
        } else {
            wv.loadUrl(article);
        }
        wv.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
