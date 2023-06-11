package com.example.sandbox.store.inventory.get;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.constans.Tags.SMOKE;

public class inventoryTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testInventoryGet() {
        // get inventory - 200
        Response response200 = getUrl(inventory);
        Assertions.assertReturnCode(response200, 200);
    }
}
