package backend.controller;

import backend.service.IPositionService;
import backend.service.impl.PositionServiceImpl;
import entity.Position;

import java.util.List;

public class PositionController {
    private IPositionService positionService;

    public PositionController() {
        this.positionService = new PositionServiceImpl();
    }

    public List<Position> findAll() {
        return positionService.findAll();
    }
}