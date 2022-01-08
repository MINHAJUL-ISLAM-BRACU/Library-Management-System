package com.project.library_mangement_system.model;

import com.project.library_mangement_system.repository.IssueRepository;
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
public class IssueServiceTest {
    @Mock
    private IssueRepository issueRepository;
    @Mock
    private IssueService issueService;

    private Issue issue;
    private Member member;
    private List<Issue> issueList;
    @Before
    public void setUp() {
        this.issueService=new IssueService(this.issueRepository);
        Date d=new Date();
        issue=new Issue(d);
        member=new Member("Other","Khaled","Jamil","Male",d);
        issue=issueService.save(issue);
    }

    @Test
    public void testGetAll() {
        issueList=issueService.getAll();
        verify(issueRepository).findAll();
        assertNotNull(issueList);
    }

    @Test
    public void testGetAllUnreturned() {
        issueList=issueService.getAllUnreturned();
        verify(issueRepository).findByReturned(Constants.BOOK_NOT_RETURNED);
        assertNotNull(issueList);
    }

    @Test
    public void testAddNew() {
        Date d=new Date();
        Issue issue=new Issue(d);
        issueService.addNew(issue);
        verify(issueRepository).save(issue);
        assertNotNull(issue);
    }

    @Test
    public void testSave() {
        Date d=new Date();
        Issue issue=new Issue(d);
        issueService.save(issue);
        verify(issueRepository).save(issue);
        assertNotNull(issue);
    }

    @Test
    public void testGetCountByMember() {
        Long i=issueService.getCountByMember(member);
        verify(issueRepository).countByMemberAndReturned(member,Constants.BOOK_NOT_RETURNED);
        assertNotNull(i);
    }
}