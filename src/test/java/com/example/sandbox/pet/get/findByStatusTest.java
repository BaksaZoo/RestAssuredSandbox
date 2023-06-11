package com.example.sandbox.pet.get;

import com.example.sandbox.Common;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static com.example.sandbox.util.constans.Tags.SMOKE;

public class findByStatusTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetFindByStatus() {
        Response response200 = getUrl(findByStatus, Map.of("status", "available"));
        Assert.assertEquals(response200.getStatusCode(), 200, "Invalid response code");

//        Response response405 = getUrl(findByStatus, Map.of("status", "non-existing-status"));
//        Assert.assertEquals(response405.getStatusCode(), 405, "Invalid response code");
    }
}