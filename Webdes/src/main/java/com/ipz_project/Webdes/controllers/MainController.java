package com.ipz_project.Webdes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;

/**Класс-контроллер, управление главной страницей сайта
 * Для администратора - вход, переход на страницу администратора
 * @author Tonya Shchirska
 */
@Controller
public class MainController {

    /**Функция перехода на главную страницу сайта
     * @return возвращает html файл - страницу на сайте
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Webdes studio");
        return "home";
    }

    /**Функция перехода на страницу ошибки про недопуск на страницу
     * @return возвращает html файл - страницу на сайте
     */
    @GetMapping("/error/403")
    public String error403(){
        return "403";
    }

    /**Функция перехода на страницу регистрирования (вход для администратора)
     * @return возвращает html файл - страницу на сайте
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
