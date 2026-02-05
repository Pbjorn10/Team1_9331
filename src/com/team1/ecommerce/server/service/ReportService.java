package com.team1.ecommerce.server.service;

import com.team1.ecommerce.shared.model.Report;
import com.team1.ecommerce.shared.model.Transaction;
import com.team1.ecommerce.server.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportService {

    private final TransactionRepository transactionRepository;

    /**
     * Constructs ReportService.
     *
     * @param transactionRepository repository used to fetch transactions
     */
    public ReportService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Generates a sales report within a date range.
     *
     * @param start starting date (inclusive)
     * @param end   ending date (inclusive)
     * @return Report object containing filtered transactions and total amount
     */
    public Report generateSalesReport(Date start, Date end) {

        // Retrieve all transactions from repository
        List<Transaction> allTransactions =
                transactionRepository.findAll();

        List<Transaction> filtered = new ArrayList<>();
        double totalAmount = 0;

        // Filter transactions within date range
        for (Transaction t : allTransactions) {

            Date date = t.getDate();

            // Include transactions where start <= date <= end
            if (!date.before(start) && !date.after(end)) {
                filtered.add(t);
                totalAmount += t.getAmount();
            }
        }

        // Build report object
        Report report = new Report();
        report.setStartDate(start);
        report.setEndDate(end);
        report.setTransactions(filtered);
        report.setTotalAmount(totalAmount);

        return report;
    }
}