package com.minhyuk.coding_sparta_portfolio.api;

import com.minhyuk.coding_sparta_portfolio.dto.UserAccountDto.UserAccountCreateReq;
import com.minhyuk.coding_sparta_portfolio.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserAccountApi {

    private final UserAccountService userAccountService;

    @GetMapping("test")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "테스트")
    public void log() {
    }

    @PostMapping("/user/signup")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "user 생성")
    public void create(@RequestBody @Validated final UserAccountCreateReq req) {
        this.userAccountService.create(req);
    }

/*    @GetMapping("admin")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "admin 조회")
    public List<AdminAccountRes> findAll() {
        List<AdminAccountRes> resList = this.adminService.findAll();
        return resList;
    }*/
}
