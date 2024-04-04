package ma.zs.stocky.unit.service.impl.admin.stock;

import ma.zs.stocky.bean.core.stock.AchatItem;
import ma.zs.stocky.dao.facade.core.stock.AchatItemDao;
import ma.zs.stocky.service.impl.admin.stock.AchatItemAdminServiceImpl;

import ma.zs.stocky.bean.core.stock.Achat ;
import ma.zs.stocky.bean.core.commun.Produit ;
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
class AchatItemAdminServiceImplTest {

    @Mock
    private AchatItemDao repository;
    private AutoCloseable autoCloseable;
    private AchatItemAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AchatItemAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllAchatItem() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveAchatItem() {
        // Given
        AchatItem toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteAchatItem() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetAchatItemById() {
        // Given
        Long idToRetrieve = 1L; // Example AchatItem ID to retrieve
        AchatItem expected = new AchatItem(); // You need to replace AchatItem with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        AchatItem result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private AchatItem constructSample(int i) {
		AchatItem given = new AchatItem();
        given.setProduit(new Produit(1L));
        given.setPrixUnitaire(BigDecimal.TEN);
        given.setPrixVente(BigDecimal.TEN);
        given.setQuantite(BigDecimal.TEN);
        given.setQuantiteAvoir(BigDecimal.TEN);
        given.setRemise(BigDecimal.TEN);
        given.setAchat(new Achat(1L));
        return given;
    }

}
