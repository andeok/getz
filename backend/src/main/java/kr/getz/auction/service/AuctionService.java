package kr.getz.auction.service;

import java.util.Comparator;
import java.util.List;

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
	public AuctionsResponses getAuctionList() {
		
		// TODO : 시작시간이 얼마 남지 않은 순서로 정렬, 추후 필터 정렬로 수정하기
		List<AuctionsResponse> response = auctionRepository.findAllWithProductAndUser().stream()
			.map(AuctionsResponse::from)
			.sorted(Comparator.comparing(AuctionsResponse::startTime))
			.toList();

		return new AuctionsResponses(response);
	}

	@Transactional(readOnly = true)
	public AuctionResponse getAuction(long id) {
		Auction auction = auctionRepository.findByProductAndUser(id)
			.orElseThrow(() -> new AuctionException(ExceptionCode.AUCTION_NOT_FOUND));

		return AuctionResponse.from(auction);
	}

	@Transactional
	public AuctionResponse placeBid(User user, BidRequest request, long id) {
		Auction auction = auctionRepository.findByProductAndUser(id)
			.orElseThrow(() -> new AuctionException(ExceptionCode.AUCTION_NOT_FOUND));

		// TODO : 입찰 조건 검증 하기(현재가보다 낮은지, 경매 상태, 2번 연속 입찰인지 등등...)
		validateBidPrice(user, auction, request);

		Bid newBid = request.of(user, auction);
		Bid savedBid = bidRepository.save(newBid);

		auction.getBids().add(savedBid);

		auction.updateCurrentPriceAndUser(request);

		return AuctionResponse.from(auction);
	}

	private void validateBidPrice(User user, Auction auction, BidRequest request) {
		auction.validateBidPrice(user, request);
	}
}
