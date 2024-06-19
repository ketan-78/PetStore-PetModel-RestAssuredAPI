package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndpoints;
import api.payload.Category;
import api.payload.ordersPOJO;
import io.restassured.response.Response;

public class orderTests {
	
	Faker faker=new Faker();
	ordersPOJO userPayload=new ordersPOJO();
	Category ob=new Category();
		
	@BeforeMethod
	public void category()
	{		
		ob.setId(faker.idNumber().hashCode());
		ob.setName(faker.name().fullName());
	}
	@BeforeMethod
	public void dataSetup()
	{
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setCategory(ob);
		userPayload.setName(faker.animal().name());
		userPayload.setStatus("available");
	}
	
	@Test(priority=1)
	public void postOrder()

	{
		Response response=userEndpoints.placeOrder(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json");
	}
	@Test(priority=2)
	public void getOrder()
	{
		Response response=userEndpoints.getOrder(userPayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.getContentType(),"application/json");
	}
	@Test(priority=3)
	public void updateOrder()
	{
		userPayload.setName(faker.animal().name());
		userPayload.setStatus("unavailable");
		Response response=userEndpoints.updateOrder(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json");
	}
	@Test(priority=4)
	public void deleteOrder()
	{
		Response response=userEndpoints.deleteOrder(userPayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json");
	}
}
