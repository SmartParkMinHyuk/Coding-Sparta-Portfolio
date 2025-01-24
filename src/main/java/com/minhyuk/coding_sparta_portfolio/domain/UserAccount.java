package com.minhyuk.coding_sparta_portfolio.domain;

import com.minhyuk.coding_sparta_portfolio.dto.UserAccountDto.UserAccountUpdateReq;
import com.minhyuk.coding_sparta_portfolio.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "admin_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    @NotNull(message = "Email required")
	private String email;

    @Column(name = "password")
    @NotNull(message = "Password required")
	private String password;

    @Column(name = "name")
    @NotNull(message = "Name required")
	private String name;

    @Column(name = "status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "deleted_at")
	private Date deletedAt;

    @Builder
	public UserAccount(
        String email,
        String password,
        String name,
        UserStatus status
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.status = status;
	}

    public void update(UserAccountUpdateReq req, String encodedPassword) {
        if(ObjectUtils.isNotEmpty(req)) {
            this.name = req.getName();
            this.email = req.getEmail();
            this.password = encodedPassword;
        }
    }
}
