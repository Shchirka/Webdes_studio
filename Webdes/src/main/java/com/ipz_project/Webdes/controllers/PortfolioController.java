package com.ipz_project.Webdes.controllers;

import com.ipz_project.Webdes.models.Works;
import com.ipz_project.Webdes.repo.WorksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

/**Класс-контроллер, просмотр портфолио мастера
 * Для администратора - просмотр и редактирование данных
 * @author Tonya Shchirska */
@Controller
public class PortfolioController {

    /**Поле - связь с репозиторием работ для портфолио */
    @Autowired
    private WorksRepository worksRepository;

    /**Функция перехода на страницу просмотра портфолио мастера
     * @return возвращает html файл - страницу на сайте */
    @GetMapping("/portfolio")
    public String portfolio(Model model){
        Iterable<Works> works = worksRepository.findAll();
        model.addAttribute("works", works);
        return "portfolio";
    }

    @GetMapping("/portfolio/create")
    public String portfolioCreate(){
        return "portfolio-create";
    }

    /**Функция считывания информации в полях создания портфолио для товара, сохранение данных в базе данных
     * @param title - наименование товара
     * @param anons - краткое объяснение
     * @return возвращает html файл - страницу на сайте */
    @PostMapping("/portfolio/create")
    public String portfolioCreate(@RequestParam String title, @RequestParam String anons, @RequestParam byte[] images, Model model){
        Works works = new Works(title, anons, images);
        worksRepository.save(works);
        return "redirect:/portfolio";
    }

    /**Функция перехода на страницы доп информации про определенный товар
     * Доступ только для администратора
     * @param tovarId - уникальный идентефикатор товара
     * @return возвращает html файл - страницу на сайте */
    @GetMapping("/portfolio/{id}")
    public String portfolioDetails(@PathVariable(value = "id") long tovarId, Model model){
        if(!worksRepository.existsById(tovarId)){
            return "redirect:/portfolio";
        }
        Optional<Works> some_tovar = worksRepository.findById(tovarId);
        ArrayList<Works> res = new ArrayList<>();
        some_tovar.ifPresent(res::add);
        model.addAttribute("some_tovar", res);
        return "portfolio-details";
    }



    /**Функция перехода на страницу редактирования портфолио мастера
     * Доступ только для администратора
     * @return возвращает html файл - страницу на сайте */
    @GetMapping("/portfolio/{id}/edit")
    public String portfolioEdit(@PathVariable(value = "id") long tovarId, Model model){
        if(!worksRepository.existsById(tovarId)){
            return "redirect:/portfolio";
        }
        Optional<Works> some_tovar = worksRepository.findById(tovarId);
        ArrayList<Works> res = new ArrayList<>();
        some_tovar.ifPresent(res::add);
        model.addAttribute("some_tovar", res);
        return "portfolio-edit";
    }

    /**Функция считывания информации в полях редактирования заказа, сохранение измененных данных в базе данных
     * @param tovarId - уникальный идентефикатор товара
     * @param title - наименование товара
     * @param anons - краткое объяснение
     * @return возвращает html файл - страницу на сайте */
    @PostMapping("/portfolio/{id}/edit")
    public String portfolioUpdate(@RequestParam String title,@RequestParam String anons, @RequestParam byte[] images, @PathVariable(value = "id") long tovarId, Model model){
        Works works = worksRepository.findById(tovarId).orElseThrow(ArithmeticException::new);
        works.setTitle(title);
        works.setAnons(anons);
        worksRepository.save(works);

        return "redirect:/portfolio";
    }

    /**Функция считывания удаления определенного заказа, удаление данных из базы данных
     * Доступ только для администратора
     * @param tovarId - уникальный идентефикатор товара
     * @return возвращает html файл - страницу на сайте */
    @PostMapping("/portfolio/{id}/remove")
    public String portfolioDelete(@PathVariable(value = "id") long tovarId, Model model){
        Works works = worksRepository.findById(tovarId).orElseThrow(ArithmeticException::new);
        worksRepository.delete(works);

        return "redirect:/portfolio";
    }
}
