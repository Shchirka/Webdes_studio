package com.ipz_project.Webdes.models;


import javax.persistence.*;

/**Класс - таблица в базе данных
 * @author Tonya Shchirska */
@Entity
public class Price_list {

    /**Поле уникальный идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**Поля название продукта, краткое описание */
    private String title, anons;

    /**Поля мин/макс цена продукта */
    private int price_1, price_2;

    /**Функции геттеры и сеттеры для всех полей */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public int getPrice_1() {
        return price_1;
    }

    public void setPrice_1(int price_1) {
        this.price_1 = price_1;
    }

    public int getPrice_2() {
        return price_2;
    }

    public void setPrice_2(int price_2) {
        this.price_2 = price_2;
    }

    public Price_list(String title, String anons, int price_1, int price_2) {
        this.title = title;
        this.anons = anons;
        this.price_1 = price_1;
        this.price_2 = price_2;
    }

    public Price_list() {
    }
}
