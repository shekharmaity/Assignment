package com.task.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;


@Repository("activityDao")
public class ActivityDao implements IActivityDao{
	
	@Autowired
	JdbcTemplate dataSourceTemplate;

	/*implemented method to fetch employee statistics*/
	@Override
	public List<Map> fetchStatistics() {
		
		//Map result = new HashMap();
		
		List<Map> resultLt = new LinkedList<Map>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT name, COUNT(name) as occurrences ");
		query.append("FROM emp_activity WHERE ");
		query.append("task_time >= SYSDATE - 7 ");
		query.append("GROUP BY name ");
		query.append("ORDER BY occurrences desc");
		
		try {
		dataSourceTemplate.query(query.toString(), new Object[] {}, new RowCallbackHandler()
		{
			@Override
			public void processRow(ResultSet resultset) throws SQLException
			{
				Map m = new LinkedHashMap<>();
				m.put("activity_name", resultset.getString(1));
				m.put("occurrences", resultset.getString(2));
				resultLt.add(m);
			}
		});
		return resultLt;
		}catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
	}

	
	//database method to fetch employee today activities
	@Override
	public List<Map> fetchTodayAct() {

		List<Map> resultLt = new LinkedList<Map>();
		StringBuilder query = new StringBuilder();
		query.append("select eid,name,task_time from emp_activity ");
		query.append("where TASK_TIME > sysdate - 1 ");
		query.append("order by eid,TASK_TIME ");
		
		try {
			dataSourceTemplate.query(query.toString(), new Object[] {}, new RowCallbackHandler()
			{
				@Override
				public void processRow(ResultSet resultset) throws SQLException
				{
					Map m = new LinkedHashMap();
					m.put("eid", resultset.getString(1));
					m.put("name", resultset.getString(2));
					m.put("start_time", resultset.getString(3));
					resultLt.add(m);
				}
			});
			return resultLt;
			}catch(Exception ex)
			{
				System.out.println(ex);
				return null;
			}
	}

}
