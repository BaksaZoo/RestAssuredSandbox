package com.example.sandbox.pet.get;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.pet.PetBodyWrapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createPetBody;
import static com.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class petByIdTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testGetPetById() {
        PetBodyWrapper body = PetBodyWrapper.builder()
                .petBody(createPetBody())
                .build();

        String endpoint = petById.replace("{petId}", String.valueOf(body.getPetBody().getId()));

        // create pet
        postUrl(newPet, createJsonBody(body));

        // get pet - 200
        Response response200 = getUrl(endpoint);
        Assertions.assertReturnCode(response200, 200);

        // delete pet
        deleteUrl(endpoint);

        // get pet - 404
        Response response404 = getUrl(endpoint);
        Assertions.assertReturnCode(response404, 404);
    }
}
