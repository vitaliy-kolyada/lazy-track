package lazy_track.dao;

import lazy_track.model.Issue;

import java.util.List;

public interface IssueDao {
    void addIssue(Issue issue);

    void updateIssue(Issue issue);

    void removeIssue(int id);

    Issue getIssueById(int id);

    List<Issue> listIssues();
}
