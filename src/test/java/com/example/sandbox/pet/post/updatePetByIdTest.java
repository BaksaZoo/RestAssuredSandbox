package com.example.sandbox.pet.post;

import com.example.sandbox.Common;
import com.example.sandbox.util.body.pet.PostCreatePet;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createPetBody;
import static com.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class updatePetByIdTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetUpdateById() {
        PostCreatePet body = PostCreatePet.builder()
                .PetBody(createPetBody())
                .build();

        String endpoint = petById.replace("{petId}", String.valueOf(body.getPetBody().getId()));

        // create pet
        postUrl(newPet, createJsonBody(body));

        // update pet - 200
        String newPetName = "not" + body.getPetBody().getName();
        String newPetStatus = "sold";
        Response response200 = postUrlForm(endpoint, "name", newPetName, "status", newPetStatus, false);
        Assert.assertEquals(response200.getStatusCode(), 200, "Invalid response code");

        // delete pet
        deleteUrl(endpoint);

        // update pet - 404
        Response response404 = postUrlForm(endpoint, "name", newPetName, "status", newPetStatus, false);
        Assert.assertEquals(response404.getStatusCode(), 404, "Invalid response code");
    }
}