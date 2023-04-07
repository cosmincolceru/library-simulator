package repository;

import models.Member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemberRepository {
    private Member[] storedMembers = new Member[0];

    public boolean add(Member member) {
        try {
            List<Member> arrList = new ArrayList<>(Arrays.asList(storedMembers));
            arrList.add(member);
            storedMembers = arrList.toArray(storedMembers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Member[] getAll() { return this.storedMembers; }

    public boolean remove(Member member) {
        try {
            List<Member> arrList = new ArrayList<>(Arrays.asList(storedMembers));
            arrList.remove(member);
            storedMembers = arrList.toArray(new Member[arrList.size()]);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;

    }

    public Member[] getMembersWithMostRented() {
        Member memberMax;
        int mostRented = 0;
        for (Member member : storedMembers) {
            if (member.getItemsRented().size() > mostRented) {
                mostRented = member.getItemsRented().size();
            }
        }
        List<Member> members = new ArrayList<>();
        for (Member member : storedMembers) {
            if (member.getItemsRented().size() == mostRented) {
                members.add(member);
            }
        }
        return members.toArray(new Member[members.size()]);
    }
}
