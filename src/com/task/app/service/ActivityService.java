package com.task.app.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.app.bean.Activities;
import com.task.app.bean.Employee;
import com.task.app.dao.EmployeeDaoImp;
import com.task.app.dao.IActivityDao;
import com.task.app.dao.IEmployeeDao;

@Service("activityService")
public class ActivityService implements IActivityService {
	
	@Autowired
	IActivityDao activityDao; 

//	service to save employee activity from Json file
	@Override
	public boolean saveActivityRecord() {
		IEmployeeDao employeeDaoImp = new EmployeeDaoImp(); 
		TypeReference<Map<String, Object>> type = new TypeReference<Map<String, Object>>() {
		};
		try {
			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			Resource[] resources = (Resource[]) resolver.getResources("/JSON/*.json");
			 if(resources.length > 10 || resources.length < 100000)
			 {
			List<Employee> emplt = new ArrayList<Employee>();
			for (Resource resource : resources) {
				Employee e = new Employee();
				InputStream input = resource.getInputStream();

				ObjectMapper mapper = new ObjectMapper();
				Object contacts = mapper.readValue(input, type);
				Map m = (Map) contacts;
				e.setEmployee_id(Long.parseLong(m.get("employee_id").toString()));

				if (m.containsKey("activities")) 
				{
					List lt = (List) m.get("activities");
					// Map<String,String> actMap = new HashMap<String,String>();
					List<Activities> actList = new ArrayList<Activities>();
					for (int i = 0; i < lt.size(); i++) 
					{
						Map<String, String> actMap = (Map<String, String>) lt.get(i);
						Activities a = new Activities();
						a.setName(actMap.get("name"));
						Timestamp ts = Timestamp.valueOf(actMap.get("time"));
						a.setTime(ts);
						
						int duration = Integer.parseInt(actMap.get("duration"));
						a.setDuration(duration);
						
						//validation
						if(a.getName().equals("login") || a.getName().equals("logout")
							|| a.getName().equals("teabreak") || a.getName().equals("lunchbreak")
							|| a.getName().equals("gamemood") || a.getName().equals("naptime"))
						{
								actList.add(a);
						}
						else {
							System.out.println("Activity name is not match :::: "+a.toString());
						}
					}
					e.setActivities(actList);
					
					//employee save method called
					employeeDaoImp.saveActivity(e);
				}
			}
			}
			return true;
		} catch (IOException ex) {
			System.out.println("failure while fetching json file :: " + ex);
			return false;
		} catch (Exception ex) {
			System.out.println("Exception :: " + ex);
			return false;
		}

		//return false;
	}

	
	//method to get 7 days statistics
	@Override
	public List<Map> fetchStatistics() {
		
		List<Map> result = (List<Map>) activityDao.fetchStatistics();
		
		return result;
	}


	/*method to fetch employee 24 hour activity data*/
	@Override
	public List<Map> fetchTodayAct() {
		List<Map> result = (List<Map>) activityDao.fetchTodayAct();
		return orderTodayAct(result);
	}
	
	
	/*method to format json as per today activity required*/
	public List<Map> orderTodayAct(List<Map> map){
		
		List<Map> lt = new LinkedList<Map>();
		Map resMap = new LinkedHashMap();
		
		TreeSet<Integer> set = new TreeSet<Integer>();
		
		for(Map m : map)
			set.add(Integer.valueOf((String) m.get("eid")));
		
		set = (TreeSet<Integer>) set.descendingSet();
		for(Integer s : set)
		{
			Map mm = new LinkedHashMap();
			
			mm.put("employee_id", s);
			List<Map> inLt = new LinkedList<Map>();
			for(Map m : map)
			{
				Map inMap = new LinkedHashMap();
				if(m.get("eid").equals(s.toString()))
				{
					inMap.put("name", m.get("name"));
					inMap.put("start_time", m.get("start_time"));
					inLt.add(inMap);
				}
			}
			mm.put("activities", inLt);
			lt.add(mm);
		}
		return lt;
		
	}
	
	
	

}
