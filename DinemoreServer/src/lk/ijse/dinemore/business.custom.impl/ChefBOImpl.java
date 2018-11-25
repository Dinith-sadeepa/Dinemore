package lk.ijse.dinemore.business.custom.impl;

import lk.ijse.dinemore.business.custom.ChefBO;
import lk.ijse.dinemore.dto.ChefDTO;
import lk.ijse.dinemore.entity.Chef;
import lk.ijse.dinemore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ChefBOImpl implements ChefBO {
    private SessionFactory sessionFactory;

    public ChefBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean addChef(ChefDTO chefDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            boolean isSaved = (session.save(new Chef(chefDTO.getChefName(), chefDTO.getChefNIC(), chefDTO.getChefAddress(), chefDTO.getChefUserName(), chefDTO.getChefPassword())) != null);
            session.getTransaction().commit();
            return isSaved;
        }
    }

    @Override
    public boolean deleteChef(int id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            Chef chef = session.get(Chef.class, id);
            session.delete(chef);

            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public boolean updateChef(ChefDTO chefDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            Chef chef = session.get(Chef.class, chefDTO.getChefId());
            chef.setChefName(chefDTO.getChefName());
            chef.setChefAddress(chefDTO.getChefAddress());
            chef.setChefNIC(chefDTO.getChefNIC());
            chef.setChefUserName(chefDTO.getChefUserName());
            chef.setChefPassword(chefDTO.getChefPassword());

            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public List<ChefDTO> getAllChef() throws Exception {
        List<ChefDTO> chefDTOArrayList = new ArrayList<>();
        List<Chef> list;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            list = session.createCriteria(Chef.class).list();
            session.getTransaction().commit();
        }
        for (Chef chef : list) {
            chefDTOArrayList.add(new ChefDTO(chef.getChefId(), chef.getChefName(), chef.getChefNIC(), chef.getChefAddress(), chef.getChefUserName(), chef.getChefPassword()));
        }
        return chefDTOArrayList;
    }
}
