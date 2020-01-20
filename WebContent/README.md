### Guide to installing 
* A copy of JDK/JRE 8 + eclipse
* A built in or external container like Apache Tomcat 8.x +

* Oracle Database with the name sheku and password sheku

* Given the sql(.sql) file under /WEB-INF/script/, Import the file into your database and you have your tables ready
* Try to change the username and passwords as per your database users for both of the file 
1)Assignment\WebContent\WEB-INF\config\database-config.xml
2)Assignment\src\database-config.xml

Assignment no 1:-
step 1) To insert JSON record in database, paste the json file on path "Assignment\src\JSON" in 		".json".
Step 2) Run "EmployeeStoreData.java" file to read, validate and dump the data in database.
		Path:- "Assignment\src\com\task\app\EmployeeStoreData.java"
		
Assignment no 2:-
Step 1) Run the project as a server.
Step 2) enter url "http://localhost:8080/Assignment/activityList.htm" to get result
