package com.minhyuk.coding_sparta_portfolio.service;

import com.minhyuk.coding_sparta_portfolio.repository.UserAccountRepository;
import com.minhyuk.coding_sparta_portfolio.dto.UserAccountDto.UserAccountCreateReq;
import com.minhyuk.coding_sparta_portfolio.domain.UserAccount;
import com.minhyuk.coding_sparta_portfolio.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.ObjectUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAccountService {

    private final PasswordEncoder passwordEncoder;

    private final UserAccountRepository accountRepository;

    public void create(UserAccountCreateReq req) {

        UserAccount oldAdmin = this.accountRepository.findOneByEmail(req.getEmail());
        if(ObjectUtils.isNotEmpty(oldAdmin)) {
            throw new IllegalArgumentException("User duplicated");
        }

        String encodedPassword = passwordEncoder.encode(req.getPassword());

        UserAccount entity = UserAccount.builder()
                                .email(req.getEmail())
                                .password(encodedPassword)
                                .name(req.getName())
                                .status(UserStatus.NEW)
                                .build();

        this.accountRepository.save(entity);
    }
}
