package lk.ijse.dinemore.business.custom.impl;

import lk.ijse.dinemore.business.custom.MealBO;
import lk.ijse.dinemore.dto.MealDTO;
import lk.ijse.dinemore.entity.Meal;
import lk.ijse.dinemore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class MealBOImpl implements MealBO {
    private SessionFactory sessionFactory;

    public MealBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean addMeal(MealDTO mealDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            boolean isSaved = (session.save(new Meal(mealDTO.getMealName(), mealDTO.getMealCategory(), mealDTO.getMealUnitPrice())) != null);
            session.getTransaction().commit();
            return isSaved;
        }
    }

    @Override
    public boolean deleteMeal(int id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Meal meal = session.get(Meal.class, id);
            session.remove(meal);

            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public boolean updateMeal(MealDTO mealDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Meal meal = session.get(Meal.class, mealDTO.getMealId());
            meal.setMealName(mealDTO.getMealName());
            meal.setMealCategory(mealDTO.getMealCategory());
            meal.setMealUnitPrice(mealDTO.getMealUnitPrice());
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public List<MealDTO> getAllMeal() throws Exception {
        List<MealDTO> mealDTOS = new ArrayList<>();
        List<Meal> meals;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            meals = session.createCriteria(Meal.class).list();
            session.getTransaction().commit();
        }
        for (Meal meal : meals) {
            mealDTOS.add(new MealDTO(meal.getMealId(), meal.getMealName(), meal.getMealCategory(), meal.getMealUnitPrice()));
        }
        return mealDTOS;
    }
}
