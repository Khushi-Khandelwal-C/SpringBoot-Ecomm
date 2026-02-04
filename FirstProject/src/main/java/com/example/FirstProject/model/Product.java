package com.example.FirstProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String Category;

    //@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private Date releaseDate;
    @Column(name = "PRODUCTAVAILABLE")
    private boolean productAvailable;

    @Column(name = "STOCKQUANTITY")
    //private int stockQuantity;
    //private boolean productAvailable;
    private int stockQuantity;

    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageDate;

























































































}
