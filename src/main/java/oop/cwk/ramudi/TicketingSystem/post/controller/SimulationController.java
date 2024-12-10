package oop.cwk.ramudi.TicketingSystem.post.controller;


import oop.cwk.ramudi.TicketingSystem.wiring.service.SimulationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin(origins = "*")
    @RequestMapping("/api/simulation")
    public class SimulationController {
        private final SimulationService simulationService; // @Autowire not used as a constructor is there

        public SimulationController(SimulationService simulationService) {
            this.simulationService = simulationService;
        }

        @PostMapping("/start")
        public void startSimulation() {
            simulationService.startSimulation();
        }

        @PostMapping("/stop")
        public void stopSimulation() {
            simulationService.stopSimulation();
        }

    }

