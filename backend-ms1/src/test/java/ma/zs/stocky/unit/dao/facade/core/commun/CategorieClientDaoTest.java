package ma.zs.stocky.unit.dao.facade.core.commun;

import ma.zs.stocky.bean.core.commun.CategorieClient;
import ma.zs.stocky.dao.facade.core.commun.CategorieClientDao;

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
public class CategorieClientDaoTest {

@Autowired
    private CategorieClientDao underTest;

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        CategorieClient entity = new CategorieClient();
        entity.setCode(code);
        underTest.save(entity);
        CategorieClient loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-1";
        CategorieClient entity = new CategorieClient();
        entity.setCode(code);
        underTest.save(entity);

        int result = underTest.deleteByCode(code);

        CategorieClient loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        CategorieClient entity = new CategorieClient();
        entity.setId(id);
        underTest.save(entity);
        CategorieClient loaded = underTest.findById(id).get();
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        CategorieClient entity = new CategorieClient();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        CategorieClient loaded = underTest.findById(id).get();
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<CategorieClient> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<CategorieClient> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }
    @Test
    void shouldSave(){
        CategorieClient given = constructSample(1);
        CategorieClient saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private CategorieClient constructSample(int i) {
		CategorieClient given = new CategorieClient();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
