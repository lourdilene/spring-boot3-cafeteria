package com.example.cafeteria.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ORDER_PRODUCT")
public class OrderProductModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idOrderProduct;
    
 // Estudar como implementar os construtores

	private int qty;
	
//    @ManyToOne
//    @JoinColumn(name = "id_order")
//    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductModel product;
    
	public UUID getIdOrderProduct() {
		return idOrderProduct;
	}

	public void setIdOrderProduct(UUID idOrderProduct) {
		this.idOrderProduct = idOrderProduct;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}
}
