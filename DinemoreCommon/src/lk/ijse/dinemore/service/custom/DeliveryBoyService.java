package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.DeliveryBoyDTO;
import lk.ijse.dinemore.service.SuperService;

import java.util.List;

public interface DeliveryBoyService extends SuperService {
    public boolean addDeliveryBoy(DeliveryBoyDTO deliveryBoyDTO) throws Exception;

    public boolean deleteDeliveryBoyr(int id) throws Exception;

    public boolean updateDeliveryBoy(DeliveryBoyDTO deliveryBoyDTO) throws Exception;

    public List<DeliveryBoyDTO> getAllDeliveryBoy() throws Exception;
}
