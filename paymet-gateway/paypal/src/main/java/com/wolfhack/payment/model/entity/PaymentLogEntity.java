package com.wolfhack.payment.model.entity;

import com.wolfhack.payment.model.LogType;
import com.wolfhack.payment.model.domain.PaymentLog;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity for {@link PaymentLog}
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class PaymentLogEntity {

	@Id
	@Column(name = "log_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "request_payload")
	private String requestPayload;

	@Column(name = "response_payload")
	private String responsePayload;

	@Column(name = "log_date")
	private LocalDate logDate;

	@Column(name = "log_type")
	@Enumerated(EnumType.STRING)
	private LogType logType;

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
		PaymentLogEntity that = (PaymentLogEntity) object;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
