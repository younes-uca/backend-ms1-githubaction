package ma.zs.stocky.unit.dao.facade.core.commun;

import ma.zs.stocky.bean.core.commun.Produit;
import ma.zs.stocky.dao.facade.core.commun.ProduitDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProduitDaoTest {

@Autowired
    private ProduitDao underTest;

    @Test
    void shouldFindByReference(){
        String reference = "reference-1";
        Produit entity = new Produit();
        entity.setReference(reference);
        underTest.save(entity);
        Produit loaded = underTest.findByReference(reference);
        assertThat(loaded.getReference()).isEqualTo(reference);
    }

    @Test
    void shouldDeleteByReference() {
        String reference = "reference-1";
        Produit entity = new Produit();
        entity.setReference(reference);
        underTest.save(entity);

        int result = underTest.deleteByReference(reference);

        Produit loaded = underTest.findByReference(reference);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Produit entity = new Produit();
        entity.setId(id);
        underTest.save(entity);
        Produit loaded = underTest.findById(id).get();
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Produit entity = new Produit();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Produit loaded = underTest.findById(id).get();
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Produit> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Produit> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }
    @Test
    void shouldSave(){
        Produit given = constructSample(1);
        Produit saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Produit constructSample(int i) {
		Produit given = new Produit();
        given.setReference("reference-"+i);
        given.setLibelle("libelle-"+i);
        given.setBarcode("barcode-"+i);
        given.setDiscription("discription-"+i);
        given.setPrixAchatMoyen(BigDecimal.TEN);
        given.setQuantite(BigDecimal.TEN);
        given.setSeuilAlert(BigDecimal.TEN);
        return given;
    }

}
