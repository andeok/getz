package kr.getz.auction.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.getz.auction.domain.Auction;
import kr.getz.auction.dto.request.AuctionWithProductRequest;
import kr.getz.auction.dto.request.BidRequest;
import kr.getz.auction.dto.response.AuctionResponse;
import kr.getz.auction.dto.response.AuctionsResponse;
import kr.getz.auction.dto.response.AuctionsResponses;
import kr.getz.auction.repository.AuctionRepository;
import kr.getz.bid.domain.Bid;
import kr.getz.bid.repository.BidRepository;
import kr.getz.global.exception.AuctionException;
import kr.getz.global.exception.ExceptionCode;
import kr.getz.product.domain.Product;
import kr.getz.product.repository.ProductRepository;
import kr.getz.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuctionService {

	private final AuctionRepository auctionRepository;
	private final ProductRepository productRepository;
	private final BidRepository bidRepository;

	@Transactional
	public long createAuction(User user, AuctionWithProductRequest request) {

		Product product = request.product().toProduct(user);
		Product saveProduct = productRepository.save(product);

		Auction auction = request.auction().toAuction(saveProduct);
		Auction saveAuction = auctionRepository.save(auction);

		return saveAuction.getId();
	}

	@Transactional(readOnly = true)
	public Page<AuctionsResponse> getAuctionList(Pageable pageable) {
		return auctionRepository.findAll(pageable)
			.map(AuctionsResponse::from);
	}

	@Transactional(readOnly = true)
	public AuctionResponse getAuction(long id) {
		Auction auction = auctionRepository.findById(id)
			.orElseThrow(() -> new AuctionException(ExceptionCode.AUCTION_NOT_FOUND));

		return AuctionResponse.from(auction);
	}

	@Transactional
	public AuctionResponse placeBid(User user, BidRequest request, long auctionId) {
		Auction auction = auctionRepository.findById(auctionId)
			.orElseThrow(() -> new AuctionException(ExceptionCode.AUCTION_NOT_FOUND));

		Bid newBid = request.of(user, auction);

		auction.addBid(newBid);

		return AuctionResponse.from(auction);
	}

}
