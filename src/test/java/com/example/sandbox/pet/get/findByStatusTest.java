package com.example.sandbox.pet.get;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static com.example.sandbox.util.constans.Tags.SMOKE;

public class findByStatusTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetFindByStatus() {
        Response response200 = getUrl(findByStatus, Map.of("status", "available"));
        Assertions.assertReturnCode(response200, 200);

//        Response response405 = getUrl(findByStatus, Map.of("status", "non-existing-status"));
//        Assert.assertEquals(response405.getStatusCode(), 405, "Invalid response code");
    }
}
