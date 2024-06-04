package com.example.teamproject;

import data.dto.MemberDto;
import data.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {



    @GetMapping("/")
    public String home() {

        return "layout/main";

    }
}
