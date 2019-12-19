package treeservice.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import treeservice.domain.CalcRequestResult;

@Repository
@Transactional
public class CalcRequestRepositoryImpl implements CalcRequestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(CalcRequestResult result) {
	entityManager.persist(result);
    }

    @Override
    public List<CalcRequestResult> getAll() {
	return null;
    }

}
