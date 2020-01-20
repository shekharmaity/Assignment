package com.task.app.dao;

import java.util.List;
import java.util.Map;

public interface IActivityDao {
	
	public List<Map> fetchStatistics();
	
	public List<Map> fetchTodayAct();
}
