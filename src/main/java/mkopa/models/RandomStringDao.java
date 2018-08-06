package mkopa.models;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class RandomStringDao {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public void save(RandomString randomString) {
        getSession().save(randomString);
        return;
    }

    public List<RandomString> getAll() {
        String query = "FROM randoms r ORDER BY r.id DESC";
        List<RandomString> randomStrings = getSession().createQuery(query).setMaxResults(100).list();
        return randomStrings;
    }
}