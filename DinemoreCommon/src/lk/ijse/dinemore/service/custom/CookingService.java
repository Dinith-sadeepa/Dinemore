package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.CookingDTO;
import lk.ijse.dinemore.service.SuperService;

import java.util.List;

public interface CookingService extends SuperService {
    public boolean addCooking(CookingDTO cookingDTO) throws Exception;

    public boolean deleteCooking(int id) throws Exception;

    public boolean updateCooking(CookingDTO cookingDTO) throws Exception;

    public List<CookingDTO> getAllCooking() throws Exception;
}
