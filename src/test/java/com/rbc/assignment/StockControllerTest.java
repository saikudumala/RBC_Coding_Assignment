package com.rbc.assignment;

import com.rbc.assignment.model.Stock;
import com.rbc.assignment.repository.StocksRepo;
import com.rbc.assignment.service.StockService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;


import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StockControllerTest extends DemoApplicationTests{

    @Test
    public void parseMultipartFile_Should_Return_Ok() throws Exception {
        Resource fileResource = new ClassPathResource("/dow_jones_index.csv");
        MockMultipartFile multipartFile = new MockMultipartFile("file", fileResource.getFilename(),"text/csv", fileResource.getInputStream());
        this.mvc.perform(multipart("/stocks/upload").file(multipartFile))
                .andExpect(status().isOk());

    }


}
