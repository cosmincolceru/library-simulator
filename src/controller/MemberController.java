package controller;

import models.Member;
import service.MemberService;

import java.util.ArrayList;
import java.util.Set;

public class MemberController {
    private MemberService memberService;

    public MemberController() {this.memberService = MemberService.getInstance(); }

    public boolean addMember(String firstName, String lastName, String CNP, String birthdate) {
        firstName = capitalizeString(firstName);
        lastName = capitalizeString(lastName);

        return memberService.addMember(firstName, lastName, CNP, birthdate);
    }

    public ArrayList<Member> getAllMembers() { return memberService.getAllMembers(); }

    private String capitalizeString (String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public int memberIndex(int memberId) {
        ArrayList<Member> members = getAllMembers();
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMemberId() == memberId) {
                return i;
            }
        }
        System.out.println("Member ID doesn't exist.");
        return -1;
    }

    public boolean removeMember(int memberId) {
        return memberService.removeMember(memberId);
    }

    public Set<Member> getMembersWithMostRented() {
        return memberService.getMembersWithMostRented();
    }

}
