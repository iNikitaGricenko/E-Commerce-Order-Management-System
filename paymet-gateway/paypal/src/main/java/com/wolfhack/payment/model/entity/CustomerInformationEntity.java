package com.wolfhack.payment.model.entity;

import com.wolfhack.payment.model.domain.CustomerInformation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity for {@link CustomerInformation}
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class CustomerInformationEntity {

	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "customer_reference")
	private String reference;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "cardholder_name")
	private String cardHolderName;

	@Column(name = "card_type")
	private String cardType;

	@Column(name = "card_last4_digits")
	private String cardLast4Digits;

	@Column(name = "billing_address")
	private String billingAddress;

	@Column(name = "payment_method_id")
	private Long paymentMethodId;

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
		CustomerInformationEntity that = (CustomerInformationEntity) object;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
