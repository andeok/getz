package kr.getz.depth.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.getz.depth.user.domain.User;

public interface UsersRepository extends JpaRepository<User, Long> {
}
