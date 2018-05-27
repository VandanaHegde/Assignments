package com.example.mastapplication.interfaces;

import com.example.mastapplication.model.ApiCallOutput;

/**
 * Created by vandanahegde on 27/05/18.
 */

public interface ApiResponseInterface {

    public void onRequestCompleted(String apiName, ApiCallOutput apiCallOutput);
    public void onError(String apiName, ApiCallOutput apiCallOutput);

}
