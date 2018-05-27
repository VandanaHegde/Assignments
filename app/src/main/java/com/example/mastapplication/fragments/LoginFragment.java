package com.example.mastapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mastapplication.R;
import com.example.mastapplication.asyncTasks.ApiCallTask;
import com.example.mastapplication.interfaces.ApiResponseInterface;
import com.example.mastapplication.model.ApiCallOutput;
import com.example.mastapplication.model.LoginOutput;
import com.example.mastapplication.util.CommonUtil;
import com.example.mastapplication.util.DBAdapter;
import com.example.mastapplication.util.DatabseHelper;
import com.example.mastapplication.util.MastConstants;

/**
 * Created by vandanahegde on 27/05/18.
 */

public class LoginFragment extends Fragment implements ApiResponseInterface {
    private View view;
    private EditText nameEditText;
    private TextView submitTextView;
    private RelativeLayout progressLayout, contentLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        initialiseUI();
        setOnClicks();
        checkLogin();
        return view;
    }

    private void initialiseUI() {
        try {
            nameEditText = view.findViewById(R.id.user_name_edit_text);
            submitTextView = view.findViewById(R.id.submit_text_view);
            progressLayout = view.findViewById(R.id.progress_layout);
            contentLayout = view.findViewById(R.id.content_layout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showProgressLayout(boolean flag) {
        if (progressLayout == null) progressLayout = view.findViewById(R.id.progress_layout);
        if (flag) progressLayout.setVisibility(View.VISIBLE);
        else progressLayout.setVisibility(View.GONE);
    }

    private void setOnClicks() {
        submitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtil.hideSoftKeyboard(view, getActivity());
                try {
                    if (CommonUtil.isValidString(nameEditText.getText().toString())) {
                        if (CommonUtil.checkDataConnection(getActivity())) {
                            new ApiCallTask(LoginFragment.this, MastConstants.API_LOGIN, nameEditText.getText().toString()).execute();
                            showProgressLayout(true);
                            contentLayout.setVisibility(View.GONE);
                        } else CommonUtil.showToast(getActivity(), "No internet");
                    } else nameEditText.setError("Please enter the name");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void checkLogin() {
        DatabseHelper databseHelper = new DatabseHelper(getActivity());
        LoginOutput output = databseHelper.fetchUserData();
        if(output != null && output.getLogin() != null) navigate(output);
    }

    private void navigate(LoginOutput loginOutput) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MastConstants.LOGIN_DATA, loginOutput);
        RepositoryListFragment fragment = new RepositoryListFragment();
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

    @Override
    public void onRequestCompleted(String apiName, ApiCallOutput apiCallOutput) {
        if (apiCallOutput.getLoginOutput() != null) {
            LoginOutput loginOutput = (LoginOutput) apiCallOutput.getLoginOutput();
            if (loginOutput.getMessage() != null) {
                showProgressLayout(false);
                contentLayout.setVisibility(View.VISIBLE);
                CommonUtil.showToast(getActivity(), "Entered user name is not avaialable");
                nameEditText.setText("");
            } else {
                DatabseHelper databseHelper = new DatabseHelper(getActivity());
                databseHelper.insertUserData(loginOutput);
                navigate(loginOutput);
            }
        }
    }

    @Override
    public void onError(String apiName, ApiCallOutput apiCallOutput) {
        showProgressLayout(false);
        contentLayout.setVisibility(View.VISIBLE);
    }
}
