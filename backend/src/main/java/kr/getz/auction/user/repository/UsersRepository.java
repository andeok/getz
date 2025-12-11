package kr.getz.auction.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.getz.auction.user.domain.User;

public interface UsersRepository extends JpaRepository<User, Long> {
}
