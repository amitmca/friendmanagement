package com.cg.ssp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.cg.ssp.model.FriendsMgmtRequestDO;
import com.cg.ssp.model.FriendsMgmtResponseDO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendsManagementApplicationTests {
	
	public static final String REST_SERVICE_URI = "http://localhost:8085/api/";

	// To test that application returns the list of friends for an email address.
	@Test
	public void testFriendsList() {
		RestTemplate restTemplate = new RestTemplate();
		FriendsMgmtRequestDO friendMgmtDO = new FriendsMgmtRequestDO();
		friendMgmtDO.setEmail("amit@gmail.com");
		ResponseEntity<FriendsMgmtResponseDO> friendsMgmtResponseDO = restTemplate.postForEntity( REST_SERVICE_URI+"/friendlist/", friendMgmtDO,FriendsMgmtResponseDO.class );
		String friends[] = friendsMgmtResponseDO.getBody().getFriends();
		Assert.assertEquals("kasturi@gmail.com", friends[0]);
		Assert.assertEquals(1, friendsMgmtResponseDO.getBody().getCount());
		Assert.assertEquals(true,friendsMgmtResponseDO.getBody().isSuccess());
	}
	
	// To test that application does not return anything when the user is not present
	@Test
	public void testFriendsListFalse() {
			RestTemplate restTemplate = new RestTemplate();
			FriendsMgmtRequestDO friendMgmtDO = new FriendsMgmtRequestDO();
			friendMgmtDO.setEmail("amit1@gmail.com");
			ResponseEntity<FriendsMgmtResponseDO> friendsMgmtResponseDO = restTemplate.postForEntity( REST_SERVICE_URI+"/friendlist/", friendMgmtDO,FriendsMgmtResponseDO.class );
			Assert.assertEquals(null, friendsMgmtResponseDO.getBody().getFriends());
			Assert.assertEquals(0, friendsMgmtResponseDO.getBody().getCount());
			Assert.assertEquals(false,friendsMgmtResponseDO.getBody().isSuccess());
		}
	
	// To test that application creates friend connection between two email addresses.
	@Test
	public void testCreateFriend() {
			RestTemplate restTemplate = new RestTemplate();
			FriendsMgmtRequestDO friendMgmtDO = new FriendsMgmtRequestDO();
			String friends[] = {"amit@gmail.com","amey@gmail.com"};
			friendMgmtDO.setFriends(friends);
			ResponseEntity<FriendsMgmtResponseDO> friendsMgmtResponseDO = restTemplate.postForEntity( REST_SERVICE_URI+"/friends/", friendMgmtDO,FriendsMgmtResponseDO.class );
			String resFriends[] = friendsMgmtResponseDO.getBody().getFriends();
			Assert.assertEquals("amit@gmail.com", resFriends[0]);
			Assert.assertEquals("amey@gmail.com", resFriends[1]);
			Assert.assertEquals(2, friendsMgmtResponseDO.getBody().getCount());
			Assert.assertEquals(true,friendsMgmtResponseDO.getBody().isSuccess());
		}
	
	// To test that application does not creates friend connection between two email addresses if they are already connected
	@Test
	public void testCreateDuplicateFriend() {
			RestTemplate restTemplate = new RestTemplate();
			FriendsMgmtRequestDO friendMgmtDO = new FriendsMgmtRequestDO();
			String friends[] = {"amit@gmail.com","amey@gmail.com"};
			friendMgmtDO.setFriends(friends);
			ResponseEntity<FriendsMgmtResponseDO> friendsMgmtResponseDO = restTemplate.postForEntity( REST_SERVICE_URI+"/friends/", friendMgmtDO,FriendsMgmtResponseDO.class );
			String resFriends[] = friendsMgmtResponseDO.getBody().getFriends();
			Assert.assertEquals(null, friendsMgmtResponseDO.getBody().getFriends());
			Assert.assertEquals(0, friendsMgmtResponseDO.getBody().getCount());
			Assert.assertEquals(false,friendsMgmtResponseDO.getBody().isSuccess());
		}
	
	// To test that application retrieve the common friends list between two email addresses.
	@Test
	public void testCommonFriends() {
			RestTemplate restTemplate = new RestTemplate();
			FriendsMgmtRequestDO friendMgmtDO = new FriendsMgmtRequestDO();
			String friends[] = {"amit@gmail.com","amod@gmail.com"};
			friendMgmtDO.setFriends(friends);
			ResponseEntity<FriendsMgmtResponseDO> friendsMgmtResponseDO = restTemplate.postForEntity( REST_SERVICE_URI+"/commonfriends/", friendMgmtDO,FriendsMgmtResponseDO.class );
			String resFriends[] = friendsMgmtResponseDO.getBody().getFriends();
			Assert.assertEquals("amey@gmail.com", resFriends[0]);
			Assert.assertEquals("kasturi@gmail.com", resFriends[1]);
			Assert.assertEquals(2, friendsMgmtResponseDO.getBody().getCount());
			Assert.assertEquals(true,friendsMgmtResponseDO.getBody().isSuccess());
		}
	
	// To test that application does not retrieve anything if there are no common friends list between two email addresses.
	@Test
	public void testCommonFriendsFalse() {
			RestTemplate restTemplate = new RestTemplate();
			FriendsMgmtRequestDO friendMgmtDO = new FriendsMgmtRequestDO();
			String friends[] = {"amit@gmail.com","amey@gmail.com"};
			friendMgmtDO.setFriends(friends);
			ResponseEntity<FriendsMgmtResponseDO> friendsMgmtResponseDO = restTemplate.postForEntity( REST_SERVICE_URI+"/commonfriends/", friendMgmtDO,FriendsMgmtResponseDO.class );
			Assert.assertEquals(null, friendsMgmtResponseDO.getBody().getFriends());
			Assert.assertEquals(0, friendsMgmtResponseDO.getBody().getCount());
			Assert.assertEquals(false,friendsMgmtResponseDO.getBody().isSuccess());
		}
	
	// To test that application subscribe to updates from an email address
	@Test
	public void testSubscribeForUpdates() {
			RestTemplate restTemplate = new RestTemplate();
			
			FriendsMgmtRequestDO friendMgmtDO = new FriendsMgmtRequestDO();
			friendMgmtDO.setRequestor("amit@gmail.com");
			friendMgmtDO.setTarget("amey@gmail.com");
			
			HttpEntity<FriendsMgmtRequestDO> entity = new HttpEntity<FriendsMgmtRequestDO>(friendMgmtDO);
			ResponseEntity<FriendsMgmtResponseDO> friendsMgmtResponseDO = restTemplate.exchange( REST_SERVICE_URI+"/subscribe/", HttpMethod.PUT, entity, FriendsMgmtResponseDO.class);
		    
			Assert.assertEquals(null, friendsMgmtResponseDO.getBody().getFriends());
			Assert.assertEquals(0, friendsMgmtResponseDO.getBody().getCount());
			Assert.assertEquals(true,friendsMgmtResponseDO.getBody().isSuccess());
		}
	
	// To test that application does not allows to subscribe to updates from an email address if it is already subscribed
	@Test
	public void testSubscribeForUpdatesFalse() {
			RestTemplate restTemplate = new RestTemplate();
			
			FriendsMgmtRequestDO friendMgmtDO = new FriendsMgmtRequestDO();
			friendMgmtDO.setRequestor("amit@gmail.com");
			friendMgmtDO.setTarget("amey@gmail.com");
			
			HttpEntity<FriendsMgmtRequestDO> entity = new HttpEntity<FriendsMgmtRequestDO>(friendMgmtDO);
			ResponseEntity<FriendsMgmtResponseDO> friendsMgmtResponseDO = restTemplate.exchange( REST_SERVICE_URI+"/subscribe/", HttpMethod.PUT, entity, FriendsMgmtResponseDO.class);
		    
			Assert.assertEquals(null, friendsMgmtResponseDO.getBody().getFriends());
			Assert.assertEquals(0, friendsMgmtResponseDO.getBody().getCount());
			Assert.assertEquals(false,friendsMgmtResponseDO.getBody().isSuccess());
		}
	
	// To test that application Blocks updates from an email address
	@Test
	public void testBlockForUpdates() {
			RestTemplate restTemplate = new RestTemplate();
			
			FriendsMgmtRequestDO friendMgmtDO = new FriendsMgmtRequestDO();
			friendMgmtDO.setRequestor("amit@gmail.com");
			friendMgmtDO.setTarget("amey@gmail.com");
			
			HttpEntity<FriendsMgmtRequestDO> entity = new HttpEntity<FriendsMgmtRequestDO>(friendMgmtDO);
			ResponseEntity<FriendsMgmtResponseDO> friendsMgmtResponseDO = restTemplate.exchange( REST_SERVICE_URI+"/block/", HttpMethod.PUT, entity, FriendsMgmtResponseDO.class);
		    
			Assert.assertEquals(null, friendsMgmtResponseDO.getBody().getFriends());
			Assert.assertEquals(0, friendsMgmtResponseDO.getBody().getCount());
			Assert.assertEquals(true,friendsMgmtResponseDO.getBody().isSuccess());
		}
	
	// To test that application does not allows to Blocks updates from an email address which is already blocked
	@Test
	public void testBlockForUpdatesFalse() {
			RestTemplate restTemplate = new RestTemplate();
			
			FriendsMgmtRequestDO friendMgmtDO = new FriendsMgmtRequestDO();
			friendMgmtDO.setRequestor("amit@gmail.com");
			friendMgmtDO.setTarget("amey@gmail.com");
			
			HttpEntity<FriendsMgmtRequestDO> entity = new HttpEntity<FriendsMgmtRequestDO>(friendMgmtDO);
			ResponseEntity<FriendsMgmtResponseDO> friendsMgmtResponseDO = restTemplate.exchange( REST_SERVICE_URI+"/block/", HttpMethod.PUT, entity, FriendsMgmtResponseDO.class);
		    
			Assert.assertEquals(null, friendsMgmtResponseDO.getBody().getFriends());
			Assert.assertEquals(0, friendsMgmtResponseDO.getBody().getCount());
			Assert.assertEquals(false,friendsMgmtResponseDO.getBody().isSuccess());
		}
}
