package rs.frm.officetimetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.frm.officetimetracker.domain.CheckInOut;
import rs.frm.officetimetracker.domain.User;
import rs.frm.officetimetracker.service.CheckInOutService;
import rs.frm.officetimetracker.service.LocationService;
import rs.frm.officetimetracker.service.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/check")
public class CheckInOutController {
    @Autowired
    private CheckInOutService checkInOutService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private UserService userService;

    @PostMapping("/in")
    public ResponseEntity<String> checkIn(@RequestBody Map<String, Double> location, Principal principal) {
        double latitude = location.get("latitude");
        double longitude = location.get("longitude");
        if (locationService.isWithinOfficeLocation(latitude, longitude)) {
            User user = userService.findByEmail(principal.getName()).orElseThrow();
            CheckInOut checkIn = new CheckInOut();
            checkIn.setUser(user);
            checkIn.setCheckInTime(LocalDateTime.now());
            checkInOutService.save(checkIn);
            return ResponseEntity.ok("Check-in successful");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not within office location");
        }
    }

    @PostMapping("/out")
    public ResponseEntity<String> checkOut(@RequestBody Map<String, Double> location, Principal principal) {
        double latitude = location.get("latitude");
        double longitude = location.get("longitude");
        if (locationService.isWithinOfficeLocation(latitude, longitude)) {
            User user = userService.findByEmail(principal.getName()).orElseThrow();
            CheckInOut checkOut = checkInOutService.findLastCheckInByUser(user)
                    .orElseThrow(() -> new RuntimeException("No check-in found"));
            checkOut.setCheckOutTime(LocalDateTime.now());
            checkInOutService.save(checkOut);
            return ResponseEntity.ok("Check-out successful");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not within office location");
        }
    }
}
