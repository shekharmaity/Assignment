package com.task.app.service;

import java.util.List;
import java.util.Map;

public interface IActivityService {
	boolean saveActivityRecord();
	
	List<Map> fetchStatistics();
	
	public List<Map> fetchTodayAct();
	

}
