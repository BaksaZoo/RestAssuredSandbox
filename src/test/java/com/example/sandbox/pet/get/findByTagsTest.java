package com.example.sandbox.pet.get;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.constans.Tags.SMOKE;

public class findByTagsTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetFindByTags() {
        Response response200 = getUrl(findByTags + "?tags=string");
        Assertions.assertReturnCode(response200, 200);

//        Response response400 = getUrl(findByTags);
//        Assert.assertEquals(response400.getStatusCode(), 400, "Invalid response code");
    }
}
