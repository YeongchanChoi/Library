package com.group.libraryapp.dto.user.response;

public class UserResponseByOne {
    private long id;
    private String name;
    private Integer age;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public UserResponseByOne(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
