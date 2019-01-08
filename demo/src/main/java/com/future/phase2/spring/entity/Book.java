package com.future.phase2.spring.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "book") // karena pakai mongoDB
public class Book implements Serializable { // Serializable = menunjukkan java Bean yang bisa di serialize tidak pengaruh apa-apa

    private String id;

    private String name;

    private String type;
}
