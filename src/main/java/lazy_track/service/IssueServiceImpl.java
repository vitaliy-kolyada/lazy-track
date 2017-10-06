package lazy_track.service;

import lazy_track.dao.IssueDao;
import lazy_track.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("issueService")
public class IssueServiceImpl implements IssueService {

    private IssueDao issueDao;

    @Autowired
    public void setIssueDao(IssueDao issueDao) {
        this.issueDao = issueDao;
    }

    public List<Issue> findAll() {
        return issueDao.findAll();
    }

    public Issue findById(long id) {
        return issueDao.findById(id);
    }

    public void save(Issue issue) {
        issueDao.save(issue);
    }

    public void update(Issue issue) {
        issueDao.update(issue);
    }

    public void deleteById(long id) {
        issueDao.deleteById(id);
    }

    public boolean isExist(Issue issue) {
        return issueDao.findByName(issue.getName()) != null;
    }
}
