package com.gyanpath.question.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("quiz")
public interface QuizServiceClient {

    @GetMapping("/api/v1/test")
    public String test();

}
