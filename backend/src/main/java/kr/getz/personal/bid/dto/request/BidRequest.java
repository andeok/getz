package kr.getz.personal.bid.dto.request;

import kr.getz.personal.auction.domain.Auction;
import kr.getz.personal.bid.domain.Bid;
import kr.getz.personal.member.domain.Member;

public record BidRequest(
	Integer bidAmount
) {

	public Bid toBid(Auction auction, Member member) {
		return Bid.builder()
			.auctionId(auction.getId())
			.bidderId(member.getId())
			.bidAmount(bidAmount)
			.build();
	}
}
