package com.theboys.valheimcompendium;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;

public class RedditPageActivity extends AppCompatActivity {
    private WebView redditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_page);
        redditView = (WebView) findViewById(R.id.redditWebview);

        redditView.getSettings().setLoadsImagesAutomatically(true);
        redditView.getSettings().setJavaScriptEnabled(true);
        redditView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        redditView.setWebViewClient(new WebViewClient());

        //setContentView(redditView);

        String redditUrl = "https://www.reddit.com/r/valheim";

        redditView.loadUrl(redditUrl);

        try {
            String response = run(redditUrl);
            Log.i("Reddit", response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String run(String url) throws IOException {
        JSONArray array = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}