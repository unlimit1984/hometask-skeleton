package ua.epam.spring.hometask.domain;

public class UserAccount extends DomainObject{
    private Long userId;
    private double money;

    public UserAccount() {
    }

    public UserAccount(Long userId, double money) {
        this.userId = userId;
        this.money = money;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean canBuy(double price) {
        return money - price > 0;
    }

    public void addMoney(double amount) {
        money += amount;
    }

    public boolean buy(double price) {
        if (canBuy(price)) {
            money -= price;
            return true;
        }
        return false;
    }
}
