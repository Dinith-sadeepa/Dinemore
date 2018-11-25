package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.business.BOFactory;
import lk.ijse.dinemore.business.custom.CookingBO;
import lk.ijse.dinemore.dto.CookingDTO;
import lk.ijse.dinemore.service.custom.CookingService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CookingServiceImpl extends UnicastRemoteObject implements CookingService {
    private CookingBO cookingBO;

    public CookingServiceImpl() throws RemoteException {
        cookingBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.COOKING);
    }

    @Override
    public boolean addCooking(CookingDTO cookingDTO) throws Exception {
        return cookingBO.addCooking(cookingDTO);
    }

    @Override
    public boolean deleteCooking(int id) throws Exception {
        return false;
    }

    @Override
    public boolean updateCooking(CookingDTO cookingDTO) throws Exception {
        return false;
    }

    @Override
    public List<CookingDTO> getAllCooking() throws Exception {
        return cookingBO.getAllCooking();
    }
}
