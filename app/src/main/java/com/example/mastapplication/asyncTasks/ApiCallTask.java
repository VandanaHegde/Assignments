package com.example.mastapplication.asyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mastapplication.interfaces.ApiResponseInterface;
import com.example.mastapplication.model.ApiCallOutput;
import com.example.mastapplication.model.LoginOutput;
import com.example.mastapplication.model.RepositoryRes;
import com.example.mastapplication.util.CommonUtil;
import com.example.mastapplication.util.MastConstants;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by vandanahegde on 27/05/18.
 */

public class ApiCallTask extends AsyncTask<Void, Void, ApiCallOutput> {
    String apiName, userName;
    ApiResponseInterface callback;
    ApiCallOutput apiCallOutput = new ApiCallOutput();


    public ApiCallTask(ApiResponseInterface callback, String apiName, String userName) {
        this.apiName = apiName;
        this.userName = userName;
        this.callback = callback;
    }

    @Override
    protected ApiCallOutput doInBackground(Void... voids) {
        String response = null;
        switch (apiName) {
            case MastConstants.API_LOGIN:
                response = CommonUtil.fetchGetResponse("https://api.github.com/users/" + userName);
                Log.e("API_LOGIN", response + "");
                if (response != null && !response.equals("")) {
                    LoginOutput loginOutput = null;
                    try {
                        loginOutput = new Gson().fromJson(response, LoginOutput.class);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (loginOutput != null) {
                        apiCallOutput.setStatusCode(MastConstants.SUCCESS);
                        apiCallOutput.setLoginOutput(loginOutput);
                    } else apiCallOutput.setStatusCode(MastConstants.ERROR);
                } else apiCallOutput.setStatusCode(MastConstants.ERROR);
                break;
            case MastConstants.API_FETCH_LIST:
                response = CommonUtil.fetchGetResponse("https://api.github.com/users/" + userName + "/repos");
//                response = CommonUtil.fetchGetResponse("https://api.github.com/users/shreya/repos");
                Log.e("API_FETCH_LIST", response + "");
                if (response != null && !response.equals("")) {
                    List<RepositoryRes.RepositoryData> list = null;
                    try {
                        list = new Gson().fromJson(response, new TypeToken<List<RepositoryRes.RepositoryData>>() {
                        }.getType());
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                    if (list != null) {
                        apiCallOutput.setStatusCode(MastConstants.SUCCESS);
                        apiCallOutput.setRepositoryRes(list);
                    } else apiCallOutput.setStatusCode(MastConstants.ERROR);
                } else apiCallOutput.setStatusCode(MastConstants.ERROR);
                break;
        }
        return apiCallOutput;
    }

    @Override
    protected void onPostExecute(ApiCallOutput apiCallOutput) {
        super.onPostExecute(apiCallOutput);
        if (apiCallOutput.getStatusCode() == MastConstants.SUCCESS)
            callback.onRequestCompleted(apiName, apiCallOutput);
        else callback.onError(apiName, apiCallOutput);
    }
}
