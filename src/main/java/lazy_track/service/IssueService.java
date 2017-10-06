package lazy_track.service;

import lazy_track.model.Company;
import lazy_track.model.Issue;

import java.util.List;

public interface IssueService {
    Issue findById(long id);

    void save(Issue issue);

    void update(Issue issue);

    void deleteById(long id);

    List<Issue> findAll();

    boolean isExist(Issue issue);

}
