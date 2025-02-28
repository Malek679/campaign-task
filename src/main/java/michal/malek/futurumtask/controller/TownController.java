package michal.malek.futurumtask.controller;

import lombok.RequiredArgsConstructor;
import michal.malek.futurumtask.model.entity.Town;
import michal.malek.futurumtask.service.TownService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/towns")
@RequiredArgsConstructor
public class TownController {

    private final TownService townService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Town>> getAllCampaigns() {
        List<Town> towns = townService.getAll();
        return ResponseEntity.ok(towns);
    }
}
