package kr.getz.personal.auction.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.getz.personal.auction.domain.Auction;
import kr.getz.personal.auction.repository.AuctionRepository;
import kr.getz.personal.bid.dto.request.BidRequest;
import kr.getz.personal.global.exception.ErrorCode;
import kr.getz.personal.global.exception.custom.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuctionService {

	private final AuctionRepository auctionRepository;

	public Slice<Auction> getAuctions(Long lastId, Pageable pageable) {
		if (lastId == null) {
			return auctionRepository.findAllByOrderByIdDesc(pageable);
		}
		return auctionRepository.findByIdLessThanOrderByIdDesc(lastId, pageable);
	}

	public Auction getAuction(Long auctionId) {
		return auctionRepository.findById(auctionId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND));
	}

	@Transactional
	public void placeBid(Long auctionId, BidRequest request) {
		Auction auction = auctionRepository.findById(auctionId)
			.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND));

		Integer bidAmount = request.bidAmount();
		auction.placeBid(bidAmount);

	}
}
