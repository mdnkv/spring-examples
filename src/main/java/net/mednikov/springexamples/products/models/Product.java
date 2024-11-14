package net.mednikov.springexamples.products.models;

import jakarta.persistence.*;
import net.mednikov.springexamples.categories.models.Category;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "products_product")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku_number", nullable = false, unique = true)
    private String skuNumber;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Category category;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Product product)) return false;

        return new EqualsBuilder()
                .append(skuNumber, product.skuNumber)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(skuNumber)
                .toHashCode();
    }

    public Long getId() {
        return id;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Optional<Category> getCategory() {
        return Optional.ofNullable(category);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static final class ProductBuilder {
        private Long id;
        private String skuNumber;
        private String name;
        private String description;
        private BigDecimal price;
        private Category category;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public ProductBuilder() {
        }

        public ProductBuilder(Product other) {
            this.id = other.id;
            this.skuNumber = other.skuNumber;
            this.name = other.name;
            this.description = other.description;
            this.price = other.price;
            this.category = other.category;
            this.createdAt = other.createdAt;
            this.updatedAt = other.updatedAt;
        }

        public ProductBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder withSkuNumber(String skuNumber) {
            this.skuNumber = skuNumber;
            return this;
        }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductBuilder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public ProductBuilder withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ProductBuilder withUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setSkuNumber(skuNumber);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategory(category);
            product.createdAt = this.createdAt;
            product.id = this.id;
            product.updatedAt = this.updatedAt;
            return product;
        }
    }
}
