package com.techprimers.cache.springredisexample.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private Long salary;
}
