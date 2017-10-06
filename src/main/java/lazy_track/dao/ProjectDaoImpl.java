package lazy_track.dao;

import lazy_track.model.Project;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("projectDao")
@Transactional

public class ProjectDaoImpl extends AbstractDao<Long, Project> implements ProjectDao {
    @Override
    public void update(Project project) {
        getSession().update(project);
    }

    @Override
    public Project findById(long id) {
        return getByKey(id);
    }

    @Override
    public void save(Project project) {
        persist(project);
    }

    @Override
    public void deleteById(long id) {
        delete(findById(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Project> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<Project>) criteria.list();
    }

    @Override
    public Project findByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (Project) criteria.uniqueResult();
    }
}
