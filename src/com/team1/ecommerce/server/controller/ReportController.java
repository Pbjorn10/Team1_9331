package com.team1.ecommerce.server.controller;

import com.team1.ecommerce.server.service.ReportService;
import com.team1.ecommerce.shared.model.Report;

import java.util.Date;

public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Requests a generated sales report.
     *
     * @param start start date (inclusive)
     * @param end   end date (inclusive)
     * @return generated Report object
     */
    public Report getSalesReport(Date start, Date end) {
        return reportService.generateSalesReport(start, end);
    }
}