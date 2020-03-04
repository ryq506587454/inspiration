package com.ryq.inspiration.pojo;

import lombok.*;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String password;

    private String image;



}