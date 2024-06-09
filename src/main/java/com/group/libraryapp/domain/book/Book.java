package com.group.libraryapp.domain.book;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id=null;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Column(nullable = false)
    private String name;
    protected Book(){}

    public Book(String name){
        if(name==null||name.isBlank()){
            throw new IllegalArgumentException("Illegal name");
        }
        this.name=name;
    }

}
