package org.example.HQL_JOIN;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HqlJoinDao {

    private static SessionFactory sessionFactory = null;

    static {
        Configuration configuration = new Configuration();
        sessionFactory = configuration.buildSessionFactory();
    }

    public void addEmployee(Employee employee){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void getAddress(int addressId){
        try(Session session = sessionFactory.openSession()){

            String HQL = "From Address address LEFT OUTER JOIN Fetch address.employee where address.id =:addressId";
            Query<Address> query = session.createQuery(HQL, Address.class);
            query.setParameter("addressId", addressId);
            Address address = query.uniqueResult();
            System.out.println(address);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
