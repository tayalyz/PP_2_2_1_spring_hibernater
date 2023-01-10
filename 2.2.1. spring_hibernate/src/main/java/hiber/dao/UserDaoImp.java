package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void addUser(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> getListUsers() {
      TypedQuery<?> query = sessionFactory.getCurrentSession().createQuery("from User");
      return (List<User>) query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      TypedQuery<?> query = sessionFactory.getCurrentSession().createQuery("from User where car.model = :model and car.series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      return (User) query.getSingleResult();
   }
}
