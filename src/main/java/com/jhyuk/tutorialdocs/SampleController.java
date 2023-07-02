package com.jhyuk.tutorialdocs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SampleController {

    private final Map<Long, Member> memberMap = new HashMap<>();
    private Long keyId = 1L;

    @GetMapping("members")
    public ResponseEntity<Map<Long, Member>> getMember() {
        return ResponseEntity.ok().body(memberMap);
    }

    @GetMapping("members/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable Long memberId) {
        Member member = memberMap.get(memberId);
        if(member == null){
            return  ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().body(member);
    }

    @PostMapping("members")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        memberMap.put(keyId++, member);
        return ResponseEntity.ok().body(member);
    }

    @PutMapping("members/{memberId}")
    public ResponseEntity<Member> editMember(@PathVariable Long memberId,
                                             @RequestBody Member member) {
        memberMap.put(memberId, member);
        return ResponseEntity.ok().body(member);
    }

    @DeleteMapping("members/{memberId}")
    public ResponseEntity<Member> deleteMember(@PathVariable Long memberId) {
        Member member = memberMap.get(memberId);
        memberMap.remove(memberId);
        return ResponseEntity.ok().body(member);
    }

}
