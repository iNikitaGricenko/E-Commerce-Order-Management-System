package com.wolfhack.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity for {@link com.wolfhack.model.domain.ProductInventory}
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "product_inventory")
public class ProductInventoryEntity {

	@Id
	@Column(name = "inventory_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "product_name")
	private String name;

	@Column(name = "available_quantity")
	private long availableQuantity;

	@Column(name = "reserver_quantity")
	private long reservedQuantity;

	@Column(name = "created_date")
	private LocalDate createdDate;

	@Column(name = "updated_date")
	private LocalDate updatedDate;

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
		ProductInventoryEntity that = (ProductInventoryEntity) object;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
