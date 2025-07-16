package com.gyanpath.quiz.service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("security")
public interface UserServiceClient {


}
