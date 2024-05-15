package com.example.cafeteria.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PRODUCTS")
public class ProductModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idProduct;
	private String name;
	private BigDecimal value;
    private String image;
    private String type;
    private LocalDateTime dateEntry;
	
	public UUID getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(UUID idProduct) {
		this.idProduct = idProduct;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDateEntry() {
        return dateEntry;
    }
    
    public void setDateEntry(LocalDateTime dateEntry) {
        this.dateEntry = dateEntry;
    }
}
