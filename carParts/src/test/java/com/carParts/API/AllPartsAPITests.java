package com.carParts.API;

import com.carParts.model.entity.Part;
import com.carParts.service.impl.PartServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AllPartsAPITests {
    private final String TEST_NAME = "testName";
    private final double TEST_PRICE = 2.50;
    private final int TEST_QUANTITY = 1;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void allPartsAPITest() throws Exception {
        PartServiceImpl mockPartService = Mockito.mock();

        Part part = new Part();
        part.setName(TEST_NAME);
        part.setPrice(TEST_PRICE);
        part.setQuantity(TEST_QUANTITY);

        List<Part> parts = new ArrayList<>();
        parts.add(part);

        when(mockPartService.findAllParts()).thenReturn(parts);

        mockMvc.perform(get("/api/allParts"))
                .andExpect(status().isFound());

    }
}
