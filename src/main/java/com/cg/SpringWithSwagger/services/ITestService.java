package com.cg.SpringWithSwagger.services;

import com.cg.SpringWithSwagger.request.Request;
import com.cg.SpringWithSwagger.response.Response;

public interface ITestService {

    Response getUser(Request request);
}
