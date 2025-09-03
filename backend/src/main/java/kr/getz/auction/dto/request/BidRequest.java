package kr.getz.auction.dto.request;

import kr.getz.auction.domain.Auction;
import kr.getz.bid.domain.Bid;
import kr.getz.user.domain.User;

public record BidRequest(
	int bidPrice
) {
	public Bid of(User user, Auction auction) {
		return new Bid(bidPrice, auction, user);
	}
}
