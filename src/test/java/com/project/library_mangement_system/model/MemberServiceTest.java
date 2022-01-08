package com.project.library_mangement_system.model;

import com.project.library_mangement_system.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MemberService memberService;
    private Member member;
    private List<Member> memberList;
    @Before
    public void setUp() {
        this.memberService = new MemberService(this.memberRepository);
        Date d=new Date();
        member = new Member("Instructor","Mohamad","niamul","Male",d);
        member = memberService.save(member);
    }

    @Test
    public void testGetTotalCount() {
        Long i=memberService.getTotalCount();
        verify(memberRepository).count();
        assertNotNull(i);
    }

    @Test
    public void testGetInstructorsCount() {
        Long i=memberService.getInstructorsCount();
        verify(memberRepository).countByType("Instructor");
        assertNotNull(i);
    }

    @Test
    public void testGetStudentsCount() {
        Date d=new Date();
        member= new Member("Student","Mohamada","nasir","Male",d);
        Member member_student=memberService.save(member);
        Long i=memberService.getStudentsCount();
        verify(memberRepository).countByType("Student");
        assertNotNull(i);
    }

    @Test
    public void testGetAll() {
        memberList=memberService.getAll();
        verify(memberRepository).findAllByOrderByFirstNameAscMiddleNameAscLastNameAsc();
        assertNotNull(memberList);
    }

    @Test
    public void testDelete() {
        memberService.delete(member);
        verify(memberRepository).delete(member);
        assertNull(member);
    }

    @Test
    public void testTestDelete() {
        memberService.delete(1L);
        verify(memberRepository).deleteById(1L);
        assertNull(member);
    }

    @Test
    public void testAddNew() {
        Date d= new Date();
        Member member=new Member("Other","Mirza","Nabil","Male",d);
        memberService.addNew(member);
        verify(memberRepository).save(member);
        assertNotNull(member);
    }

    @Test
    public void testSave() {
        Date d= new Date();
        Member member=new Member("Other","Abdul","Khalek","Male",d);
        memberService.save(member);
        verify(memberRepository).save(member);
        assertNotNull(member);
    }
}