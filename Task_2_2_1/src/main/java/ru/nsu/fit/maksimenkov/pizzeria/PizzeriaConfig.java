package ru.nsu.fit.maksimenkov.pizzeria;

import java.util.List;

public class PizzeriaConfig {

    private Long nBaker;
    private Long nCourier;
    private Long warehouse;

    public List<Integer> getTrunkCaps() {
        return trunkCaps;
    }

    public void setTrunkCaps(List<Integer> trunkCaps) {
        this.trunkCaps = trunkCaps;
    }

    List<Integer> trunkCaps;

    public Long getnBaker() {
        return nBaker;
    }

    public void setnBaker(Long nBaker) {
        this.nBaker = nBaker;
    }

    public Long getnCourier() {
        return nCourier;
    }

    public void setnCourier(Long nCourier) {
        this.nCourier = nCourier;
    }

    public Long getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Long warehouse) {
        this.warehouse = warehouse;
    }
}
