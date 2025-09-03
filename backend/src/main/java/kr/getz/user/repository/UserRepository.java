package kr.getz.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.getz.global.exception.AuctionException;
import kr.getz.global.exception.ExceptionCode;
import kr.getz.user.domain.Email;
import kr.getz.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	default User getUserById(Long id) {
		return findById(id).orElseThrow(() -> new AuctionException(ExceptionCode.USER_NOT_FOUND));
	}

	Optional<User> findByEmail(Email email);
}
