Quick install 
If you already have the software and know how it works. Download the repo direct from GitHub and 
tell youre chosen ide to clone it from version control. 
1. Open pgAdmin and create a new database (project name is gamenight)
2. Open chosen Ide and navigate to : src/main/resources/application.properties 


3. Line 3: the port 5432 if default is not chosen and gamenight if other database name is 
chosen. 
4. Line 4 and 5 for your username and password of postgres. 
5. Open pom.xml and reload project
6. Open postman and import the 2 files in : src\main\resources\postman\
7. Change the environment to Localhost. 
Postman is set up to automate, the environment Localhost is used to store variables, witch are being 
used throughout post man. JWTokens are stored automatically in {{Authtoken}} and used per folder
as inherit from parent
Other Localhost setting ? the variable {{localBasedUrl}} is used to store the local host settings, change 
the value in environment only.