package kr.getz.personal.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;
import kr.getz.personal.BaseTimeEntity;
import kr.getz.personal.global.exception.ErrorCode;
import kr.getz.personal.global.exception.custom.IllegalArgumentException;

@Entity
public class Stock extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	private Long quantity;

	@Version
	private Long version;

	public void descrease(Long count) {
		if(this.quantity < count) {
			throw new IllegalArgumentException(ErrorCode.QUANTITY_EXCEED_STOCK);
		}
		this.quantity -= count;
	}
}
