package store.domain;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final List<OrderItems> purchasedItems;
    private final List<OrderItems> freeItems;
    private int totalAmount;
    private int promotionDiscount;
    private int membershipDiscount;
    private int finalPayment;

    public Receipt() {
        this.purchasedItems = new ArrayList<>();
        this.freeItems = new ArrayList<>();
        totalAmount = 0;
        promotionDiscount = 0;
        membershipDiscount = 0;
        finalPayment = 0;
    }

    public void addPurchasedItem(OrderItems item) {
        purchasedItems.add(item);
        totalAmount += item.getTotalPrice();
    }

    public void addFreeItem(OrderItems item) {
        freeItems.add(item);
    }

    public void applyPromotionDiscount(int discount) {
        this.promotionDiscount += discount;
    }

    public void applyMembershipDiscount(int discount) {
        this.membershipDiscount = discount;
    }

    public void calculateFinalPayment() {
        this.finalPayment = totalAmount - promotionDiscount - membershipDiscount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public List<OrderItems> getPurchasedItems() {
        return purchasedItems;
    }

    public List<OrderItems> getFreeItems() {
        return freeItems;
    }

    public int getPromotionDiscount() {
        return promotionDiscount;
    }

    public int getMembershipDiscount() {
        return membershipDiscount;
    }

    public int getFinalPayment() {
        return finalPayment;
    }

}
