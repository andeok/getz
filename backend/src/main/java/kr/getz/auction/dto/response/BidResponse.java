package kr.getz.auction.dto.response;

import java.time.LocalDateTime;

import kr.getz.bid.domain.Bid;

public record BidResponse(
	String bidderNickname,
	int bidPrice,
	LocalDateTime bidTime
) {
	public static BidResponse from(Bid bid) {
		return new BidResponse(
			bid.getBidder().getNickname(),
			bid.getBidPrice(),
			bid.getCreatedAt()
		);
	}
}
