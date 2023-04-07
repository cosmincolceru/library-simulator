package controller;

import models.Member;
import service.MemberService;

public class MemberController {
    private MemberService memberService;

    public MemberController() {this.memberService = new MemberService(); }

    public boolean addMember(String firstName, String lastName, String CNP, String birthdate) {
        firstName = capitalizeString(firstName);
        lastName = capitalizeString(lastName);

        return memberService.addMember(firstName, lastName, CNP, birthdate);
    }

    public Member[] getAllMembers() { return memberService.getAllMembers(); }

    private String capitalizeString (String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public int memberIndex(int memberId) {
        Member[] members = getAllMembers();
        for (int i = 0; i < members.length; i++) {
            if (members[i].getMemberId() == memberId) {
                return i;
            }
        }
        System.out.println("Member ID doesn't exist.");
        return -1;
    }

    public boolean removeMember(int memberId) {
        return memberService.removeMember(memberId);
    }

    public Member[] getMembersWithMostRented() {
        return memberService.getMembersWithMostRented();
    }

}
