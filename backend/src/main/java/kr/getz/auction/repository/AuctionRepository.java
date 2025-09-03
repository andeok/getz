package kr.getz.auction.repository;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.getz.auction.domain.Auction;
import kr.getz.auction.dto.response.AuctionsResponse;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

	@Query("SELECT DISTINCT a FROM Auction a JOIN FETCH a.product p JOIN FETCH p.user LEFT JOIN FETCH a.bids b WHERE a.id = :id")
	Optional<Auction> findById(@Param("id") long id);

	@Query("SELECT a FROM Auction a WHERE a.id = :id")
	Optional<Auction> findByIdForUpdate(@Param("id") long id);

	@Query("SELECT auction FROM Auction auction JOIN FETCH auction.product product JOIN FETCH product.user")
	Page<Auction> findAll(Pageable pageable);
}
