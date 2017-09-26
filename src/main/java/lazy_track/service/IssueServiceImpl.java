package lazy_track.service;

import lazy_track.dao.IssueDao;
import lazy_track.model.Issue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private IssueDao issueDao;

    public void setIssueDao(IssueDao issueDao) {
        this.issueDao = issueDao;
    }

    @Override
    @Transactional
    public void addIssue(Issue issue) {
        issueDao.addIssue(issue);
    }

    @Override
    @Transactional
    public void updateIssue(Issue issue) {
        issueDao.updateIssue(issue);
    }

    @Override
    @Transactional
    public void removeIssue(int id) {
        issueDao.removeIssue(id);
    }

    @Override
    @Transactional
    public Issue getIssueById(int id) {
        return issueDao.getIssueById(id);
    }

    @Override
    @Transactional
    public List<Issue> listIssues() {
        return issueDao.listIssues();
    }
}
