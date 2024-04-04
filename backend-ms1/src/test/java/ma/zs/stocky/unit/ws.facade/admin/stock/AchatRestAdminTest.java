package ma.zs.stocky.unit.ws.facade.admin.stock;

import ma.zs.stocky.bean.core.stock.Achat;
import ma.zs.stocky.service.impl.admin.stock.AchatAdminServiceImpl;
import ma.zs.stocky.ws.facade.admin.stock.AchatRestAdmin;
import ma.zs.stocky.ws.converter.stock.AchatConverter;
import ma.zs.stocky.ws.dto.stock.AchatDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AchatRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private AchatAdminServiceImpl service;
    @Mock
    private AchatConverter converter;

    @InjectMocks
    private AchatRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllAchatTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<AchatDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<AchatDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveAchatTest() throws Exception {
        // Mock data
        AchatDto requestDto = new AchatDto();
        Achat entity = new Achat();
        Achat saved = new Achat();
        AchatDto savedDto = new AchatDto();

        // Mock the converter to return the achat object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved achat DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<AchatDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        AchatDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved achat DTO
        assertEquals(savedDto.getReference(), responseBody.getReference());
        assertEquals(savedDto.getDateAchat(), responseBody.getDateAchat());
        assertEquals(savedDto.getTotal(), responseBody.getTotal());
        assertEquals(savedDto.getTotalPaye(), responseBody.getTotalPaye());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
