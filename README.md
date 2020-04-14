# JMS COMMUNICATION SYSTEM

## This is a communication system based on JMS framework and ActiveMQ message queue.

Current functions include: point-to-point communication, broadcast communication, and file transfer.

## How to use it?
First, you need to install and configure the ActiveMQ message queuing server.  
Then clone the code and generate the jar package.  
Using the command line to run the jar file and input parameters to activate the function you want to use.  
For example:java -jar xxx.jar fileSender.  

## Security verification        ---2020/4/11
Now we have joined the security verification process, and we need to enter the user name and password before starting the client,We also provide the function of account registration. This function is based on the persistence layer middleware -mybatis.
### Mybatis
Mybatis is an excellent persistence framework that supports customized SQL, stored procedures, and advanced mapping. Mybatis avoids almost all JDBC code and manual setting of parameters and getting result sets. Mybatis can use simple XML or annotation for configuration and native map to map interface and Java POJOs (plain old Java objects) to records in database.   

## Real-time translation!                                    ---2020/3/28
The point to point(p2p) communication supports real-time translation!   
This function is realized through Baidu translation API. After receiving each message, it will query the current system region and call Baidu translation API to translate the message to the current region's language.   

# This program is for STUDY purpose only！
