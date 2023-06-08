package com.example.sandbox.businessProcesses;

import com.example.sandbox.Common;
import com.example.sandbox.util.body.pet.PostCreatePet;
import com.example.sandbox.util.swagger.definitions.Item;
import com.example.sandbox.util.swagger.definitions.PetBody;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static com.example.sandbox.util.Tools.createPetBody;
import static com.example.sandbox.util.Tools.generateRandomNumber;
import static com.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;
import static com.example.sandbox.util.constans.TestData.HYDRAIMAGE;

public class PetLifeCycle extends Common {
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

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetUploadImage() {
        Response response = postUrlForm(uploadImage.replace("{petId}", String.valueOf(generateRandomNumber())),
                "additionalMetadata", "test-metadata", "file", HYDRAIMAGE, true);
        Assert.assertEquals(response.getStatusCode(), 200, "Invalid response code");
    }

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetUpdate() {
        PostCreatePet body = PostCreatePet.builder()
                .PetBody(createPetBody())
                .build();

        // create pet
        postUrl(newPet, createJsonBody(body));

        // update pet - 200
        JSONObject petBody = new JSONObject();
        String newPetName = "not-" + body.getPetBody().getName();
        petBody.put("name", newPetName);
        petBody.put("id", body.getPetBody().getId());
        Response response200 = putUrl(updatePet, petBody.toJSONString());
        Assert.assertEquals(response200.getStatusCode(), 200, "Invalid response code");

        // check if name is updated
        String name = response200.jsonPath().get("name").toString();
        Assert.assertEquals(name, newPetName, "Created and updated names are not the same");

        // TODO: test 400, 404, 405
        // hint - 400: id < 0 returns 200, [id: string] returns 500 when trying to supply invalid id
        // hint - 404: deleting the pet wont result return 404 when trying to update it
        // hint - 405: missing body parts does not result in validation exception
    }

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetUpdateById() {
        PostCreatePet body = PostCreatePet.builder()
                .PetBody(createPetBody())
                .build();

        String endpoint = petById.replace("{petId}", String.valueOf(body.getPetBody().getId()));

        // create pet
        postUrl(newPet, createJsonBody(body));

        // update pet
        String newPetName = "not" + body.getPetBody().getName();
        String newPetStatus = "sold";
        Response response200 = postUrlForm(endpoint, "name", newPetName, "status", newPetStatus, false);
        Assert.assertEquals(response200.getStatusCode(), 200, "Invalid response code");

        // delete pet
        deleteUrl(endpoint);

        Response response404 = postUrlForm(endpoint, "name", newPetName, "status", newPetStatus, false);
        Assert.assertEquals(response404.getStatusCode(), 404, "Invalid response code");
    }

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetFindByStatus() {
        Response response200 = getUrl(findByStatus, Map.of("status", "available"));
        Assert.assertEquals(response200.getStatusCode(), 200, "Invalid response code");

//        Response response405 = getUrl(findByStatus, Map.of("status", "non-existing-status"));
//        Assert.assertEquals(response405.getStatusCode(), 405, "Invalid response code");
    }

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetFindByTags() {
        Response response200 = getUrl(findByTags + "?tags=string");
        Assert.assertEquals(response200.getStatusCode(), 200, "Invalid response code");

//        Response response400 = getUrl(findByTags);
//        Assert.assertEquals(response400.getStatusCode(), 400, "Invalid response code");
    }

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetDeleteById() {
        PostCreatePet body = PostCreatePet.builder()
                .PetBody(createPetBody())
                .build();

        // create pet
        postUrl(newPet, createJsonBody(body));

        // delete pet
        for (int i = 0; i < 2; i++) {
            Response response = deleteUrl(petById.replace("{petId}", String.valueOf(body.getPetBody().getId())));
            Assert.assertEquals(response.getStatusCode(), i < 1 ? 200 : 404, "Invalid response code");
        }
    }
}
