package ma.zs.stocky.unit.dao.facade.core.commun;

import ma.zs.stocky.bean.core.commun.Client;
import ma.zs.stocky.dao.facade.core.commun.ClientDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zs.stocky.bean.core.commun.CategorieClient ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ClientDaoTest {

@Autowired
    private ClientDao underTest;

    @Test
    void shouldFindByCin(){
        String cin = "cin-1";
        Client entity = new Client();
        entity.setCin(cin);
        underTest.save(entity);
        Client loaded = underTest.findByCin(cin);
        assertThat(loaded.getCin()).isEqualTo(cin);
    }

    @Test
    void shouldDeleteByCin() {
        String cin = "cin-1";
        Client entity = new Client();
        entity.setCin(cin);
        underTest.save(entity);

        int result = underTest.deleteByCin(cin);

        Client loaded = underTest.findByCin(cin);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Client entity = new Client();
        entity.setId(id);
        underTest.save(entity);
        Client loaded = underTest.findById(id).get();
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Client entity = new Client();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Client loaded = underTest.findById(id).get();
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Client> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Client> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }
    @Test
    void shouldSave(){
        Client given = constructSample(1);
        Client saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Client constructSample(int i) {
		Client given = new Client();
        given.setCin("cin-"+i);
        given.setNom("nom-"+i);
        given.setTel("tel-"+i);
        given.setEmail("email-"+i);
        given.setAdresse("adresse-"+i);
        given.setDescription("description-"+i);
        given.setCreance(BigDecimal.TEN);
        given.setCategorieClient(new CategorieClient(1L));
        return given;
    }

}
