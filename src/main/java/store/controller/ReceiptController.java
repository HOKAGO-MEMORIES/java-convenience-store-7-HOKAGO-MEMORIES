package store.controller;

import store.domain.OrderItems;
import store.domain.Receipt;
import store.util.ReceiptFormat;

public class ReceiptController {
    private final Receipt receipt;
    private final static String MINUS = "-";

    public ReceiptController(Receipt receipt) {
        this.receipt = receipt;
    }

    public void printReceipt() {
        printHeader();
        printOrderItems();
        printFreeItems();
        printResult();
    }

    private void printHeader() {
        ReceiptFormat.printStoreHeader();
        System.out.println(
                ReceiptFormat.getFormatedLabel(ReceiptFormat.ITEM) +
                        ReceiptFormat.getFormatedLabel(ReceiptFormat.QUANTITY) +
                        ReceiptFormat.getFormatedLabel(ReceiptFormat.PRICE)
        );
    }

    private void printOrderItems() {
        for (OrderItems item : receipt.getPurchasedItems()) {
            System.out.print(ReceiptFormat.formatValue(item.getProductName(), ReceiptFormat.ITEM.getWidth()));
            System.out.print(
                    ReceiptFormat.formatValue(String.valueOf(item.getOrderQuantity()),
                            ReceiptFormat.QUANTITY.getWidth()));
            System.out.print(
                    ReceiptFormat.formatValue(String.format("%,d", item.getPrice()),
                            ReceiptFormat.PRICE.getWidth()));
            System.out.println();
        }
    }

    private void printFreeItems() {
        if (!receipt.getFreeItems().isEmpty()) {
            ReceiptFormat.printPromotionHeader();
            for (OrderItems item : receipt.getFreeItems()) {
                System.out.print(ReceiptFormat.formatValue(item.getProductName(), ReceiptFormat.FREE_ITEM.getWidth()));
                System.out.print(
                        ReceiptFormat.formatValue(String.valueOf(item.getOrderQuantity()),
                                ReceiptFormat.QUANTITY.getWidth()));
                System.out.println();
            }
        }
    }

    private void printResult() {
        ReceiptFormat.printSeparator();
        printTotalPayment();
        printPromotionDiscount();
        printMembershipDiscount();
        printFinalPayment();
    }

    private void printTotalPayment() {

        System.out.println(
                ReceiptFormat.formatValue(ReceiptFormat.TOTAL_PAYMENT.getLabel(),
                        ReceiptFormat.TOTAL_PAYMENT.getWidth()) +
                        ReceiptFormat.formatValue(String.format("%,d", receipt.getTotalAmount()),
                                ReceiptFormat.TOTAL_PAYMENT.getWidth())
        );
    }

    private void printPromotionDiscount() {
        System.out.println(
                ReceiptFormat.formatValue(ReceiptFormat.PROMOTION_DISCOUNT.getLabel(),
                        ReceiptFormat.PROMOTION_DISCOUNT.getWidth()) +
                        ReceiptFormat.formatValue(String.format("%s%,d", MINUS, receipt.getPromotionDiscount()),
                                ReceiptFormat.PROMOTION_DISCOUNT.getWidth())
        );
    }

    private void printMembershipDiscount() {
        System.out.println(
                ReceiptFormat.formatValue(ReceiptFormat.MEMBERSHIP_DISCOUNT.getLabel(),
                        ReceiptFormat.MEMBERSHIP_DISCOUNT.getWidth()) +
                        ReceiptFormat.formatValue(String.format("%s%,d", MINUS, receipt.getMembershipDiscount()),
                                ReceiptFormat.MEMBERSHIP_DISCOUNT.getWidth())
        );
    }

    private void printFinalPayment() {
        System.out.println(
                ReceiptFormat.formatValue(ReceiptFormat.FINAL_PAYMENT.getLabel(),
                        ReceiptFormat.FINAL_PAYMENT.getWidth()) +
                        ReceiptFormat.formatValue(String.format("%,d", receipt.getFinalPayment()),
                                ReceiptFormat.FINAL_PAYMENT.getWidth())
        );
    }
}
