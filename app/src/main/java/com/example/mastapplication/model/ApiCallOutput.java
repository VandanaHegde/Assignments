package com.example.mastapplication.model;

import java.util.List;

public class ApiCallOutput {
    private int statusCode;
    private LoginOutput loginOutput;
    private List<RepositoryRes.RepositoryData> repositoryRes;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public LoginOutput getLoginOutput() {
        return loginOutput;
    }

    public void setLoginOutput(LoginOutput loginOutput) {
        this.loginOutput = loginOutput;
    }

    public List<RepositoryRes.RepositoryData> getRepositoryRes() {
        return repositoryRes;
    }

    public void setRepositoryRes(List<RepositoryRes.RepositoryData> repositoryRes) {
        this.repositoryRes = repositoryRes;
    }
}
