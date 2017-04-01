# Comments-sockets
Prototype for teammates comments+ project.<br/>

About:-<br/>
Real time commenting System.<br/>

Technologies & languages used:-<br/>
1)Java<br/>
2)HTML,CSS,Javascript<br/>
3)SQL<br/>


Requirements for Setting up:-<br/>
1)Eclipse IDE for Java EE Developers(http://www.eclipse.org/downloads/packages/release/Luna/SR2)<br/>
2)Apache tomcat 7(http://tomcat.apache.org/download-70.cgi)<br/>
3)Jdk 1.7<br/>
4)Mysql<br/>

Setting up Mysql:-<br/>
1)Create a new Database in Mysql named as comments.<br/>
2)Clone the repo.<br/>
3)Go to clone repo directory and open database folder and import comments.sql in your database.<br/>


Procedure:-<br/>
1)Open Eclipse IDE.
2)Click File->Import->General->Existing projects into workspace.<br/>
3)Browse to clone repo and select Comments from the directory.<br/>
4)Click Finish.<br/>
5)Right Click on Comments Project and select Run as->Run on Server.<br/>
6)Select Apache Tomcat 7.0 Server.<br/>
7)Browse to apache Tomcat directory and Select Finish.<br/>
8)An error will occur,Next Click on servers folder on left side select context.xml<br/>
9)Paste the contents from context.txt from clone repo before the end of Context tag in context.xml and change username and password with your mysql username and password.(default is username = "root" and password = "root")<br/>
  10)Now Right click on Comments->Run as->Run on Server.<br/>
  11)Open http://localhost:8080/Comments in your browser.<br/>
  12)Volla You did it.

Sample run:-<br/>
1)Open localhost:8080/Comments<br/>
2)Click login link on top right hand corner.<br/>
3)By default there exist three users,you can get the details from sql table.<br/>
  Example :-Email:mayank.harsani@gmail.com Password:mayank<br/>
4)Comment particularly for someone.<br/>
5)As soon as you comment, "View new Comments" buttons appear click and you will see your own comment.<br/>

How this project works:-<br/>
This project implements basic comment and reply system with additonal feature to know if the comment has been read.
How additional feature works is when user1 makes a comment for user2,user2 gets notified with a link.When user2 visits the link or clicks the notification icon ,the comment is termed as read by user2.


