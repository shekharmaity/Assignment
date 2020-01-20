package com.task.app;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.app.bean.Activities;
import com.task.app.bean.Employee;
import com.task.app.dao.EmployeeDaoImp;
import com.task.app.dao.IEmployeeDao;
import com.task.app.service.ActivityService;
import com.task.app.service.IActivityService;

public class EmployeeStoreData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IActivityService actServ = new ActivityService();
		actServ.saveActivityRecord();

	}
}
