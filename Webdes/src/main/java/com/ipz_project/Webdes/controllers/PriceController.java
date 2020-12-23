package com.ipz_project.Webdes.controllers;

import com.ipz_project.Webdes.models.Price_list;
import com.ipz_project.Webdes.repo.Price_listRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

/**Класс-контроллер, просмотр прайса и контактов
 * Для администратора - редактирование данных
 * @author Tonya Shchirska */
@Controller
public class PriceController {

    /**Поле - связь с репозиторием заказов */
    @Autowired
    private Price_listRepository priceListRepository;

    /**Функция перехода на страницу прайса и контактов
     * @return возвращает html файл - страницу на сайте */
    @GetMapping("/price")
    public String price(Model model){
        Iterable<Price_list> price_lists = priceListRepository.findAll();
        model.addAttribute("price_lists", price_lists);
        return "price";
    }

    /**Функция перехода на страницу составления прайса
     * @return возвращает html файл - страницу на сайте */
    @GetMapping("/price/create")
    public String priceCreate(Model model){
        return "price-create";
    }

    /**Функция считывания информации в полях оформления заказа, сохранение данных в базе данных
     * @param title - наименование продукта
     * @param anons - краткое описание продукта
     * @param price_1 -минимальная цена продукта
     * @param price_2 -максимальная цена продукта
     * @return возвращает html файл - страницу на сайте */
    @PostMapping("/price/create")
    public String priceAdd(@RequestParam String title, @RequestParam String anons, @RequestParam int price_1, @RequestParam int price_2, Model model){
        Price_list price_list = new Price_list(title, anons, price_1, price_2);
        priceListRepository.save(price_list);
        return "redirect:/price";
    }

    /**Функция перехода на страницу редактирования данных на странице прайса
     * Доступ только администратору
     * @param priceId - уникальный идентификатор товара
     * @return возвращает html файл - страницу на сайте */
    @GetMapping("/price/{id}/edit")
    public String priceEdit(@PathVariable(value = "id") long priceId, Model model){
        if(!priceListRepository.existsById(priceId)){
            return "redirect:/price";
        }
        Optional<Price_list> some_price = priceListRepository.findById(priceId);
        ArrayList<Price_list> res = new ArrayList<>();
        some_price.ifPresent(res::add);
        model.addAttribute("some_price", res);
        return "price-edit";
    }

    /**Функция считывания информации в полях редактирования заказа, сохранение измененных данных в базе данных
     * @param priceId - уникальный идентефикатор
     * @param title - наименование товара
     * @param anons - краткое объяснение
     * @param price_1 - минимальная цена на товар
     * @param price_2 - максимальная цена на товар
     * @return возвращает html файл - страницу на сайте */
    @PostMapping("/price/{id}/edit")
    public String priceUpdate(@RequestParam String title, @RequestParam String anons, @RequestParam int price_1, @RequestParam int price_2, @PathVariable(value = "id") long priceId, Model model){
        Price_list price_list = priceListRepository.findById(priceId).orElseThrow(ArithmeticException::new);
        price_list.setTitle(title);
        price_list.setAnons(anons);
        price_list.setPrice_1(price_1);
        price_list.setPrice_2(price_2);
        priceListRepository.save(price_list);

        return "redirect:/price";
    }

    /**Функция считывания удаления определенного товара из прайса, удаление данных из базы данных
     * Доступ только для администратора
     * @param priceId - уникальный идентефикатор товара
     * @return возвращает html файл - страницу на сайте */
    @PostMapping("/price/{id}/remove")
    public String orderDelete(@PathVariable(value = "id") long priceId, Model model){
        Price_list orders = priceListRepository.findById(priceId).orElseThrow(ArithmeticException::new);
        priceListRepository.delete(orders);

        return "redirect:/price";
    }

}
