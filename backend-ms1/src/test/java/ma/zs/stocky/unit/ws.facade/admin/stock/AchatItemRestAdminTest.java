package ma.zs.stocky.unit.ws.facade.admin.stock;

import ma.zs.stocky.bean.core.stock.AchatItem;
import ma.zs.stocky.service.impl.admin.stock.AchatItemAdminServiceImpl;
import ma.zs.stocky.ws.facade.admin.stock.AchatItemRestAdmin;
import ma.zs.stocky.ws.converter.stock.AchatItemConverter;
import ma.zs.stocky.ws.dto.stock.AchatItemDto;
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
public class AchatItemRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private AchatItemAdminServiceImpl service;
    @Mock
    private AchatItemConverter converter;

    @InjectMocks
    private AchatItemRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllAchatItemTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<AchatItemDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<AchatItemDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveAchatItemTest() throws Exception {
        // Mock data
        AchatItemDto requestDto = new AchatItemDto();
        AchatItem entity = new AchatItem();
        AchatItem saved = new AchatItem();
        AchatItemDto savedDto = new AchatItemDto();

        // Mock the converter to return the achatItem object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved achatItem DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<AchatItemDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        AchatItemDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved achatItem DTO
        assertEquals(savedDto.getPrixUnitaire(), responseBody.getPrixUnitaire());
        assertEquals(savedDto.getPrixVente(), responseBody.getPrixVente());
        assertEquals(savedDto.getQuantite(), responseBody.getQuantite());
        assertEquals(savedDto.getQuantiteAvoir(), responseBody.getQuantiteAvoir());
        assertEquals(savedDto.getRemise(), responseBody.getRemise());
    }

}
