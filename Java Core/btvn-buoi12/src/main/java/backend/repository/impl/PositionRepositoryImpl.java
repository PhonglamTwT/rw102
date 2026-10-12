package backend.repository.impl;

import backend.repository.IPositionRepository;
import entity.Position;
import entity.PositionName;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PositionRepositoryImpl implements IPositionRepository {

    @Override
    public List<Position> findAll() {
        List<Position> positions = new ArrayList<>();
        String sql = "SELECT * FROM `position`";
        try {
            Connection conn = JDBCUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("position_id");
                String nameStr = rs.getString("position_name");
                PositionName pName = (nameStr != null) ? PositionName.valueOf(nameStr) : null;
                positions.add(new Position(id, pName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }
        return positions;
    }
}