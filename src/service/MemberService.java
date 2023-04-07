package service;

import models.Member;
import repository.MemberRepository;

public class MemberService {
    private MemberRepository memberRepository;

    public MemberService() { this.memberRepository = new MemberRepository(); }

    public boolean addMember(String firstName, String lastName, String CNP, String birthdate) {
        Member.numberOfMembers += 1;
        int memberId = Member.numberOfMembers;

        Member member = new Member(firstName, lastName, CNP, birthdate, memberId);
        return this.memberRepository.add(member);
    }

    public Member[] getAllMembers() { return this.memberRepository.getAll(); }

    public boolean removeMember(int memberId) {
        Member[] members = memberRepository.getAll();
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return memberRepository.remove(member);
            }
        }
        return false;
    }

    public Member[] getMembersWithMostRented() {
        return memberRepository.getMembersWithMostRented();
    }
}
