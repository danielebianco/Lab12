package it.polito.tdp.rivers.model;

import java.util.*;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	private RiversDAO rdao;
	private Map<Integer,River> riversMap;
	 

	public Model() {
		this.rdao = new RiversDAO();
		this.riversMap = rdao.getAllRivers();		
	}
	
	public List<Flow> getFlows(River river) {
		
		List<Flow> rflow = rdao.getFlows(river);
		if (rflow == null) {
			return new LinkedList<Flow>();
		}
		
		return rflow;
	}

	public Map<Integer,River> getRiversMap() {
		return riversMap;
	}

	public String getStartDate(River value) {
		if(!rdao.getFlows(value).isEmpty())
			return rdao.getFlows(value).get(0).getDay().toString();
		return null;
	}

	public String getEndDate(River value) {
		if(!rdao.getFlows(value).isEmpty()) {
			int n = rdao.getFlows(value).size() - 1;
			return rdao.getFlows(value).get(n).getDay().toString();
		}
		return null;
	}

	public String getNumMeasurements(River value) {
		if(rdao.getFlows(value)!=null) 
			return String.format("%d", rdao.getFlows(value).size());
		else
			return null;
	}

	public String getFMed(River value) {
		double avg = 0.0;
		if(rdao.getFlows(value)!=null) {
			for(Flow f : rdao.getFlows(value))
				avg += f.getFlow();
			avg /= rdao.getFlows(value).size();
			return String.format("%f", avg);
		}
		return null;
	}

		
	
}
