package com.eagleshing.ms.model;

import com.eagleshing.ms.model.type.BlockType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "home_block")
public class HomeBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 30)
    private BlockType type;

    private int position;

    @Column(name = "img",length = 255)
    private String img;

    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
