## Friend Management Api
This is an application with a need to build its own social network, "Friends Management" is a common requirement which usually starts off simple but can grow in complexity depending on the application's use case. Usually, the application compromised of features like "Friend", "Unfriend", "Block", "Receive Updates" etc.

###### Technology Stack:
1)	Java
2)	J2EE
3)	Spring 
  a)	Spring REST
  b)	Spring BOOT
  c)	Spring DATA
4)	MySQL
5)	Postman

###### Database Structure 
1) ######	Operations  :  Table which represents the operations that a user can perform

CREATE TABLE IF NOT EXISTS `operations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

2) ###### Users : Table which stores user details like  name,contactno,email

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `contactno` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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

### List of REST Endpoints and Explanation
1)	###### Return list of friends for a user
  



