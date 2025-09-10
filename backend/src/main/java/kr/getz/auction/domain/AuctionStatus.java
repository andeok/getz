package kr.getz.auction.domain;

public enum AuctionStatus {
	SCHEDULED,  // 경매 시작 대기 (예정됨)
	ONGOING,    // 경매 진행 중
	SOLD,       // 경매 종료 (낙찰)
	UNSOLD      // 경매 종료 (유찰)
}
