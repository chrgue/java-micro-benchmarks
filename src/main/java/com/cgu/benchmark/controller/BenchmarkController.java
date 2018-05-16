package com.cgu.benchmark.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BenchmarkController {

    @GetMapping("/endpoint")
    public ResponseEntity endpoint(@RequestParam(required = false) Long id) {
        // do something here
        return ResponseEntity.ok().build();
    }
}
