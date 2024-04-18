package org.example.demo;

import DAO.User_DAO;
import Model.User;
import javafx.animation.TranslateTransition;

import java.util.concurrent.TransferQueue;

public class test {
    public static void main(String[] rags) {
        User u1 = new User(0, "NVA", "123", 1);
        User_DAO.getInstance().insert(u1);

    }
}
