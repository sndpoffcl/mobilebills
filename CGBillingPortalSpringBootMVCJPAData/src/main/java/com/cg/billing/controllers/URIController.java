package com.cg.billing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class URIController {

	@RequestMapping("/")
    public String getIndexPage(){
        return "indexPage";
    }
	
	
}
