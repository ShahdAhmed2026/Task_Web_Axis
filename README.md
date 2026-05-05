
### Prerequisites
Make sure you have:
Java (JDK 21)
Maven
Allure installed and added to Environment Variables

if you don't have Allure installed ---> you can install it using the following link:
https://github.com/allure-framework/allure2/releases/tag/2.39.0 
choose allure-2.x.x.zip
then add the bin folder to your system Variables (Path)

### 1. Clone the Repository

git clone https://github.com/ShahdAhmed2026/Task_Web_Axis.git
cd Task_Web_Axis


### 2. Open project terminal then Run Tests
mvn clean test


### 3. Generate and Open Allure Report

allure serve target/allure-results
