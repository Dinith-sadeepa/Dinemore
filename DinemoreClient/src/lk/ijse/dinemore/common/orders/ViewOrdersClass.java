package lk.ijse.dinemore.common.orders;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dinemore.dto.*;
import lk.ijse.dinemore.service.custom.*;

import java.util.ArrayList;

public class ViewOrdersClass {
    private static ObservableList<ViewOrderDTO> viewOrderDTOS = FXCollections.observableArrayList();

    public ViewOrdersClass() {
    }

    public static void loadOrders(TableView<ViewOrderDTO> orderTable, CustomerService customerService, OrderService orderService, OrderDetailService orderDetailService, CookingService cookingService, DeliveryService deliveryService) {

        ArrayList<ViewOrderDTO> viewOrderDTOArrayList = new ArrayList<>();
        try {
            ArrayList<CustomerDTO> allCustomer = (ArrayList<CustomerDTO>) customerService.getAllCustomer();
            ArrayList<OrderDTO> allOrder = (ArrayList<OrderDTO>) orderService.getAllOrder();
            ArrayList<OrderDetailDTO> allOrderDetail = (ArrayList<OrderDetailDTO>) orderDetailService.getAllOrderDetail();
            ArrayList<CookingDTO> allCooking = (ArrayList<CookingDTO>) cookingService.getAllCooking();
            ArrayList<DeliveryDTO> allDelivery = (ArrayList<DeliveryDTO>) deliveryService.getAllDelivery();
            for (OrderDTO orderDTO : allOrder) {
                ViewOrderDTO viewOrderDTO = new ViewOrderDTO();
                viewOrderDTO.setOrderId(orderDTO.getOrderId());
                viewOrderDTO.setCookingStatus("pending");
                viewOrderDTO.setDeliveryStatus("pending");
                viewOrderDTO.setOrderDate(orderDTO.getOrderDate());
                for (CustomerDTO customerDTO : allCustomer) {
                    if (customerDTO.getCustomerId() == orderDTO.getCustomerDTO().getCustomerId()) {
                        viewOrderDTO.setCustomerId(customerDTO.getCustomerId());
                        viewOrderDTO.setCustomerName(customerDTO.getCustomerName());
                        viewOrderDTO.setCustomerAddress(customerDTO.getCustomerAddress());
                    }
//                    break;
                }
                for (OrderDetailDTO orderDetailDTO : allOrderDetail) {
                    if (orderDetailDTO.getOrderDTO().getOrderId() == orderDTO.getOrderId()) {
                        int orderDetailId = orderDetailDTO.getOrderDetailId();
                        for (CookingDTO cookingDTO : allCooking) {
                            if (orderDetailId == cookingDTO.getOrderDetailDTO().getOrderDetailId()) {
                                viewOrderDTO.setCookingStatus("Success");
                            } else {
                                viewOrderDTO.setCookingStatus("on going");
                            }
//                                break;
                        }
                        for (DeliveryDTO deliveryDTO :allDelivery) {
                            if(orderDetailId == deliveryDTO.getOrderDetail().getOrderDetailId()){
                                viewOrderDTO.setDeliveryStatus("Success");
                            }else{
                                viewOrderDTO.setDeliveryStatus("on going");
                            }
                            break;
                        }
                    }

                }
                viewOrderDTOArrayList.add(viewOrderDTO);
            }
            viewOrderDTOS = FXCollections.observableArrayList(viewOrderDTOArrayList);
            orderTable.setItems(viewOrderDTOS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void loadColumns(TableView<ViewOrderDTO> orderTable) {
        orderTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        orderTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        orderTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        orderTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("cookingStatus"));
        orderTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
        orderTable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    }
    public static ObservableList<ViewOrderDTO> getarray(){
        return viewOrderDTOS;
    }
}
