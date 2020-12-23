package com.ipz_project.Webdes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**Класс-контроллер, управление страницей администратора, страницей данных администратора
 * @author Tonya Shchirska */

@Controller
public class AdminController {

    /**Функция перехода на страницу администратора
     * @return возвращает html файл - страницу на сайте */
    @GetMapping("/admin")
    public String adminHome(){
        return "admin";
    }
/*
    @GetMapping("/admin/edit")
    public String adminEdit() {
        return "admin-edit";
    }

    @PostMapping("/admin/edit")
    public String adminUpdate(Model model){
        return "redirect:/admin";
    }*/

}
