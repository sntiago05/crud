package productos.crud.domain;

import java.math.BigDecimal;
import java.util.Objects;

import static productos.crud.utils.CompareOption.*;
import static productos.crud.utils.HandleThrow.checkAndThrow;
import static productos.crud.utils.validators.compareNumbers;
import static productos.crud.utils.validators.emptyString;


public class Producto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;

    public Producto() {
    }

    public Producto(String name, BigDecimal price, Integer stock) {
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
    }

    public Producto(Long id, String name, BigDecimal price, Integer stock) {
        this(name, price, stock);
        this.setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        checkAndThrow(() -> compareNumbers(id, 0L, LESSTHAN), "id must be greather than 0");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkAndThrow(() -> emptyString(name), "name cannot be blank");
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        checkAndThrow(() -> compareNumbers(price, BigDecimal.ZERO, LESSTHAN), "price must be grather than 0");
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        checkAndThrow(() -> compareNumbers(stock, 0, LESSTHAN), "Stock must bre greather than 0");
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id) && Objects.equals(name, producto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
