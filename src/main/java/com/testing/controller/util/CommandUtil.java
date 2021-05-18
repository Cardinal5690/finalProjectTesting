package com.testing.controller.util;

public class CommandUtil {

    public static String getUserPageByRole(int accessLevel){
        String page = "";
        switch (accessLevel){
            case 1:
                page = "redirect:/student";
                break;
            case 2:
                page= "redirect:/admin";
                break;
        }
        return page;
    }

}
