package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.business.BOFactory;
import lk.ijse.dinemore.business.custom.DeliverBoyBO;
import lk.ijse.dinemore.dto.DeliveryBoyDTO;
import lk.ijse.dinemore.service.custom.DeliveryBoyService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DeliveryBoyServiceImpl extends UnicastRemoteObject implements DeliveryBoyService {
    private DeliverBoyBO deliverBoyBO;
    public DeliveryBoyServiceImpl() throws RemoteException {
        deliverBoyBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.DELIVERY_BOY);
    }

    @Override
    public boolean addDeliveryBoy(DeliveryBoyDTO deliveryBoyDTO) throws Exception {
        return deliverBoyBO.addDeliveryBoy(deliveryBoyDTO);
    }

    @Override
    public boolean deleteDeliveryBoyr(int id) throws Exception {
        return deliverBoyBO.deleteDeliveryBoyr(id);
    }

    @Override
    public boolean updateDeliveryBoy(DeliveryBoyDTO deliveryBoyDTO) throws Exception {
        return deliverBoyBO.updateDeliveryBoy(deliveryBoyDTO);
    }

    @Override
    public List<DeliveryBoyDTO> getAllDeliveryBoy() throws Exception {
        return deliverBoyBO.getAllDeliveryBoy();
    }
}
