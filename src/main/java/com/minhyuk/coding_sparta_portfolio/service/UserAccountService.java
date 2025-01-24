package com.minhyuk.coding_sparta_portfolio.service;

import com.minhyuk.coding_sparta_portfolio.dto.UserAccountDto.UserAccountFindAllRes;
import com.minhyuk.coding_sparta_portfolio.repository.UserAccountRepository;
import com.minhyuk.coding_sparta_portfolio.dto.UserAccountDto.UserAccountCreateReq;
import com.minhyuk.coding_sparta_portfolio.domain.UserAccount;
import com.minhyuk.coding_sparta_portfolio.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAccountService {

    private final PasswordEncoder passwordEncoder;

    private final UserAccountRepository userAccountRepository;

    public void create(UserAccountCreateReq req) {

        UserAccount oldAdmin = this.userAccountRepository.findOneByEmail(req.getEmail());
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

        this.userAccountRepository.save(entity);
    }

    public List<UserAccountFindAllRes> findAll() {
        List<UserAccount> entities = this.userAccountRepository.findAll();

        List<UserAccountFindAllRes> resList = new ArrayList<>();
        entities.forEach(i -> {
            UserAccountFindAllRes res = UserAccountFindAllRes.builder()
                                    .user(i)
                                    .build();
            resList.add(res);
        });

        return resList;
    }
}
