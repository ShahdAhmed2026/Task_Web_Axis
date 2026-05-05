
### Prerequisites
Make sure you have:
Java (JDK 21)
Maven
Allure installed and added to Environment Variables

### 1. Clone the Repository

git clone https://github.com/ShahdAhmed2026/Task_Web_Axis.git
cd Task_Web_Axis

### 2.Open the project terminal and run:

Remove-Item -Recurse -Force target                

mvn clean test

allure serve allure-results
