package com.example.sandbox.store.order.post;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.order.OrderBodyWrapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createOrderBody;
import static com.example.sandbox.util.body.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class createOrderTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testCreateOrder() {
        OrderBodyWrapper body = OrderBodyWrapper.builder()
                .orderBody(createOrderBody())
                .build();

        // create order - 200
        Response response200 = postUrl(order, createJsonBody(body));
        Assertions.assertReturnCode(response200, 200);

        // create order - 400
        Response response400 = postUrl(order, createJsonBody(OrderBodyWrapper.builder().build()));
        Assertions.assertReturnCode(response400, 400);
    }
}
