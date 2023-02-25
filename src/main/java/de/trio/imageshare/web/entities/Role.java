package de.trio.imageshare.web.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class Role {
    private String name;
    private String id;
    private List<User> users;
}
