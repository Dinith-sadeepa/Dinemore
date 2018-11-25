package lk.ijse.dinemore.util;

import lk.ijse.dinemore.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .loadProperties("application.properties").build();

        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Chef.class)
                .addAnnotatedClass(DeliveryBoy.class)
                .addAnnotatedClass(Meal.class)
                .addAnnotatedClass(Operator.class)
                .addAnnotatedClass(OrderDetail.class)
                .addAnnotatedClass(Orders.class)
                .addAnnotatedClass(LogIn.class)
                .addAnnotatedClass(Cooking.class)
                .addAnnotatedClass(Delivery.class)
                .buildMetadata();
        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
