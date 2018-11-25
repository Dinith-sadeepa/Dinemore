package lk.ijse.dinemore.business.custom.impl;

import lk.ijse.dinemore.business.custom.DeliverBoyBO;
import lk.ijse.dinemore.dto.DeliveryBoyDTO;
import lk.ijse.dinemore.entity.DeliveryBoy;
import lk.ijse.dinemore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class DeliveryBoyBOImpl implements DeliverBoyBO {
    private SessionFactory sessionFactory;

    public DeliveryBoyBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean addDeliveryBoy(DeliveryBoyDTO deliveryBoyDTO) throws Exception {
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            boolean isSaved = (session.save(new DeliveryBoy(deliveryBoyDTO.getDeliveryBoyName(),deliveryBoyDTO.getDeliveryBoyNIC(),deliveryBoyDTO.getDeliveryBoyAddress(),deliveryBoyDTO.getDeliveryBoyUserName(),deliveryBoyDTO.getDeliveryBoyPassword())) != null);
            session.getTransaction().commit();
            return isSaved;
        }
    }

    @Override
    public boolean deleteDeliveryBoyr(int id) throws Exception {
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            DeliveryBoy deliveryBoy = session.get(DeliveryBoy.class, id);
            session.remove(deliveryBoy);

            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public boolean updateDeliveryBoy(DeliveryBoyDTO deliveryBoyDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            DeliveryBoy deliveryBoy = session.get(DeliveryBoy.class, deliveryBoyDTO.getDeliveryBoyId());
            deliveryBoy.setDeliveryBoyName(deliveryBoyDTO.getDeliveryBoyName());
            deliveryBoy.setDeliveryBoyAddress(deliveryBoyDTO.getDeliveryBoyAddress());
            deliveryBoy.setDeliveryBoyNIC(deliveryBoyDTO.getDeliveryBoyNIC());
            deliveryBoy.setDeliveryBoyUserName(deliveryBoyDTO.getDeliveryBoyUserName());
            deliveryBoy.setDeliveryBoyPassword(deliveryBoyDTO.getDeliveryBoyPassword());
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public List<DeliveryBoyDTO> getAllDeliveryBoy() throws Exception {
        List<DeliveryBoy> list;
        List<DeliveryBoyDTO> deliveryBoyDTOS = new ArrayList<>();

        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            list = session.createCriteria(DeliveryBoy.class).list();
            session.getTransaction().commit();
        }
        for (DeliveryBoy deliveryBoy:list) {
            deliveryBoyDTOS.add(new DeliveryBoyDTO(deliveryBoy.getDeliveryBoyId(),deliveryBoy.getDeliveryBoyName(),deliveryBoy.getDeliveryBoyNIC(),deliveryBoy.getDeliveryBoyAddress(),deliveryBoy.getDeliveryBoyUserName(),deliveryBoy.getDeliveryBoyPassword()));
        }
        return deliveryBoyDTOS;
    }
}
