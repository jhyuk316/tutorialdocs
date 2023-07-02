package com.jhyuk.tutorialdocs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Member Controller", description = "Member Controller api 입니다.")
@RestController
public class SampleController {

    private final Map<Long, Member> memberMap = new HashMap<>();
    private Long keyId = 1L;

    @Operation(summary = "전체 회원 조회 요청", description = "모든 회원 정보를 조회 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = Member.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("member")
    public ResponseEntity<Map<Long, Member>> getMember() {
        return ResponseEntity.ok().body(memberMap);
    }

    @Operation(summary = "특정 회원 조회 요청", description = "특정 회원 정보를 조회 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = Member.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("member/{memberId}")
    public ResponseEntity<Member> getMember(@Parameter(description = "맴버 ID") @PathVariable Long memberId) {
        Member member = memberMap.get(memberId);
        if(member == null){
            return  ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().body(member);
    }

    @Operation(summary = "회원 추가 요청", description = "회원 정보를 추가 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = Member.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("member")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        memberMap.put(keyId++, member);
        return ResponseEntity.ok().body(member);
    }

    @Operation(summary = "특정 회원 수정 요청", description = "특정 회원 정보를 수정 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = Member.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping("member/{memberId}")
    public ResponseEntity<Member> editMember(@PathVariable Long memberId,
                                             @RequestBody Member member) {
        memberMap.put(memberId, member);
        return ResponseEntity.ok().body(member);
    }

    @Operation(summary = "회원 탈퇴 요청", description = "회원 정보가 삭제됩니다.", tags = { "Member Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = Member.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("member/{memberId}")
    public ResponseEntity<Member> deleteMember(@PathVariable Long memberId) {
        Member member = memberMap.get(memberId);
        memberMap.remove(memberId);
        return ResponseEntity.ok().body(member);
    }

}
