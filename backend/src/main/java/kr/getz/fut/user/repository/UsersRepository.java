package kr.getz.fut.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.getz.fut.user.domain.User;

public interface UsersRepository extends JpaRepository<User, Long> {
}
