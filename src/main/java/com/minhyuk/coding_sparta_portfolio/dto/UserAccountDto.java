package com.minhyuk.coding_sparta_portfolio.dto;

import com.minhyuk.coding_sparta_portfolio.domain.UserAccount;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

public class UserAccountDto {

    @Data
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class UserAccountCreateReq {
        @NotBlank(message = "Email required")
        @Email(message = "Invalid email format")
        protected String email;

        @NotBlank(message = "Password required")
        protected String password;

        @NotBlank(message = "Name required")
        protected String name;
    }

    @Data
   	@NoArgsConstructor(access = AccessLevel.PROTECTED)
   	public static class UserAccountFindAllRes {
   		protected Long id;
        protected String email;
   		protected String name;

   		@Builder
   		public UserAccountFindAllRes(UserAccount user) {
               if(ObjectUtils.isNotEmpty(user)) {
                   this.id = user.getId();
                   this.email = user.getEmail();
                   this.name = user.getName();
               }
   		}
   	}

}