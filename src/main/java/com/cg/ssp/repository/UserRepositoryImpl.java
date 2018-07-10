package com.cg.ssp.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserRepositoryImpl implements UsersRepositoryCustom{
	@PersistenceContext
	private EntityManager entityManager;  

	@SuppressWarnings("unchecked")
	public List<String> getFriendsList(String from) {
		int userid =this.getUseridbyEmail(from);
		Query query = entityManager.createNativeQuery("SELECT u.email FROM users u, user_operations o WHERE o.userid1 = ?  AND u.id = o.userid2");
		query.setParameter(1, userid);
		return query.getResultList();
	}
	
	public int checkExistance(String friend1, String friend2,int operation) {
		
		int user1 =this.getUseridbyEmail(friend1);
		int user2 =this.getUseridbyEmail(friend2);
		
		Query query = entityManager.createNativeQuery("SELECT userid1,userid2 FROM user_operations where userid1 = ? and userid2 = ? and operation_id = ? ");
		query.setParameter(1, user1);
		query.setParameter(2, user2);
		query.setParameter(3, operation);
		
		int size = query.getResultList().size();
		return size;
	}
	
	public int createFriendRequest(String from, String to) {
		int user1 =this.getUseridbyEmail(from);
		int user2 =this.getUseridbyEmail(to);
		
		Query query = entityManager.createNativeQuery("insert into user_operations(userid1,userid2,operation_id) values(?, ?, ?) ");
		query.setParameter(1, user1);
	    query.setParameter(2, user2);
	    query.setParameter(3, 1);
	    
		int size = query.executeUpdate();
		return size;
	}

	@SuppressWarnings("unchecked")
	public List<String> getCommonFriends(String from ,String to){
		
		int user1 =this.getUseridbyEmail(from);
		int user2 =this.getUseridbyEmail(to);
		
		Query query = entityManager.createNativeQuery("SELECT u.email "+
		" FROM users u,user_operations o "+
		" WHERE u.id = o.userid2 and o.userid1 IN ( ?, ? ) GROUP BY o.userid2  HAVING COUNT( * ) = ( SELECT MAX(nor)  FROM (SELECT o.userid2, COUNT( * ) AS nor "+
		" FROM user_operations o GROUP BY o.userid2 ) user_operations ) ");
		
		query.setParameter(1, user1);
	    query.setParameter(2, user2);
		return query.getResultList();
	}
	
	public int subscribeForUpdates(String requestor, String target) {
		int user1 =this.getUseridbyEmail(requestor);
		int user2 =this.getUseridbyEmail(target);
		
		Query query = entityManager.createNativeQuery("insert into user_operations(userid1,userid2,operation_id) values(?, ?, ?) ");
		query.setParameter(1, user1);
	    query.setParameter(2, user2);
	    query.setParameter(3, 4);
	    
		int size = query.executeUpdate();
		return size;
	}
	
	public int blockForUpdates(String requestor, String target) {
		int user1 =this.getUseridbyEmail(requestor);
		int user2 =this.getUseridbyEmail(target);
						
		Query query = entityManager.createNativeQuery("update user_operations set operation_id = ? where userid1 = ? and userid2 = ? and operation_id = 4 ");
		query.setParameter(1, 3);
		query.setParameter(2, user1);
	    query.setParameter(3, user2);
	    
		int size = query.executeUpdate();
		return size;
	}

	public int getUseridbyEmail(String email) {
		Query query = entityManager.createNativeQuery("SELECT id FROM users where email = ? ");
		query.setParameter(1, email);
		List<Integer> result = query.getResultList();
		if(result.isEmpty())
			return -1;
		else
		return (int) result.get(0);
	}
	
}