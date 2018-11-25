package lk.ijse.dinemore.business.custom;

import lk.ijse.dinemore.business.SuperBO;
import lk.ijse.dinemore.dto.CookingDTO;

import java.util.List;

public interface CookingBO extends SuperBO {
    public boolean addCooking(CookingDTO cookingDTO) throws Exception;

    public boolean deleteCooking(int id) throws Exception;

    public boolean updateCooking(CookingDTO cookingDTO) throws Exception;

    public List<CookingDTO> getAllCooking() throws Exception;
}
