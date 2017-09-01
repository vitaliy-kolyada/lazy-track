package lazy_track.dao;

import lazy_track.model.Issue;

import java.util.List;

public interface IssueDao {
    void add(Issue issue);

    void update(Issue issue);

    void remove(int id);

    Issue get(int id);

    List<Issue> list();
}
