package com.ipz_project.Webdes.models;

import sun.plugin.javascript.navig.Image;

import javax.persistence.*;

/**Класс - таблица в базе данных
 * @author Tonya Shchirska */
@Entity
public class Works {

    /**Поле уникальный идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**Поля название и краткое описание */
    private String title, anons;

    /**Поле количество просмотров */
    private int views;

    private byte[] images;

    /**Функции геттеры и сеттеры для всех полей класса */
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public Works() {
    }

    public Works(String title, String anons, byte[] images) {
        this.title = title;
        this.anons = anons;
        this.images = images;
    }

    public Works(String title, String anons) {
        this.title = title;
        this.anons = anons;
    }
}
