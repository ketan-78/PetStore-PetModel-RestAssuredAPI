package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.ordersPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndpoints {

	public static Response placeOrder(ordersPOJO payload)
	{
		Response response=given()
			.body(payload)
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.post(routes.postURL);
		return response;
	}
	public static Response getOrder(int id)
	{
		Response response=given()
			.accept(ContentType.JSON)
			.pathParam("petId", id)
		.when()
			.get(routes.getURL);
		return response;
	}
	public static Response updateOrder(ordersPOJO payload)
	{
		Response response=given()
			.body(payload)
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.put(routes.postURL);
		return response;
	}
	public static Response deleteOrder(int id)
	{
		Response response=given()
			.accept(ContentType.JSON)
			.pathParam("petId", id)
		.when()
			.delete(routes.deleteURL);
		return response;
	}
}
