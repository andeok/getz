package kr.getz.auction.domain;

public enum AuctionStatus {
	READY,  // 경매 시작 대기 (예정됨)
	OPEN,    // 경매 진행 중
	CLOSED,       // 경매 종료 (낙찰)
	EXPIRED      // 경매 종료 (유찰)
}
