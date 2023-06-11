package com.example.sandbox.pet.put;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.pet.PetBodyWrapper;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createPetBody;
import static com.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class updatePetTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetUpdate() {
        PetBodyWrapper body = PetBodyWrapper.builder()
                .petBody(createPetBody())
                .build();

        // create pet
        postUrl(newPet, createJsonBody(body));

        // update pet - 200
        JSONObject petBody = new JSONObject();
        String newPetName = "not-" + body.getPetBody().getName();
        petBody.put("name", newPetName);
        petBody.put("id", body.getPetBody().getId());
        Response response200 = putUrl(updatePet, petBody.toJSONString());
        Assertions.assertReturnCode(response200, 200);

        // check if name is updated
        String name = response200.jsonPath().get("name").toString();
        Assert.assertEquals(name, newPetName, "Created and updated names are not the same");

        // TODO: test 400, 404, 405
        // hint - 400: id < 0 returns 200, [id: string] returns 500 when trying to supply invalid id
        // hint - 404: deleting the pet wont result return 404 when trying to update it
        // hint - 405: missing body parts does not result in validation exception
    }
}
