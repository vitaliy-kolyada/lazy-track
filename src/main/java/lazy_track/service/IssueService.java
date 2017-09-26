package lazy_track.service;

import lazy_track.model.Issue;

import java.util.List;

public interface IssueService {
    void addIssue(Issue issue);

    void updateIssue(Issue issue);

    void removeIssue(int id);

    Issue getIssueById(int id);

    List<Issue> listIssues();
}
