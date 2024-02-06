package com.gerenciamentoestoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@Entity
@Table(name = "produto")
public class Produto implements Serializable {
   private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nomeProduto;

    @Column(unique = true)
    private String sku;

    @Column
    private BigDecimal preco;

}
