package main.java.com.himani.messageprocessor.model;

import java.math.BigDecimal;

import main.java.com.himani.messageprocessor.model.enums.AdjustmentEnumType;

public class Adjustment {
	private AdjustmentEnumType type;
    private String productName;
    private BigDecimal amount;

    public Adjustment(AdjustmentEnumType type, String productName, BigDecimal amount) {
        this.type = type;
        this.productName = productName;
        this.amount = amount;
    }

    public AdjustmentEnumType getType() {
        return type;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adjustment that = (Adjustment) o;

        if (type != that.type) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 46 * result + (productName != null ? productName.hashCode() : 0);
        result = 46 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Adjustment{" +
                "type=" + type +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
