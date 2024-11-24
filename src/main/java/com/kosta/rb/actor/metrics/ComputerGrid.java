package com.kosta.rb.actor.metrics;

import com.kosta.rb.actor.metrics.dynamicflow.ComputerFlowContext;
import com.kosta.rb.actor.metrics.dynamicflow.TrafficFlowContext;
import com.kosta.rb.comp.Position;
import com.kosta.rb.core.Board;
import com.kosta.rb.core.Store;
import com.kosta.rb.core.abs.Actor;
import com.kosta.rb.rule.def.FlowStatus;

import java.util.HashMap;
import java.util.Map;

public class ComputerGrid extends Actor {
    Map<Integer, Computer> computerStore = new HashMap<>();
    Map<Integer, Traffic> trafficStore = new HashMap<>();
    Traffic activeTraffic;

    protected Store store;

    public ComputerGrid(){
        super();
        store = Board.getInstance().getStore();
    }

    public Computer spawnComputer(FlowStatus flowStatus){
        int seq = flowStatus.getSequence();
        ComputerFlowContext ctx = flowStatus.getUniqueDynamicFlow();
        boolean hasKey = computerStore.containsKey(seq);
        if(!hasKey){
            Computer dynamicActor = store.dynamicFlowSpawnActor(Computer.class, ctx);
            computerStore.put(seq, dynamicActor);
            Position ps = dynamicActor.getPosition();
            ps.setPosition(seq * 6, 3);
        }

        Computer dynamicActor = computerStore.get(seq);
        dynamicActor.visible(true);
        return dynamicActor;
    }

    public Traffic chainTraffic(FlowStatus flowStatus){
        int seq = flowStatus.getSequence();
        TrafficFlowContext ctx = flowStatus.getUniqueDynamicFlow();
        boolean hasKey = trafficStore.containsKey(seq);
        if(!hasKey){
            Traffic dynamicActor = store.dynamicFlowSpawnActor(Traffic.class, ctx);
            trafficStore.put(seq, dynamicActor);
        }

        Traffic dynamicActor = trafficStore.get(seq);
        Position ps = dynamicActor.getPosition();

        Computer sc = findComputerByDirKey(ctx.startDir);
        Computer ec = findComputerByDirKey(ctx.endDir);

        dynamicActor.setDirPosition(sc, ec);
        visibleTraffic(flowStatus, true);
        return dynamicActor;
    }

    public void visibleComputer(FlowStatus flowStatus, boolean value){
        int seq = flowStatus.getSequence();
        Computer computer = computerStore.get(seq);
        computer.visible(value);
    }

    public void visibleTraffic(FlowStatus flowStatus, boolean value){
        int seq = flowStatus.getSequence();
        Traffic traffic = trafficStore.get(seq);
        traffic.visible(value);
    }

    public Computer findComputerByDirKey(String key){
        return computerStore.values().stream().filter( computer->{
            ComputerFlowContext ctx = computer.getCTX();
            return ctx.getDirKey().equals(key);
        }).findAny().get();
    }

}
