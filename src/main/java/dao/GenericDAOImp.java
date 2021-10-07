package main.java.dao;

/*
 * @autor Fábio R data 27 de Maio de 2012 organização IFRR - SIGECE - XTADS
 *
 */
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class GenericDAOImp<T> {

    private EntityManager entityManager;
    private final Class<T> persistentClass;

    public GenericDAOImp(Class<T> classe) {
        persistentClass = classe;
        entityManager = new JPAMF().getEntityManager();
    }

    public Class<T> getPersintentClass() {
        return this.persistentClass;
    }

    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }

    protected EntityManager getEntityManager() {
        if (this.entityManager == null) {
            throw new IllegalStateException("Erro onde:");
        }
        return entityManager;
    }

    public T save(T entity) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(entity);
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            getEntityManager().getTransaction().rollback();
        }
        return entity;
    }

    public T update(T entity) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(entity);
            getEntityManager().getTransaction().commit();
 
        } catch (Exception ex) {
            getEntityManager().getTransaction().rollback();
        }
        return entity;
    }

    public void delete(T entity) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(entity);
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            getEntityManager().getTransaction().rollback();
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        String querySelect = "SELECT obj FROM " + persistentClass.getSimpleName() + " obj";
        Query query = getEntityManager().createQuery(querySelect);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> getAllByCodigo(String field, Integer codigo) {
        String querySelect = "SELECT obj FROM " + persistentClass.getSimpleName() + " obj WHERE (" + field + ") = ('" + codigo + "')";
        Query query = getEntityManager().createQuery(querySelect);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> getAllByCodigoOrdem(String field, Integer codigo) {
        String querySelect = "SELECT obj AS data FROM " + persistentClass.getSimpleName() + " obj WHERE (" + field + ") = ('" + codigo + "')";
        Query query = getEntityManager().createQuery(querySelect);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> getAllByCodigox(String field, Integer codigo) {
        String querySelect = "SELECT obj FROM " + persistentClass.getSimpleName() + " obj WHERE (" + field + ") = ('" + codigo + "')";
        Query query = getEntityManager().createQuery(querySelect);
        return query.getResultList();
    }

//    @SuppressWarnings("unchecked")
//    public List<T> getLogin(Usuario user) {
//        String querySelect = "SELECT obj FROM " + persistentClass.getSimpleName() + " obj WHERE login = (" + user.getLogin() + ") and senha = ('" + user.getSenha() + "')";
//        Query query = getEntityManager().createQuery(querySelect);
//        return query.getResultList();
//    }
    @SuppressWarnings("unchecked")
    public List<T> getAllByName(String field, String name) {
        String querySelect = "SELECT obj FROM " + persistentClass.getSimpleName() + " obj WHERE upper(" + field + ") like upper('%" + name + "%')";
        Query query = getEntityManager().createQuery(querySelect);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> getAllOrder(String field) {
        String querySelect = "SELECT obj FROM " + persistentClass.getSimpleName() + " obj ORDER BY " + field;
        Query query = getEntityManager().createQuery(querySelect);
        return query.getResultList();
    }

    public int count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(persistentClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
