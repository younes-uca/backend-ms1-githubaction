package ma.zs.stocky.unit.dao.facade.core.stock;

import ma.zs.stocky.bean.core.stock.Achat;
import ma.zs.stocky.dao.facade.core.stock.AchatDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zs.stocky.bean.core.commun.Client ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AchatDaoTest {

@Autowired
    private AchatDao underTest;

    @Test
    void shouldFindByReference(){
        String reference = "reference-1";
        Achat entity = new Achat();
        entity.setReference(reference);
        underTest.save(entity);
        Achat loaded = underTest.findByReference(reference);
        assertThat(loaded.getReference()).isEqualTo(reference);
    }

    @Test
    void shouldDeleteByReference() {
        String reference = "reference-1";
        Achat entity = new Achat();
        entity.setReference(reference);
        underTest.save(entity);

        int result = underTest.deleteByReference(reference);

        Achat loaded = underTest.findByReference(reference);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Achat entity = new Achat();
        entity.setId(id);
        underTest.save(entity);
        Achat loaded = underTest.findById(id).get();
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Achat entity = new Achat();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Achat loaded = underTest.findById(id).get();
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Achat> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Achat> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }
    @Test
    void shouldSave(){
        Achat given = constructSample(1);
        Achat saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Achat constructSample(int i) {
		Achat given = new Achat();
        given.setReference("reference-"+i);
        given.setDateAchat(LocalDateTime.now());
        given.setTotal(BigDecimal.TEN);
        given.setTotalPaye(BigDecimal.TEN);
        given.setDescription("description-"+i);
        given.setClient(new Client(1L));
        return given;
    }

}
