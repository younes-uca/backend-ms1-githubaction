package  ma.zs.stocky.dao.specification.core.commun;

import ma.zs.stocky.dao.criteria.core.commun.CategorieClientCriteria;
import ma.zs.stocky.bean.core.commun.CategorieClient;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class CategorieClientSpecification extends  AbstractSpecification<CategorieClientCriteria, CategorieClient>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public CategorieClientSpecification(CategorieClientCriteria criteria) {
        super(criteria);
    }

    public CategorieClientSpecification(CategorieClientCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
