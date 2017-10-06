package lazy_track.dao;

import lazy_track.model.Issue;

import java.util.List;

public interface IssueDao {
    void update(Issue issue);

    Issue findById(long id);

    void save(Issue issue);

    void deleteById(long id);

    List<Issue> findAll();

    Issue findByName(String name);
}