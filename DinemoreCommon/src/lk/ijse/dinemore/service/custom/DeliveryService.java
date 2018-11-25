package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.DeliveryDTO;
import lk.ijse.dinemore.service.SuperService;

import java.util.List;

public interface DeliveryService extends SuperService {
    public boolean addDelivery(DeliveryDTO deliveryDTO) throws Exception;

    public boolean deleteDelivery(int id) throws Exception;

    public boolean updateDelivery(DeliveryDTO deliveryDTO) throws Exception;

    public List<DeliveryDTO> getAllDelivery() throws Exception;
}
