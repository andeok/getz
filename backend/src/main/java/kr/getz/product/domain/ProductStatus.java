package kr.getz.product.domain;

public enum ProductStatus {
	AVAILABLE,    // 판매 가능 (새로운 경매 등록 가능)
	ON_AUCTION,   // 경매 진행 중 (새로운 경매 등록 불가)
	SOLD          // 판매 완료 (새로운 경매 등록 불가)

}
