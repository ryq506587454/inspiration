package com.ryq.inspiration.pojo;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String password;

    private String image;



}