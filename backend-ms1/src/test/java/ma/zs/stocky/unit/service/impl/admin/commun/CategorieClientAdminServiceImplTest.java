package ma.zs.stocky.unit.service.impl.admin.commun;

import ma.zs.stocky.bean.core.commun.CategorieClient;
import ma.zs.stocky.dao.facade.core.commun.CategorieClientDao;
import ma.zs.stocky.service.impl.admin.commun.CategorieClientAdminServiceImpl;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class CategorieClientAdminServiceImplTest {

    @Mock
    private CategorieClientDao repository;
    private AutoCloseable autoCloseable;
    private CategorieClientAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CategorieClientAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCategorieClient() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCategorieClient() {
        // Given
        CategorieClient toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCategorieClient() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCategorieClientById() {
        // Given
        Long idToRetrieve = 1L; // Example CategorieClient ID to retrieve
        CategorieClient expected = new CategorieClient(); // You need to replace CategorieClient with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        CategorieClient result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private CategorieClient constructSample(int i) {
		CategorieClient given = new CategorieClient();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
