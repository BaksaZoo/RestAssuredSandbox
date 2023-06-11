package com.example.sandbox.pet.delete;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.pet.PetBodyWrapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createPetBody;
import static com.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class deletePetByIdTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetDeleteById() {
        PetBodyWrapper body = PetBodyWrapper.builder()
                .petBody(createPetBody())
                .build();

        // create pet
        postUrl(newPet, createJsonBody(body));

        // delete pet 200 and 404
        for (int i = 0; i < 2; i++) {
            Response response = deleteUrl(petById.replace("{petId}", String.valueOf(body.getPetBody().getId())));
            Assertions.assertReturnCode(response, i < 1 ? 200 : 404);
        }
    }
}
