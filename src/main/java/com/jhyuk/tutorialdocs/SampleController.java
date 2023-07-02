package com.jhyuk.tutorialdocs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SampleController {

    private final Map<Long, Member> memberMap = new HashMap<>();
    private Long keyId = 1L;

    @GetMapping("member")
    public ResponseEntity<Map<Long, Member>> getMember() {
        return ResponseEntity.ok().body(memberMap);
    }

    @GetMapping("member/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable Long memberId) {
        Member member = memberMap.get(memberId);
        if(member == null){
            return  ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().body(member);
    }

    @PostMapping("member")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        memberMap.put(keyId++, member);
        return ResponseEntity.ok().body(member);
    }

    @PutMapping("member/{memberId}")
    public ResponseEntity<Member> editMember(@PathVariable Long memberId,
                                             @RequestBody Member member) {
        memberMap.put(memberId, member);
        return ResponseEntity.ok().body(member);
    }

    @DeleteMapping("member/{memberId}")
    public ResponseEntity<Member> deleteMember(@PathVariable Long memberId) {
        Member member = memberMap.get(memberId);
        memberMap.remove(memberId);
        return ResponseEntity.ok().body(member);
    }

}
