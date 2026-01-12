package kr.getz.personal.auction.repository;

import kr.getz.personal.auction.domain.Auction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    Slice<Auction> findByIdLessThanOrderByIdDesc(Long lastId, Pageable pageable);

    Slice<Auction> findAllByOrderByIdDesc(Pageable pageable);
}
