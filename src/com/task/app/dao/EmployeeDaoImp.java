package com.task.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.task.app.bean.Activities;
import com.task.app.bean.Employee;

@Repository("employeeDaoImp")
public class EmployeeDaoImp implements IEmployeeDao{
	
	/*IOC to get database instance*/
	ApplicationContext context = new ClassPathXmlApplicationContext("database-config.xml");
	JdbcTemplate dataSource= (JdbcTemplate) context.getBean("dataSourceTemplate");
	
	/*implemented method to save employee record from given json*/
	@Override
	public boolean saveActivity(Employee e) {
		// TODO Auto-generated method stub
		
		try {
		List<Activities> ac = new ArrayList<Activities>();
		ac = e.getActivities();
		Long eid = e.getEmployee_id();
		for(Activities a : ac)
		{
			StringBuilder query = new StringBuilder();
			query.append("insert into emp_activity (eid, name,duration,task_time) values ");
			query.append("("+eid);
			query.append(", '"+a.getName());
			query.append("',"+a.getDuration());
			query.append(", TO_TIMESTAMP('"+a.getTime()+"' , 'YYYY-MM-DD HH24:MI:SS:FF')");
			query.append(")");
			
			//System.out.println(query.toString());
			
			dataSource.execute(query.toString());
		}
		}catch(Exception ex) {
			System.out.println("exception while saving record :::"+ex);
		}
		return false;
	}
	

}
