package rs.frm.officetimetracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.frm.officetimetracker.repository.CheckInOutRepository;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final CheckInOutRepository checkInOutRepository;

    public void generateWeeklyReport() {
        // implementacija generisanja izveštaja
    }
}
