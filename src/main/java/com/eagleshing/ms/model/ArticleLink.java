package com.eagleshing.ms.model;

import com.eagleshing.ms.model.type.ArticleType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 20)
    private ArticleType type;

    public int getId() {
        return id;
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
