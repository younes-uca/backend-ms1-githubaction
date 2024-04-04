package ma.zs.stocky.unit.service.impl.admin.stock;

import ma.zs.stocky.bean.core.stock.Achat;
import ma.zs.stocky.dao.facade.core.stock.AchatDao;
import ma.zs.stocky.service.impl.admin.stock.AchatAdminServiceImpl;

import ma.zs.stocky.bean.core.stock.AchatItem ;
import ma.zs.stocky.bean.core.stock.Achat ;
import ma.zs.stocky.bean.core.commun.Client ;
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
class AchatAdminServiceImplTest {

    @Mock
    private AchatDao repository;
    private AutoCloseable autoCloseable;
    private AchatAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AchatAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllAchat() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveAchat() {
        // Given
        Achat toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteAchat() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetAchatById() {
        // Given
        Long idToRetrieve = 1L; // Example Achat ID to retrieve
        Achat expected = new Achat(); // You need to replace Achat with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Achat result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Achat constructSample(int i) {
		Achat given = new Achat();
        given.setReference("reference-"+i);
        given.setDateAchat(LocalDateTime.now());
        given.setTotal(BigDecimal.TEN);
        given.setTotalPaye(BigDecimal.TEN);
        given.setDescription("description-"+i);
        given.setClient(new Client(1L));
        List<AchatItem> achatItems = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                AchatItem element = new AchatItem();
                                                element.setId((long)id);
                                                element.setProduit(new Produit(Long.valueOf(1)));
                                                element.setPrixUnitaire(new BigDecimal(2*10));
                                                element.setPrixVente(new BigDecimal(3*10));
                                                element.setQuantite(new BigDecimal(4*10));
                                                element.setQuantiteAvoir(new BigDecimal(5*10));
                                                element.setRemise(new BigDecimal(6*10));
                                                element.setAchat(new Achat(Long.valueOf(7)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setAchatItems(achatItems);
        return given;
    }

}
