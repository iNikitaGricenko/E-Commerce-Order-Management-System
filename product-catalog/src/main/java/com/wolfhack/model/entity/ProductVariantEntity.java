package com.wolfhack.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Entity for {@link com.wolfhack.model.domain.ProductVariant}
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "product_variant")
public class ProductVariantEntity {

	@Id
	@Column(name = "variant_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "variant_name")
	private String variantName;

	@Column(name = "variant_description")
	private String variantDescription;

	@Column(name = "variant_price")
	private double variantPrice;

	@Column(name = "variant_stock_quantity")
	private long variantStockQuantity;

	@Override
	public final boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) {
			return false;
		}
		ProductVariantEntity that = (ProductVariantEntity) object;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
