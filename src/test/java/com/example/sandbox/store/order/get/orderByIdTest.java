package com.example.sandbox.store.order.get;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.pet.OrderBodyWrapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createOrderBody;
import static com.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class orderByIdTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testGetOrderById() {
        OrderBodyWrapper body = OrderBodyWrapper.builder()
                .orderBody(createOrderBody())
                .build();

        String endpoint = orderById.replace("{orderId}", String.valueOf(body.getOrderBody().getId()));

        // create order
        postUrl(order, createJsonBody(body));

        // get order - 200
        Response response200 = getUrl(endpoint);
        Assertions.assertReturnCode(response200, 200);

        // delete order
        deleteUrl(endpoint);

        // get order - 404
        Response response404 = getUrl(endpoint);
        Assertions.assertReturnCode(response404, 404);
    }
}
