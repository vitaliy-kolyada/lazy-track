package lazy_track.service;

import lazy_track.dao.IssueDao;
import lazy_track.model.Issue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {
    private IssueDao issueDao;

    public void setIssueDao(IssueDao issueDao) {
        this.issueDao = issueDao;
    }

    @Override
    public void add(Issue issue) {
        issueDao.add(issue);
    }

    @Override
    public void update(Issue issue) {
        issueDao.update(issue);
    }

    @Override
    public void remove(int id) {
        issueDao.remove(id);
    }

    @Override
    public Issue get(int id) {
        return issueDao.get(id);
    }

    @Override
    public List<Issue> list() {
        return issueDao.list();
    }
}
