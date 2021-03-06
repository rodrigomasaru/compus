package br.com.compus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.compus.models.Cpu;
import br.com.compus.models.Memory;

public class MemoryDAO extends BaseDAO {
  public MemoryDAO() {
    super();
  }

  public static MemoryDAO getInstance() {
    return new MemoryDAO();
  }

  public List<Memory> getAll() throws SQLException {
    List<Memory> memories = new ArrayList<Memory>();
    try {
      String sql = "SELECT * FROM memory";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Memory memory = new Memory();
        memory.setId(result.getInt("id"));
        memory.setName(result.getString("name"));
        memory.setPrice(result.getDouble("price"));
        memory.setType(result.getString("type"));
        memory.setCapacity(result.getString("capacity"));
        memory.setImageUrl(result.getString("image_url"));
        memories.add(memory);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return memories;
  }

  public Memory findById(int id) throws SQLException {
    Memory memory = null;
    try {
      String sql = "SELECT * FROM memory WHERE id=?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        memory = new Memory();
        memory.setId(result.getInt("id"));
        memory.setName(result.getString("name"));
        memory.setPrice(result.getDouble("price"));
        memory.setType(result.getString("type"));
        memory.setCapacity(result.getString("capacity"));
        memory.setImageUrl(result.getString("image_url"));
        result.close();
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return memory;
  }
}
