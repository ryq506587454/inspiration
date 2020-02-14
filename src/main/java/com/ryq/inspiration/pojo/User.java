package com.ryq.inspiration.pojo;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    private Integer id;

    @NonNull
    private String name;


    @NonNull
    private String password;

    public User(String name) {
        this.name = name;
    }
}