package com.example.sandbox.store.order.delete;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.order.OrderBodyWrapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createOrderBody;
import static com.example.sandbox.util.body.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class deleteOrderByIdTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testDeleteOrderById() {
        OrderBodyWrapper body = OrderBodyWrapper.builder()
                .orderBody(createOrderBody())
                .build();

        String endpoint = orderById.replace("{orderId}", String.valueOf(body.getOrderBody().getId()));

        // create order
        postUrl(order, createJsonBody(body));

        // delete order - 200 and 404
        for (int i = 0; i < 2; i++) {
            Response response = deleteUrl(endpoint);
            Assertions.assertReturnCode(response, i < 1 ? 200 : 404);
        }
    }
}
