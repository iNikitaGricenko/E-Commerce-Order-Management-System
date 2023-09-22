package com.wolfhack.payment.model.entity;

import com.wolfhack.payment.model.ErrorType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity for {@link com.wolfhack.payment.model.domain.ErrorLog}
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class ErrorLogEntity {

	@Id
	@Column(name = "error_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "error_message")
	private String errorMessage;

	@Column(name = "error_code")
	private int errorCode;

	@Column(name = "error_date")
	private LocalDate errorDate;

	@Column(name = "error_type")
	@Enumerated(EnumType.STRING)
	private ErrorType errorType;

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
		ErrorLogEntity that = (ErrorLogEntity) object;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
