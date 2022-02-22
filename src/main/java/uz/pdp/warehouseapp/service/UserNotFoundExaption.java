package uz.pdp.warehouseapp.service;

public class UserNotFoundExaption extends Throwable {
    public UserNotFoundExaption(String message) {
        super(message);
    }
}
