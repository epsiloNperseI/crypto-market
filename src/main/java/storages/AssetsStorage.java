package storages;

import com.criptomarcet.demo.HibernateUtil;
import dto.Asset;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssetsStorage {
//TODO: Common functions move to abstractions;
    public List<Long> insert(List<Asset> assets) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        List<Long> ids = new ArrayList<>();
        assets.forEach(asset -> ids.add((Long) session.save(asset)));

        tx.commit();
        session.close();

        return ids;
    }

    public void update(List<Asset> assets) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        assets.forEach(session::update);

        tx.commit();
        session.close();
    }

    public List<Asset> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Asset> criteriaQuery = builder.createQuery(Asset.class);
        Root<Asset> root = criteriaQuery.from(Asset.class);
        criteriaQuery.select(root);

        Query query = session.createQuery(criteriaQuery);

        tx.commit();
        session.close();
        return (List<Asset>) query.getResultList();
    }

    public void batchInsert() {

    }
}
