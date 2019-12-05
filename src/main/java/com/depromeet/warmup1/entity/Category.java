package com.depromeet.warmup1.entity;


import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String image;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "connect_id")
    private Connect connect;

    @Builder
    private Category(String name, String image, Connect connect) {
        this.name = name;
        this.image = image;
        this.connect = connect;
    }

    public void update(String name, String image) {
        this.name = name;
        this.image = image;
    }

}
