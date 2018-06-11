package it.polito.tdp.rivers.db;

import java.util.*;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {
	
	private Map<Integer,River> rivers = new HashMap<Integer, River>();
	
	public Map<Integer,River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.put(res.getInt("id"), new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
public List<Flow> getFlows(River river) {
		
		final String sql = "SELECT id, day, flow FROM flow WHERE river=?";

		List<Flow> flows = new LinkedList<Flow>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				flows.add(new Flow(res.getDate("day").toLocalDate(), res.getDouble("flow"), river));
			}
			Collections.sort(flows);
			river.setFlows(flows);
			
			conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return flows;
}
	
}
