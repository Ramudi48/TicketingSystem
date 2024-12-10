package oop.cwk.ramudi.TicketingSystem.wiring.service;


import oop.cwk.ramudi.TicketingSystem.SimulateTicketing.Simulate;

import org.springframework.stereotype.Service;

@Service
    public class SimulationService {



        public void startSimulation() {
            Simulate.startSimulation();
        }

        public void stopSimulation() {
            Simulate.getCustomerThread().interrupt();
            Simulate.getVendorThread().interrupt();
        }
    }

