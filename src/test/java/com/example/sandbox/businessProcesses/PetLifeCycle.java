package com.example.sandbox.businessProcesses;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.pet.PetBodyWrapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.createPetBody;
import static com.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;

public class PetLifeCycle extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetLifecycle(){
        PetBodyWrapper body = PetBodyWrapper.builder()
                .petBody(createPetBody())
                .build();

        String petByIdEndpoint = petById.replace("{petId}", String.valueOf(body.getPetBody().getId()));

        // create pet
        Response createResponse = postUrl(newPet, createJsonBody(body));
        Assertions.assertReturnCode(createResponse, 200);

        // update pet
        String newPetName = "not " + body.getPetBody().getName();
        String newPetStatus = "sold";
        Response updateResponse = postUrlForm(petByIdEndpoint, "name", newPetName, "status", newPetStatus, false);
        Assertions.assertReturnCode(updateResponse, 200);
        // check if pet is updated
        Response getResponse = getUrl(petByIdEndpoint);
        Assertions.assertReturnCode(getResponse, 200);
        Assert.assertEquals(getResponse.jsonPath().get("name"), newPetName, "Pet name did not get updated");
        Assert.assertEquals(getResponse.jsonPath().get("status"), newPetStatus, "Pet status did not get updated");

        // delete pet
        Response deleteResponse = deleteUrl(petByIdEndpoint);
        Assertions.assertReturnCode(deleteResponse, 200);
        // check if pet is deleted
        getResponse = getUrl(petByIdEndpoint);
        Assertions.assertReturnCode(getResponse, 404);
    }
}
