package main.java.com.himani.messageprocessor.service;

import main.java.com.himani.messageprocessor.model.Adjustment;
import main.java.com.himani.messageprocessor.model.Sale;
import java.util.List;

public interface ReportGenerator {
    String generateSalesReport(List<Sale> sales);
    String generateAdjustmentsReport(List<Adjustment> adjustments);

}
