package rs.frm.officetimetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.frm.officetimetracker.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

//    @GetMapping("/weekly")
//    public void getWeeklyReport() {
//        return reportService.generateWeeklyReport();
//    }
}
