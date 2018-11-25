package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.business.BOFactory;
import lk.ijse.dinemore.business.custom.MealBO;
import lk.ijse.dinemore.dto.MealDTO;
import lk.ijse.dinemore.service.custom.MealService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MealServiceImpl extends UnicastRemoteObject implements MealService {
    private MealBO mealBO;
    public MealServiceImpl() throws RemoteException {
        mealBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.MEAL);
    }

    @Override
    public boolean addMeal(MealDTO mealDTO) throws Exception {
        return mealBO.addMeal(mealDTO);
    }

    @Override
    public boolean deleteMeal(int id) throws Exception {
        return mealBO.deleteMeal(id);
    }

    @Override
    public boolean updateMeal(MealDTO mealDTO) throws Exception {
        return mealBO.updateMeal(mealDTO);
    }

    @Override
    public List<MealDTO> getAllMeal() throws Exception {
        return mealBO.getAllMeal();
    }
}
