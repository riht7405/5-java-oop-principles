package com.example.task02;

public class DiscountBill extends Bill{
    private final double discount;
    public DiscountBill(int discount) {
        this.discount = discount;
    }
    public String getDiscount() {
        return discount + "%";
    }
    public long getAbsoluteDiscount(){
        return (long)(super.getPrice() * ((double)discount / 100));
    }
    @Override
    public long getPrice() {
        return super.getPrice() - getAbsoluteDiscount();
    }
}