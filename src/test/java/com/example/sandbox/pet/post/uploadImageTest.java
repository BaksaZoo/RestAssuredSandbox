package com.example.sandbox.pet.post;

import com.example.sandbox.Common;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.generateRandomNumber;
import static com.example.sandbox.util.constans.Tags.SMOKE;
import static com.example.sandbox.util.constans.TestData.HYDRAIMAGE;

public class uploadImageTest extends Common {

    @Test(enabled = true, groups = {SMOKE}, description = "description")
    public void testPetUploadImage() {
        Response response = postUrlForm(uploadImage.replace("{petId}", String.valueOf(generateRandomNumber())),
                "additionalMetadata", "test-metadata", "file", HYDRAIMAGE, true);
        Assert.assertEquals(response.getStatusCode(), 200, "Invalid response code");
    }
}
