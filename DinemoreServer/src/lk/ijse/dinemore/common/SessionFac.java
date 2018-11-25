package lk.ijse.dinemore.common;

import lk.ijse.dinemore.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class SessionFac {
    private static SessionFactory sessionFactory;

    public static SessionFactory getInstance(){
        if(sessionFactory == null){
            sessionFactory = HibernateUtil.getSessionFactory();
        }
        return sessionFactory;
    }
}
