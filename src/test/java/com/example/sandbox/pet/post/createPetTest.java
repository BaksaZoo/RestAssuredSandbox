package com.example.sandbox.pet.post;

import com.example.sandbox.Common;
import com.example.sandbox.util.body.pet.PostCreatePet;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createPetBody;
import static com.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class createPetTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testCreatePet() {
        PostCreatePet body = PostCreatePet.builder()
                .PetBody(createPetBody())
                .build();
        PostCreatePet emptyBody = PostCreatePet.builder().build();

        Response response200 = postUrl(newPet, createJsonBody(body));
        Assert.assertEquals(response200.getStatusCode(), 200, "Invalid response code");

        Response response405 = postUrl(newPet, createJsonBody(emptyBody));
        Assert.assertEquals(response405.getStatusCode(), 405, "Invalid response code");
    }
}
