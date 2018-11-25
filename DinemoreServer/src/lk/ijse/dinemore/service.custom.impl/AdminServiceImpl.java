package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.service.custom.AdminService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AdminServiceImpl extends UnicastRemoteObject implements AdminService {
    public AdminServiceImpl() throws RemoteException {
    }
}
