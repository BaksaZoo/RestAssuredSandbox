package com.example.sandbox.util;

import com.example.sandbox.util.swagger.definitions.Item;
import com.example.sandbox.util.swagger.definitions.OrderBody;
import com.example.sandbox.util.swagger.definitions.PetBody;
import com.example.sandbox.util.swagger.definitions.UserBody;

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

    public static OrderBody createOrderBody() {
        return OrderBody.builder()
                .id(generateRandomNumber())
                .petId(generateRandomNumber())
                .quantity(generateRandomNumber())
                .shipDate("2023-06-11T13:18:42.040Z")
                .status("placed")
                .complete(true)
                .build();
    }

    public static UserBody createUserBody() {
        return UserBody.builder()
                .id(generateRandomNumber())
                .username("baksazoo") // care: username must be posted in lowercase
                .password("123")
                .firstName("Zoltán")
                .lastName("Baksa")
                .email("baksazoo@hotmail.com")
                .phone("+36307246804")
                .userStatus(generateRandomNumber())
                .build();
    }
}
