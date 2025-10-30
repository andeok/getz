package kr.getz.bid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.getz.bid.domain.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {

}
