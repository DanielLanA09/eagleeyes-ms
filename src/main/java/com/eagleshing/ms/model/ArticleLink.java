package com.eagleshing.ms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;

import com.eagleshing.ms.model.type.ArticleType;

@Entity(name = "article_link")
public class ArticleLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "block_id")
    private int blockId;

    @Column(length = 64)
    private String title;

    @Column(length = 255)
    private String link;

    @Column(length = 100)
    private String img;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ArticleType type;

    private int view;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }


}
