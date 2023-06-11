package com.example.sandbox.pet.post;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.pet.PetBodyWrapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createPetBody;
import static com.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class updatePetByIdTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetUpdateById() {
        PetBodyWrapper body = PetBodyWrapper.builder()
                .petBody(createPetBody())
                .build();

        String endpoint = petById.replace("{petId}", String.valueOf(body.getPetBody().getId()));

        // create pet
        postUrl(newPet, createJsonBody(body));

        // update pet - 200
        String newPetName = "not" + body.getPetBody().getName();
        String newPetStatus = "sold";
        Response response200 = postUrlForm(endpoint, "name", newPetName, "status", newPetStatus, false);
        Assertions.assertReturnCode(response200, 200);

        // delete pet
        deleteUrl(endpoint);

        // update pet - 404
        Response response404 = postUrlForm(endpoint, "name", newPetName, "status", newPetStatus, false);
        Assertions.assertReturnCode(response404, 404);
    }
}
