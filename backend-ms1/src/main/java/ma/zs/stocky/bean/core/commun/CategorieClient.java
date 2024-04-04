package ma.zs.stocky.bean.core.commun;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.audit.AuditBusinessObject;
import jakarta.persistence.*;
import java.util.Objects;




@Entity
@Table(name = "categorie_client")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="categorie_client_seq",sequenceName="categorie_client_seq",allocationSize=1, initialValue = 1)
public class CategorieClient   extends AuditBusinessObject     {

    private Long id;

    @Column(length = 500)
    private String code;
    @Column(length = 500)
    private String libelle;



    public CategorieClient(){
        super();
    }

    public CategorieClient(Long id){
        this.id = id;
    }

    public CategorieClient(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public CategorieClient(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="categorie_client_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorieClient categorieClient = (CategorieClient) o;
        return id != null && id.equals(categorieClient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

