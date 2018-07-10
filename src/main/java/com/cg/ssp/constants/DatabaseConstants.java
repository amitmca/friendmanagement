package com.cg.ssp.constants;

/**
 * @author Amit Bhalerao
 * Database Queries
 */

public class DatabaseConstants {
	public static final String GET_FRIEND_LIST= "SELECT u.email FROM users u, user_operations o WHERE o.userid1 = ?  AND u.id = o.userid2 and operation_id = 1";
	
	public static final String CHECKE_FRIEND_ALREADY_PRESENT = "SELECT userid1,userid2 FROM user_operations where userid1 = ? and userid2 = ? and operation_id = ?";
	
	public static final String CREATE_FRIEND_REQUEST = "insert into user_operations(userid1,userid2,operation_id) values(?, ?, ?) ";
	
	public static final String GET_COMMON_FRIENDS = "SELECT u.email FROM users u,user_operations o WHERE u.id = o.userid2 and o.userid1 IN ( ?, ? ) GROUP BY o.userid2  HAVING COUNT( * ) = ( SELECT MAX(nor)  FROM (SELECT o.userid2, COUNT( * ) AS nor  FROM user_operations o GROUP BY o.userid2 ) user_operations ) ";
	
	public static final String SUBSCRIBE_FOR_UPDATES = "insert into user_operations(userid1,userid2,operation_id) values(?, ?, ?) ";
	
	public static final String BLOCK_UPDATES = "update user_operations set operation_id = ? where userid1 = ? and userid2 = ? and operation_id = 4 ";
	
	public static final String GET_ID_BY_EMAIL = "SELECT id FROM users where email = ? ";
}
