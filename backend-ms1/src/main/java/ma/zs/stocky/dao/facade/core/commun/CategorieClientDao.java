package ma.zs.stocky.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.commun.CategorieClient;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.commun.CategorieClient;
import java.util.List;


@Repository
public interface CategorieClientDao extends AbstractRepository<CategorieClient,Long>  {
    CategorieClient findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW CategorieClient(item.id,item.libelle) FROM CategorieClient item")
    List<CategorieClient> findAllOptimized();

}
