package com.example.memorydb.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


public abstract class Entity implements PrimaryKey{

    @Getter
    @Setter
    private Long id;
}
