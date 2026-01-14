package kr.getz.personal.auction.service;

import java.util.Objects;

import org.hibernate.PessimisticLockException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.getz.personal.auction.domain.Auction;
import kr.getz.personal.auction.domain.AuctionStatus;
import kr.getz.personal.auction.repository.AuctionRepository;
import kr.getz.personal.bid.dto.request.BidRequest;
import kr.getz.personal.bid.repository.BidRepository;
import kr.getz.personal.global.auth.dto.MemberAuth;
import kr.getz.personal.global.exception.ErrorCode;
import kr.getz.personal.global.exception.custom.BadRequestException;
import kr.getz.personal.global.exception.custom.NotFoundException;
import kr.getz.personal.member.domain.Member;
import kr.getz.personal.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuctionService {

	private final AuctionRepository auctionRepository;
	private final MemberService memberService;

	private final BidRepository bidRepository;

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
	public void placeBid(MemberAuth member, Long auctionId, BidRequest request) {

		try {
			Member author = memberService.getMemberById(member.memberId());

			Auction auction = auctionRepository.findByIdWithLock(auctionId)
				.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND));

			if (!auction.getStatus().equals(AuctionStatus.PROCEEDING)) {
				throw new BadRequestException(ErrorCode.AUCTION_NOT_PROCEEDING);
			}

			if (request.bidAmount() <= auction.getCurrentPrice()) {
				throw new BadRequestException(ErrorCode.LOWER_THAN_CURRENT_PRICE);
			}

			if (Objects.equals(auction.getSellerId(), author.getId())) {
				throw new BadRequestException(ErrorCode.CANNOT_BID_OWN_AUCTION);
			}

			auction.placeBid(request.bidAmount());
			auctionRepository.save(auction);

			bidRepository.save(request.toBid(auction, author));
		} catch (PessimisticLockException e) {
			throw new BadRequestException(ErrorCode.LOCK_FAILURE);
		}
	}
}
