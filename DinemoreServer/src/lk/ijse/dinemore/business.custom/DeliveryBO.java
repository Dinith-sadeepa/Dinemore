package lk.ijse.dinemore.business.custom;

import lk.ijse.dinemore.business.SuperBO;
import lk.ijse.dinemore.dto.DeliveryDTO;

import java.util.List;

public interface DeliveryBO extends SuperBO {
    public boolean addDelivery(DeliveryDTO deliveryDTO) throws Exception;

    public boolean deleteDelivery(int id) throws Exception;

    public boolean updateDelivery(DeliveryDTO deliveryDTO) throws Exception;

    public List<DeliveryDTO> getAllDelivery() throws Exception;
}
