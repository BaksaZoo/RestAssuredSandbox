package com.example.sandbox.pet.post;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.pet.PetBodyWrapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createPetBody;
import static com.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class createPetTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testCreatePet() {
        PetBodyWrapper body = PetBodyWrapper.builder()
                .petBody(createPetBody())
                .build();
        PetBodyWrapper emptyBody = PetBodyWrapper.builder().build();

        Response response200 = postUrl(newPet, createJsonBody(body));
        Assertions.assertReturnCode(response200, 200);

        Response response405 = postUrl(newPet, createJsonBody(emptyBody));
        Assertions.assertReturnCode(response405, 405);
    }
}
