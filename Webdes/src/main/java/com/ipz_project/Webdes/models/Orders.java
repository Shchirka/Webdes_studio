package com.ipz_project.Webdes.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**Класс - таблица в базе данных
 * @author Tonya Shchirska */
@Entity
@Table(name = "order_list")
public class Orders {

    /**Поле уникальный идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**Поле название заказа */
    @Column(name = "list")
    private String order;

    /**Поле пользователь */
    private String user;

    /**Поле цена */
    private int price;

    /**Функции геттеры и сеттеры для всех полей */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Orders() {
    }

    public Orders(String order, String user) {
        this.order = order;
        this.user = user;
    }
}
