package com.minhyuk.coding_sparta_portfolio.repository;

import com.minhyuk.coding_sparta_portfolio.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findOneByEmail(String email);
}
