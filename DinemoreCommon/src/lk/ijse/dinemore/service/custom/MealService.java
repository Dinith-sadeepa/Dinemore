package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.MealDTO;
import lk.ijse.dinemore.service.SuperService;

import java.util.List;

public interface MealService extends SuperService {
    public boolean addMeal(MealDTO mealDTO) throws Exception;

    public boolean deleteMeal(int id) throws Exception;

    public boolean updateMeal(MealDTO mealDTO) throws Exception;

    public List<MealDTO> getAllMeal() throws Exception;
}
