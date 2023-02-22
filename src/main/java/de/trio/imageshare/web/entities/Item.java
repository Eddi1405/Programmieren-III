package de.trio.imageshare.web.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

    private int id;
    private String brand;
    private String name;
    private double price;

}
