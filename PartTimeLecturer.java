import java.math.BigDecimal;

public class PartTimeLecturer implements Payable {
    private final int hours;
    private final BigDecimal rate;
    private static final int MAX_HOURS = 40;
    
    public PartTimeLecturer(int hours, BigDecimal rate) {
        if (hours < 0 || hours > MAX_HOURS) {
            throw new IllegalArgumentException("Hours must be between 0 and " + MAX_HOURS);
        }
        if (rate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Rate must be positive");
        }
        this.hours = hours;
        this.rate = rate;
    }
    
    @Override
    public BigDecimal calculatePayment() {
        BigDecimal basePayment = rate.multiply(BigDecimal.valueOf(hours));
        
        // Overtime calculation (1.5x for hours > 20)
        if (hours > 20) {
            int overtimeHours = hours - 20;
            BigDecimal overtimePayment = rate.multiply(BigDecimal.valueOf(overtimeHours))
                                           .multiply(BigDecimal.valueOf(0.5));
            return basePayment.add(overtimePayment);
        }
        
        return basePayment;
    }
}
