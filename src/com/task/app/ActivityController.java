package com.task.app;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.app.service.IActivityService;

@RestController
public class ActivityController {
	
	@Autowired
	IActivityService activityService;

	@GetMapping(value = "/activityList" )
	@ResponseBody
	public Map fetchActivityRecord() {
		System.out.println("-----------inside fetchActivityRecord controller-------------");
		Map result = new LinkedHashMap<>();
		
		List<Map> actLst = activityService.fetchStatistics();
		
		List<Map> todayLst = activityService.fetchTodayAct();
		 
		result.put("all_employees_last_7_days_statistics", actLst);
		result.put("todays_activites",todayLst);
		return result;
		
		
	}
}
