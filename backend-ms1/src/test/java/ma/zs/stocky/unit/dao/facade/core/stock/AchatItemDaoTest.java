package ma.zs.stocky.unit.dao.facade.core.stock;

import ma.zs.stocky.bean.core.stock.AchatItem;
import ma.zs.stocky.dao.facade.core.stock.AchatItemDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zs.stocky.bean.core.stock.Achat ;
import ma.zs.stocky.bean.core.commun.Produit ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AchatItemDaoTest {

@Autowired
    private AchatItemDao underTest;


    @Test
    void shouldFindById(){
        Long id = 1L;
        AchatItem entity = new AchatItem();
        entity.setId(id);
        underTest.save(entity);
        AchatItem loaded = underTest.findById(id).get();
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        AchatItem entity = new AchatItem();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        AchatItem loaded = underTest.findById(id).get();
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<AchatItem> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<AchatItem> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }
    @Test
    void shouldSave(){
        AchatItem given = constructSample(1);
        AchatItem saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
