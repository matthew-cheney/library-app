package com.example.libraryofpeers.view.utils;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import android.text.style.ClickableSpan;

import androidx.annotation.NonNull;


public class ClickSpan extends ClickableSpan {

    private String alias;

    public ClickSpan(String alias) {
        this.alias = alias;
    }

    @Override
    public void onClick(@NonNull View widget) {
    }

}
