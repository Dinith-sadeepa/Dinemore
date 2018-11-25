package lk.ijse.dinemore.business.custom.impl;

import lk.ijse.dinemore.business.custom.LogInBO;
import lk.ijse.dinemore.dto.LogInDTO;
import lk.ijse.dinemore.entity.LogIn;
import lk.ijse.dinemore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class LogInBOImpl implements LogInBO {
    private SessionFactory sessionFactory;
    public LogInBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void addLogIn(LogInDTO logInDTO) throws Exception {
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            session.save(new LogIn(logInDTO.getUserId(),logInDTO.getLoginTime()));
            session.getTransaction().commit();
        }
    }

    @Override
    public List<LogInDTO> getAllLogin() throws Exception {
        List<LogInDTO> logInDTOS = new ArrayList<>();
        List<LogIn> logins;
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            logins = session.createCriteria(LogIn.class).list();
            session.getTransaction().commit();
        }
        for (LogIn logIn:logins) {
            logInDTOS.add(new LogInDTO(logIn.getLogInId(),logIn.getUserId(),logIn.getLoginTime()));
        }
        return logInDTOS;
    }
}
