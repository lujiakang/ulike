package com.ruoyi.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * <b> Class <b> is
 * </p>
 *
 * @author Wind
 * @since 2026/02/04
 */
@RestController
@RequestMapping("/user")
public class TestController {

    @GetMapping("/{id}")
    public String test(@PathVariable Long id)
    {
        return "success";
    }

}
