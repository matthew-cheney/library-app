package com.example.libraryofpeers.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.libraryofpeers.R;

public class ImageSelectorActivity extends AppCompatActivity {

    private final String GOOGLE_IMAGES_URL = "https://images.google.com/";
    private final String GOOGLE_URL = "https://www.google.com/";
    private final String IMAGE_URL_IDENTIFIER = "imgurl=";

    private WebView webView;
    private String imageUrl = null;
    Button saveBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selector);

        webView = (WebView) findViewById(R.id.imageSelectorWebView);
        webView.loadUrl(GOOGLE_IMAGES_URL);

        Button cancelBtn = (Button) findViewById(R.id.imageSelectorBackButton);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cancel();
            }
        });

        saveBtn = (Button) findViewById(R.id.imageSelectorSaveButton);
        saveBtn.setEnabled(false);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save();
            }
        });

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (isGoogleUrl(url) && !isImageUrl(url)) {
                    view.loadUrl(url);
                }
                else {
                    imageUrl = getImageUrl(url);
                    ShowImageSelectedMessage();
                    saveBtn.setEnabled(true);
                }
                return true;
            }
        });
    }

    public void Cancel() {
        webView.loadUrl(GOOGLE_IMAGES_URL);
        imageUrl = null;
        saveBtn.setEnabled(false);
        setResult(RESULT_OK, new Intent());
        finish();
    }

    public void Save() {
        webView.loadUrl(GOOGLE_IMAGES_URL);
        imageUrl = null;
        saveBtn.setEnabled(false);
        Intent data = new Intent();
        data.putExtra("IMAGE_URL", imageUrl);
        setResult(RESULT_OK, data);
        finish();
    }

    private boolean isGoogleUrl(String url) {
        return url.startsWith(GOOGLE_URL) || url.startsWith(GOOGLE_IMAGES_URL);
    }

    private boolean isImageUrl(String url) {
        return url.contains(IMAGE_URL_IDENTIFIER);
    }

    private String getImageUrl(String url) {
        int startingIndex = url.indexOf(IMAGE_URL_IDENTIFIER) + IMAGE_URL_IDENTIFIER.length();
        int endingIndex = url.indexOf('&');
        return url.substring(startingIndex, endingIndex);
    }

    private void ShowImageSelectedMessage() {
        Toast toast = Toast.makeText(this, "Image selected!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 10);
        toast.show();
    }
}