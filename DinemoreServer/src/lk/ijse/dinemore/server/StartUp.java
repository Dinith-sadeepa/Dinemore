package lk.ijse.dinemore.server;

import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.impl.ServiceFactoryImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartUp {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(5050);
            registry.bind("dinemore", ServiceFactoryImpl.getInstance());
            System.out.println("Server has been started");
        } catch (RemoteException ex) {
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
