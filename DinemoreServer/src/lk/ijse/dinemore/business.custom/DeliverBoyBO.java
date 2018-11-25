package lk.ijse.dinemore.business.custom;

import lk.ijse.dinemore.business.SuperBO;
import lk.ijse.dinemore.dto.DeliveryBoyDTO;

import java.util.List;

public interface DeliverBoyBO extends SuperBO {
    public boolean addDeliveryBoy(DeliveryBoyDTO deliveryBoyDTO) throws Exception;

    public boolean deleteDeliveryBoyr(int id) throws Exception;

    public boolean updateDeliveryBoy(DeliveryBoyDTO deliveryBoyDTO) throws Exception;

    public List<DeliveryBoyDTO> getAllDeliveryBoy() throws Exception;
}
