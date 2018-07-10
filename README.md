## Friend Management Api
This is an application with a need to build its own social network, "Friends Management" is a common requirement which usually starts off simple but can grow in complexity depending on the application's use case. Usually, the application compromised of features like "Friend", "Unfriend", "Block", "Receive Updates" etc.

###### Technology Stack:
1)	Java
2)	J2EE
3)	Spring 
  1.	Spring REST
  2.	Spring BOOT
  3.	Spring DATA
4)	MySQL
5)	Postman

###### Database Structure 
1) ######	Operations  :  Table which represents the operations that a user can perform

CREATE TABLE IF NOT EXISTS `operations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

![opeations](https://user-images.githubusercontent.com/1614865/42512167-88df6944-8471-11e8-945b-69b5bc86c9f4.PNG)

2) ###### Users : Table which stores user details like  name,contactno,email

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `contactno` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

![usertableview](https://user-images.githubusercontent.com/1614865/42512104-580e6b3a-8471-11e8-80b3-cd19d09db553.PNG)

3) ###### User_operations : Table to store the operations performed by a user
CREATE TABLE IF NOT EXISTS `user_operations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid1` int(11) NOT NULL,
  `userid2` int(11) NOT NULL,
  `operation_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid1` (`userid1`),
  KEY `userid2` (`userid2`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1

ALTER TABLE `user_operations`
  ADD CONSTRAINT `user_operations_ibfk_1` FOREIGN KEY (`userid1`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `user_operations_ibfk_2` FOREIGN KEY (`userid2`) REFERENCES `users` (`id`);

### List of REST Endpoints and Screens
1) ###### Create friend connection between 2 users
 ![createnew](https://user-images.githubusercontent.com/1614865/42512528-76c601ea-8472-11e8-9ca6-78a84126f31b.PNG)
   
2)  ###### Return list of friends for a user
![listall](https://user-images.githubusercontent.com/1614865/42512567-901babea-8472-11e8-93d2-bf60ea52bc0e.PNG)

3) ###### Return Common friends connection between 2 users
![common](https://user-images.githubusercontent.com/1614865/42513143-d8eac288-8473-11e8-9771-3371dc97c745.PNG)

4) ###### Subscribe for updates from a friend
![subscribe](https://user-images.githubusercontent.com/1614865/42513301-3829fcc8-8474-11e8-8a9c-042185c2f239.PNG)

5) ###### Block updates from a friend
![block](https://user-images.githubusercontent.com/1614865/42513392-70ce3300-8474-11e8-9df9-12c3d790e19d.PNG)

### List of Excpetions and Screens
1) ###### Friend Connection Already exists
![falreadyexists](https://user-images.githubusercontent.com/1614865/42513659-1f12351a-8475-11e8-8d63-9ce2b8a71145.PNG)

2) ###### User Email does not exists
![image](https://user-images.githubusercontent.com/1614865/42513784-6ad1b8c2-8475-11e8-91ba-ab5db0557dbc.png)

3) ###### No common friends between 2 users
![image](https://user-images.githubusercontent.com/1614865/42513821-880c60e0-8475-11e8-8f33-4f25683c99a5.png)

4) ###### Subscrition for updates already exists
![image](https://user-images.githubusercontent.com/1614865/42514005-f9a1e2de-8475-11e8-9576-e7fde901a528.png)

5) ###### Updates from this friend are already blocked
![image](https://user-images.githubusercontent.com/1614865/42514091-2e638978-8476-11e8-99c1-be9841a95c7b.png)


