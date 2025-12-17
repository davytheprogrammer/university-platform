import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Payable {
    BigDecimal calculatePayment();
    
    default BigDecimal calculateTax(double taxRate) {
        return calculatePayment().multiply(BigDecimal.valueOf(taxRate))
                .setScale(2, RoundingMode.HALF_UP);
    }
    
    default BigDecimal calculateNetPayment(double taxRate) {
        return calculatePayment().subtract(calculateTax(taxRate));
    }
    
    default String getPaymentSummary(double taxRate) {
        BigDecimal gross = calculatePayment();
        BigDecimal tax = calculateTax(taxRate);
        BigDecimal net = calculateNetPayment(taxRate);
        
        return String.format("Gross: $%.2f, Tax: $%.2f, Net: $%.2f", 
                           gross, tax, net);
    }
}
