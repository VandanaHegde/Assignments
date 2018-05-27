package com.example.mastapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mastapplication.R;
import com.example.mastapplication.adapter.ReposAdapter;
import com.example.mastapplication.asyncTasks.ApiCallTask;
import com.example.mastapplication.interfaces.ApiResponseInterface;
import com.example.mastapplication.model.ApiCallOutput;
import com.example.mastapplication.model.LoginOutput;
import com.example.mastapplication.util.CommonUtil;
import com.example.mastapplication.util.DatabseHelper;
import com.example.mastapplication.util.MastConstants;

/**
 * Created by vandanahegde on 27/05/18.
 */

public class RepositoryListFragment extends Fragment implements ApiResponseInterface{
    View view;
    private RecyclerView recyclerView;
    private LoginOutput userData;
    private RelativeLayout progressLayout, contentLayout;
    private ReposAdapter adapter;
    private TextView logoutTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_repository_list, container, false);
        fetchFromBundle();
        initialiseUI();
        onClicks();
        callApi();
        return view;
    }


    private void initialiseUI() {
        recyclerView  = view.findViewById(R.id.repositorty_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressLayout  = view.findViewById(R.id.progress_layout);
        contentLayout  = view.findViewById(R.id.content_list_layout);
        logoutTV  = view.findViewById(R.id.logout_text_view);
    }

    private void onClicks() {
        logoutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabseHelper databseHelper = new DatabseHelper(getActivity());
                databseHelper.deleteUserData(userData.getId());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new LoginFragment()).commit();
            }
        });
    }

    private void fetchFromBundle() {
        Bundle  bundle = getArguments();
        if(bundle != null && bundle.containsKey(MastConstants.LOGIN_DATA))
            userData = (LoginOutput) bundle.getSerializable(MastConstants.LOGIN_DATA);
    }

    private void callApi() {
        try {
            if(adapter == null)
            if (CommonUtil.checkDataConnection(getActivity())) {
                new ApiCallTask(this, MastConstants.API_FETCH_LIST, userData.getLogin().trim()).execute();
                showProgressLayout(true);
                contentLayout.setVisibility(View.GONE);
            } else {
                contentLayout.setVisibility(View.VISIBLE);
                progressLayout.setVisibility(View.GONE);
                CommonUtil.showToast(getActivity(), "No internet");
            }
            else recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showProgressLayout(boolean flag) {
        if (progressLayout == null) progressLayout = view.findViewById(R.id.progress_layout);
        if (flag) progressLayout.setVisibility(View.VISIBLE);
        else progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void onRequestCompleted(String apiName, ApiCallOutput apiCallOutput) {
        showProgressLayout(false);
        if(apiCallOutput.getRepositoryRes() != null) {
            if (apiCallOutput.getRepositoryRes().size() > 0) {
                contentLayout.setVisibility(View.VISIBLE);
                adapter = new ReposAdapter(getActivity(), apiCallOutput.getRepositoryRes());
                recyclerView.setAdapter(adapter);
            } else {
                contentLayout.setVisibility(View.VISIBLE);
                CommonUtil.showToast(getActivity(), "No repositories found");
            }
        } else CommonUtil.showToast(getActivity(), "No data found");

    }

    @Override
    public void onError(String apiName, ApiCallOutput apiCallOutput) {
        showProgressLayout(false);
        if(getActivity() != null) CommonUtil.showToast(getActivity(), "Something went wrong");
    }
}
