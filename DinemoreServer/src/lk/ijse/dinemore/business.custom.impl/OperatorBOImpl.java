package lk.ijse.dinemore.business.custom.impl;

import lk.ijse.dinemore.business.custom.OperatorBO;
import lk.ijse.dinemore.dto.OperatorDTO;
import lk.ijse.dinemore.entity.Operator;
import lk.ijse.dinemore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class OperatorBOImpl implements OperatorBO {
    private SessionFactory sessionFactory;

    public OperatorBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean addOperator(OperatorDTO operatorDTO) throws Exception {
        boolean save1 = false;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            save1 = (session.save(new Operator(operatorDTO.getOperatorName(), operatorDTO.getOperatorNIC(), operatorDTO.getOperatorAddress(), operatorDTO.getOperatorUserName(), operatorDTO.getOperatorPassword())) != null);
            session.getTransaction().commit();
        }
        return save1;
    }

    @Override
    public boolean deleteOperator(int id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Operator operator = session.get(Operator.class, id);
            session.remove(operator);

            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public boolean updateOperator(OperatorDTO operatorDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Operator operator = session.get(Operator.class, operatorDTO.getOperatorId());
            operator.setOperatorName(operatorDTO.getOperatorName());
            operator.setOperatorNIC(operatorDTO.getOperatorNIC());
            operator.setOperatorAddress(operatorDTO.getOperatorAddress());
            operator.setOperatorUserName(operatorDTO.getOperatorUserName());
            operator.setOperatorPassword(operatorDTO.getOperatorPassword());
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public List<OperatorDTO> getAllOperator() throws Exception {
        List<OperatorDTO> operatorDTOS = new ArrayList<>();
        List<Operator> list;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            list = session.createCriteria(Operator.class).list();
            session.getTransaction().commit();
        }
        for (Operator operator : list) {
            operatorDTOS.add(new OperatorDTO(operator.getOperatorId(), operator.getOperatorName(), operator.getOperatorNIC(), operator.getOperatorAddress(), operator.getOperatorUserName(), operator.getOperatorPassword()));
        }
        return operatorDTOS;
    }
}
