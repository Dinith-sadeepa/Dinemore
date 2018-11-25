package lk.ijse.dinemore.business.custom;

import lk.ijse.dinemore.business.SuperBO;
import lk.ijse.dinemore.dto.MealDTO;

import java.util.List;

public interface MealBO extends SuperBO {
    public boolean addMeal(MealDTO mealDTO) throws Exception;

    public boolean deleteMeal(int id) throws Exception;

    public boolean updateMeal(MealDTO mealDTO) throws Exception;

    public List<MealDTO> getAllMeal() throws Exception;
}
