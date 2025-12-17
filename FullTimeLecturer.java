import java.math.BigDecimal;

public class FullTimeLecturer implements Payable {
    private final Rank rank;
    private final int yearsOfService;
    
    public enum Rank {
        ASSISTANT(50000), ASSOCIATE(65000), FULL(80000);
        
        private final BigDecimal baseSalary;
        
        Rank(double baseSalary) {
            this.baseSalary = BigDecimal.valueOf(baseSalary);
        }
        
        public BigDecimal getBaseSalary() { return baseSalary; }
    }
    
    public FullTimeLecturer(Rank rank, int yearsOfService) {
        this.rank = rank;
        this.yearsOfService = yearsOfService;
    }
    
    @Override
    public BigDecimal calculatePayment() {
        BigDecimal bonus = rank.getBaseSalary()
            .multiply(BigDecimal.valueOf(yearsOfService * 0.02));
        return rank.getBaseSalary().add(bonus);
    }
}
