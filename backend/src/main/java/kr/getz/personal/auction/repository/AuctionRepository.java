package kr.getz.personal.auction.repository;

import java.util.Optional;

import jakarta.persistence.LockModeType;
import kr.getz.personal.auction.domain.Auction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    Slice<Auction> findByIdLessThanOrderByIdDesc(Long lastId, Pageable pageable);

    Slice<Auction> findAllByOrderByIdDesc(Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Auction a WHERE a.id = :auctionId")
    Optional<Auction> findByIdWithLock(Long auctionId);
}
