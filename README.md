# Hotel Reservation System

**Option 20**

The client fills out the Application, indicating the number of seats in the room, class of apartments and time of stay.   
The administrator views the received Application, selects the most suitable of the Available Numbers, 
after which the system issues an Invoice to the Client.

**Вариант 20**

Клиент заполняет Заявку, указывая количество мест в номере, класс апартаментов и время пребывания.  
Администратор просматривает поступившую Заявку, выделяет наиболее подходящий из доступных Номеров, после чего система выставляет Счет Клиенту. 

## Installation and Running

**_Requirements:_**
* JDK 1.8
* Apache Maven
* PostgreSql

**_Running the project:_**
1. Clone this project to your local repository
2. Create database with next command:
    ```sql
    CREATE DATABASE "hotel-reservation-servlet"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Ukrainian_Ukraine.1251'
    LC_CTYPE = 'Ukrainian_Ukraine.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    ```
3. Run scripts from **/resources/db/** folder to create tables and to insert data
4. Update data source information in **src/main/java/ua/polina/model/dao/implementation/ConnectionPool.java**
5. Run `mvn tomcat7:run` from project root folder
5. The project will start on 8080 port. Use http://localhost:8088/ to view web application

**_Default users:_**

ROLE | USERNAME | PASSWORD
---- | -------- | --------
ADMIN | admin | admin
CLIENT | test1 | test1
