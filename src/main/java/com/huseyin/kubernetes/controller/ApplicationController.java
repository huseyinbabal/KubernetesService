package com.huseyin.kubernetes.controller;

import com.huseyin.kubernetes.dto.ScaleResponseDto;
import com.huseyin.kubernetes.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/scale")
    public ResponseEntity<ScaleResponseDto> scale(@RequestParam("name") String name,
            @RequestParam("replicas") Integer replicas) {
        return new ResponseEntity<>(applicationService.scale(name, replicas), HttpStatus.OK);
    }
}
