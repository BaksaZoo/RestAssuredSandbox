package com.example.sandbox.pet.get;

import com.example.sandbox.Common;
import com.example.sandbox.util.body.pet.PostCreatePet;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createPetBody;
import static com.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class petByIdTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testGetPetById() {
        PostCreatePet body = PostCreatePet.builder()
                .PetBody(createPetBody())
                .build();

        String endpoint = petById.replace("{petId}", String.valueOf(body.getPetBody().getId()));

        // create pet
        postUrl(newPet, createJsonBody(body));

        // get pet - 200
        Response response200 = getUrl(endpoint);
        Assert.assertEquals(response200.getStatusCode(), 200, "Invalid response code");

        // delete pet
        deleteUrl(endpoint);

        // get pet - 404
        Response response404 = getUrl(endpoint);
        Assert.assertEquals(response404.getStatusCode(), 404, "Invalid response code");
    }
}
