package ma.zs.stocky.service.impl.admin.commun;


import ma.zs.stocky.bean.core.commun.CategorieClient;
import ma.zs.stocky.dao.criteria.core.commun.CategorieClientCriteria;
import ma.zs.stocky.dao.facade.core.commun.CategorieClientDao;
import ma.zs.stocky.dao.specification.core.commun.CategorieClientSpecification;
import ma.zs.stocky.service.facade.admin.commun.CategorieClientAdminService;
import ma.zs.stocky.zynerator.service.AbstractServiceImpl;
import ma.zs.stocky.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;





import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
@Service
public class CategorieClientAdminServiceImpl extends AbstractServiceImpl<CategorieClient, CategorieClientCriteria, CategorieClientDao> implements CategorieClientAdminService {






    public CategorieClient findByReferenceEntity(CategorieClient t){
        return t==null? null : dao.findByCode(t.getCode());
    }


    public List<CategorieClient> findAllOptimized() {
        return dao.findAllOptimized();
    }





    public void configure() {
        super.configure(CategorieClient.class, CategorieClientSpecification.class);
    }



    public CategorieClientAdminServiceImpl(CategorieClientDao dao) {
        super(dao);
    }

}
