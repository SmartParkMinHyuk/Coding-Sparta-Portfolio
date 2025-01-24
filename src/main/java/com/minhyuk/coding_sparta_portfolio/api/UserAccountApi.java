package com.minhyuk.coding_sparta_portfolio.api;

import com.minhyuk.coding_sparta_portfolio.dto.UserAccountDto.UserAccountCreateReq;
import com.minhyuk.coding_sparta_portfolio.dto.UserAccountDto.UserAccountFindAllRes;
import com.minhyuk.coding_sparta_portfolio.dto.UserAccountDto.UserAccountUpdateReq;
import com.minhyuk.coding_sparta_portfolio.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserAccountApi {

    private final UserAccountService userAccountService;

    @GetMapping("test")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "테스트")
    public void log() {
    }

    @PostMapping("user/signup")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "user 생성")
    public void create(@RequestBody final @Validated UserAccountCreateReq req) {
        this.userAccountService.create(req);
    }

    @GetMapping("user/findAll")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "User 조회")
    public List<UserAccountFindAllRes> findAll() {
        List<UserAccountFindAllRes> resList = this.userAccountService.findAll();
        return resList;
    }

    @PutMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "user 수정")
    public void update (
        @PathVariable(name = "id") Long userId,
        @RequestBody final @Validated UserAccountUpdateReq req
    ) {
        this.userAccountService.update(userId, req);
    }

    @DeleteMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "user 삭제")
    public void delete(@PathVariable(name = "id") Long adminId) {
        this.userAccountService.delete(adminId);
    }
}
