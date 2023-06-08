package com.example.sandbox.util;

import com.example.sandbox.util.swagger.definitions.Item;
import com.example.sandbox.util.swagger.definitions.PetBody;

import static com.example.sandbox.util.constans.TestData.HYDRAIMAGE;

public class Tools {
    public static int generateRandomNumber() {
        return (int) (Math.random() * 100);
    }

    public static PetBody createPetBody() {
        return PetBody.builder()
                .id(generateRandomNumber())
                .category(Item.builder()
                        .id(1)
                        .name("Hydra")
                        .build())
                .name("Princess")
                .photoUrl(HYDRAIMAGE)
                .tag(Item.builder()
                        .id(2)
                        .name("cute")
                        .build())
                .status("available")
                .build();
    }
}
