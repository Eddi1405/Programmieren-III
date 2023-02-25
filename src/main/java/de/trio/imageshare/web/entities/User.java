package de.trio.imageshare.web.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String email;
    private List<Role> roles = new ArrayList<>();
    private char gender;
}
