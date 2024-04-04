package  ma.zs.stocky.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.commun.CategorieClient;
import ma.zs.stocky.ws.dto.commun.CategorieClientDto;

@Component
public class CategorieClientConverter extends AbstractConverter<CategorieClient, CategorieClientDto> {


    public  CategorieClientConverter() {
        super(CategorieClient.class, CategorieClientDto.class);
    }

    @Override
    public CategorieClient toItem(CategorieClientDto dto) {
        if (dto == null) {
            return null;
        } else {
        CategorieClient item = new CategorieClient();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }

    @Override
    public CategorieClientDto toDto(CategorieClient item) {
        if (item == null) {
            return null;
        } else {
            CategorieClientDto dto = new CategorieClientDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }




    public void initObject(boolean value) {
    }


}
