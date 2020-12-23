package com.ipz_project.Webdes.controllers;

import com.ipz_project.Webdes.models.Orders;
import com.ipz_project.Webdes.repo.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

/**Класс-контроллер, управление страницами заказов
 * Для администратора - просмотр и удаление заказов
 * Для пользователей - оформление заказов
 * @author Tonya Shchirska */
@Controller
public class OrderController {

    /**Поле - связь с репозиторием заказов */
    @Autowired
    private OrdersRepository ordersRepository;

    /**Функция перехода на страницу оформления заказов
     * @return возвращает html файл - страницу на сайте */
    @GetMapping("/order")
    public String order(Model model){
        return "order";
    }

    /**Функция перехода на страницу просмотра заказов
     * Доступ только для администратора
     * @return возвращает html файл - страницу на сайте */
    @GetMapping("/order/list")
    public String orders_list(Model model){
        Iterable<Orders> orders = ordersRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders-list";
    }

    /**Функция считывания информации в полях оформления заказа, сохранение данных в базе данных
     * @param order - наименование заказа
     * @param user - информация про пользователя
     * @return возвращает html файл - страницу на сайте */
    @PostMapping("/order")
    public String orderAdd(@RequestParam String order,@RequestParam String user, Model model){
        Orders orders = new Orders(order, user);
        ordersRepository.save(orders);
        return "redirect:/";
    }

    /**Функция перехода на страницы доп информации про определенный заказ
     * Доступ только для администратора
     * @param orderId - уникальный идентефикатор заказа
     * @return возвращает html файл - страницу на сайте */
    @GetMapping("/order/{id}")
    public String orderDetails(@PathVariable(value = "id") long orderId, Model model){
        if(!ordersRepository.existsById(orderId)){
            return "redirect:/order/list";
        }
        Optional<Orders> some_order = ordersRepository.findById(orderId);
        ArrayList<Orders> res = new ArrayList<>();
        some_order.ifPresent(res::add);
        model.addAttribute("some_order", res);
        return "order-details";
    }

    /**Функция перехода на страницу редактирования и удаления определенного заказа
     * Доступ только для администратора
     * @param orderId - уникальный идентефикатор заказа
     * @return возвращает html файл - страницу на сайте */
    @GetMapping("/order/{id}/edit")
    public String orderEdit(@PathVariable(value = "id") long orderId, Model model){
        if(!ordersRepository.existsById(orderId)){
            return "redirect:/order/list";
        }
        Optional<Orders> some_order = ordersRepository.findById(orderId);
        ArrayList<Orders> res = new ArrayList<>();
        some_order.ifPresent(res::add);
        model.addAttribute("some_order", res);
        return "order-edit";
    }

    /**Функция считывания информации в полях редактирования заказа, сохранение измененных данных в базе данных
     * @param orderId - уникальный идентефикатор заказа
     * @param order - наименование заказа
     * @param user - информация про пользователя
     * @return возвращает html файл - страницу на сайте */
    @PostMapping("/order/{id}/edit")
    public String orderUpdate(@RequestParam String order,@RequestParam String user,@PathVariable(value = "id") long orderId, Model model){
        Orders orders = ordersRepository.findById(orderId).orElseThrow(ArithmeticException::new);
        orders.setOrder(order);
        orders.setUser(user);
        ordersRepository.save(orders);

        return "redirect:/order/list";
    }

    /**Функция считывания удаления определенного заказа, удаление данных из базы данных
     * Доступ только для администратора
     * @param orderId - уникальный идентефикатор заказа
     * @return возвращает html файл - страницу на сайте */
    @PostMapping("/order/{id}/remove")
    public String orderDelete(@PathVariable(value = "id") long orderId, Model model){
        Orders orders = ordersRepository.findById(orderId).orElseThrow(ArithmeticException::new);
        ordersRepository.delete(orders);

        return "redirect:/order/list";
    }


}
