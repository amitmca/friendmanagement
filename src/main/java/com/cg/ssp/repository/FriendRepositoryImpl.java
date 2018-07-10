package com.cg.ssp.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import com.cg.ssp.constants.DatabaseConstants;

/**
 * @author Amit Bhalerao
 * Friend Repository Implementation
 */

@Transactional
public class FriendRepositoryImpl implements FriendRepository{
	@PersistenceContext
	private EntityManager entityManager;  

	@SuppressWarnings("unchecked")
	public List<String> getFriendsList(String from) {
		int userid =this.getUseridbyEmail(from);
		Query query = entityManager.createNativeQuery(DatabaseConstants.GET_FRIEND_LIST);
		query.setParameter(1, userid);
		return query.getResultList();
	}
	
	public int checkExistance(String friend1, String friend2,int operation) {
		
		int user1 =this.getUseridbyEmail(friend1);
		int user2 =this.getUseridbyEmail(friend2);
		
		Query query = entityManager.createNativeQuery(DatabaseConstants.CHECKE_FRIEND_ALREADY_PRESENT);
		query.setParameter(1, user1);
		query.setParameter(2, user2);
		query.setParameter(3, operation);
		
		int size = query.getResultList().size();
		return size;
	}
	
	public int createFriendRequest(String from, String to) {
		int user1 =this.getUseridbyEmail(from);
		int user2 =this.getUseridbyEmail(to);
		
		Query query = entityManager.createNativeQuery(DatabaseConstants.CREATE_FRIEND_REQUEST);
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
		
		Query query = entityManager.createNativeQuery(DatabaseConstants.GET_COMMON_FRIENDS);
		
		query.setParameter(1, user1);
	    query.setParameter(2, user2);
		return query.getResultList();
	}
	
	public int subscribeForUpdates(String requestor, String target) {
		int user1 =this.getUseridbyEmail(requestor);
		int user2 =this.getUseridbyEmail(target);
		
		Query query = entityManager.createNativeQuery(DatabaseConstants.SUBSCRIBE_FOR_UPDATES);
		query.setParameter(1, user1);
	    query.setParameter(2, user2);
	    query.setParameter(3, 4);
	    
		int size = query.executeUpdate();
		return size;
	}
	
	public int blockForUpdates(String requestor, String target) {
		int user1 =this.getUseridbyEmail(requestor);
		int user2 =this.getUseridbyEmail(target);
						
		Query query = entityManager.createNativeQuery(DatabaseConstants.BLOCK_UPDATES);
		query.setParameter(1, 3);
		query.setParameter(2, user1);
	    query.setParameter(3, user2);
	    
		int size = query.executeUpdate();
		return size;
	}

	public int getUseridbyEmail(String email) {
		Query query = entityManager.createNativeQuery(DatabaseConstants.GET_ID_BY_EMAIL);
		query.setParameter(1, email);
		List<Integer> result = query.getResultList();
		if(result.isEmpty())
			return -1;
		else
		return (int) result.get(0);
	}
	
}