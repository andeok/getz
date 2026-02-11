package kr.getz.personal.product.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import kr.getz.personal.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@NoArgsConstructor
public class Product extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private Long price;
	private Long discountPrice;

	private LocalDateTime startDate;
	private LocalDateTime endDate;

	@OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
	private Stock stock;

	public Product(String name, Long price, Long discountPrice, LocalDateTime startDate, LocalDateTime endDate) {
		this.name = name;
		this.price = price;
		this.discountPrice = discountPrice;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public boolean isSaleActive(LocalDateTime now) {
		return !now.isBefore(startDate) && !now.isAfter(endDate);
	}
}
