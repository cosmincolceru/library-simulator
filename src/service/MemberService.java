package service;

import models.Member;
import repository.MemberRepository;
import util.ID;


import java.util.ArrayList;
import java.util.Set;

public class MemberService {
    private MemberRepository memberRepository;

    private static MemberService instance;


    public static MemberService getInstance() {
        if (instance == null) {
            instance = new MemberService();
        }
        return instance;
    }

    ID<Member> id = ID.getInstance();

    private MemberService() { this.memberRepository = new MemberRepository(); }

    public boolean addMember(String firstName, String lastName, String CNP, String birthdate) {
        int memberId = id.getNextId(new Member());

        Member member = new Member(firstName, lastName, CNP, birthdate, memberId);
        return this.memberRepository.add(member);
    }

    public ArrayList<Member> getAllMembers() { return this.memberRepository.getAll(); }

    public boolean removeMember(int memberId) {
        ArrayList<Member> members = memberRepository.getAll();
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return memberRepository.remove(member);
            }
        }
        return false;
    }

    public Set<Member> getMembersWithMostRented() {
        return memberRepository.getMembersWithMostRented();
    }
}
