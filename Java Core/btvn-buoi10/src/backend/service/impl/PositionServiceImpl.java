package backend.service.impl;

import backend.repository.IPositionRepository;
import backend.repository.impl.PositionRepositoryImpl;
import backend.service.IPositionService;
import entity.Position;

import java.util.List;

public class PositionServiceImpl implements IPositionService {
    private IPositionRepository repository;

    public PositionServiceImpl() {
        this.repository = new PositionRepositoryImpl();
    }

    @Override
    public List<Position> findAll() {
        return repository.findAll();
    }
}