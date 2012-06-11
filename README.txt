Functional specs
------------------
As user, I should access to the course catalog:
	* should see only active courses
	* list of title, level and hours
	* possibility to order by title column
	* paginated list
As editor, I should edit, create and delete courses. The info for each course 
should be:
	* If is active
	* Professor in the academy, could be not assigned
	* Title, required, less than 30 characters
	* Hour number, between 5 and 75
	* Course level must be one of: Basic, Intermediate, Advanced
	* Possibility to upload a pdf file with course contents

Architecture
---------------
* Maven: for the configuration management (dependencies, lyfe cycle,...)
* Jetty/Heroku: developing on jetty in order to deploy on Heroku
* HSQLDB: In-memory data base with initial import
* JPA2/Hibernate: Data storing with JPA2, hibernate implementation
* Spring3: as Dependency Injection container and Transactions management
* JSF2: as MVC framework
* Primefaces: as JSF component library

Steps
-------
---Model----
1. Initial Model
2. JPA2 annotations
3. Create in-memo persistence.xml
4. Initial load with import.sql
5. Testing the model
6. Create Storing services Spring/JPA2 annotations
7. Test Storing Services
----Web----
1. Web listing courses
2. Dialog edit/save title
3. Edit/save professor with selectOneMenu
4. Nobody professor selection possibility
5. Add new courses
6. Add level combo
7. Add isActive checkbox
8. Add hours spinner
9. Add fileUpload for contents
10. Add fileDownload
11. Add delete course column
----Validation----
1. Bean/Hibernate Validations
2. Title, required, less than 50 characters
3. Hour number, between 5 and 75
4. Ensure error message on each field
----Security----
1. Model for User/Roles support on database
2. JAAS support configurations for the container
3. Add login form in header
4. Ensure read-only access for anonymous users
5. Ensure edit users for editor users