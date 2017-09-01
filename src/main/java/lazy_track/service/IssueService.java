package lazy_track.service;

import lazy_track.model.Issue;

import java.util.List;

public interface IssueService {
    void add(Issue issue);

    void update(Issue issue);

    void remove(int id);

    Issue get(int id);

    List<Issue> list();
}
