package com.example.cafeteria.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
@Table(name = "TB_ORDERS")
public class OrderModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
    // pesquisar se precisa de construtor

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idOrders;

	@ManyToOne
	@JoinColumn(name = "id_user")
    private UserModel userModel;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_orders")
    private List<OrderProductModel> productsQty = new ArrayList<>();

    private String status;

    private LocalDateTime dateEntry;

	public UUID getIdOrders() {
		return idOrders;
	}

	public void setIdOrders(UUID idOrders) {
		this.idOrders = idOrders;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public List<OrderProductModel> getProductsQty() {
		return productsQty;
	}

	public void setProductsQty(List<OrderProductModel> productsQty) {
		this.productsQty = productsQty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDateEntry() {
		return dateEntry;
	}

	public void setDateEntry(LocalDateTime dateEntry) {
		this.dateEntry = dateEntry;
	}    
}
