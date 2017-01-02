package com.example;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@SuppressWarnings("serial")
public class Account implements Serializable {
    private long id;
    private String name;
}
