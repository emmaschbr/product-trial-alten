package com.productrial.web;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class ProductController {

    //@GetMapping(value = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@Operation
}
