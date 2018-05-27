package com.example.mastapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.example.mastapplication.R;
import com.example.mastapplication.util.MastConstants;

/**
 * Created by vandanahegde on 27/05/18.
 */

public class WebFragment extends Fragment {
    private View view;
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_web, container, false);
        initialiseUI();
        setupData();
        return view;
    }

    private void setupData() {
        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey(MastConstants.HTML_URL))
            webView.loadUrl(bundle.getString(MastConstants.HTML_URL));
    }

    private void initialiseUI() {
        webView = view.findViewById(R.id.html_webview);
        webView.getSettings().setJavaScriptEnabled(true);
    }

}
