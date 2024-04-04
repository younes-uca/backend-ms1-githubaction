package  ma.zs.stocky.ws.facade.admin.commun;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import ma.zs.stocky.bean.core.commun.CategorieClient;
import ma.zs.stocky.dao.criteria.core.commun.CategorieClientCriteria;
import ma.zs.stocky.service.facade.admin.commun.CategorieClientAdminService;
import ma.zs.stocky.ws.converter.commun.CategorieClientConverter;
import ma.zs.stocky.ws.dto.commun.CategorieClientDto;
import ma.zs.stocky.zynerator.controller.AbstractController;
import ma.zs.stocky.zynerator.dto.AuditEntityDto;
import ma.zs.stocky.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.stocky.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.stocky.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/categorieClient/")
public class CategorieClientRestAdmin  extends AbstractController<CategorieClient, CategorieClientDto, CategorieClientCriteria, CategorieClientAdminService, CategorieClientConverter> {



    @Operation(summary = "upload one categorieClient")
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }
    @Operation(summary = "upload multiple categorieClients")
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @Operation(summary = "Finds a list of all categorieClients")
    @GetMapping("")
    public ResponseEntity<List<CategorieClientDto>> findAll() throws Exception {
        return super.findAll();
    }

    @Operation(summary = "Finds an optimized list of all categorieClients")
    @GetMapping("optimized")
    public ResponseEntity<List<CategorieClientDto>> findAllOptimized() throws Exception {
        return super.findAllOptimized();
    }

    @Operation(summary = "Finds a categorieClient by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CategorieClientDto> findByLibelle(@PathVariable String libelle) {
        return super.findByReferenceEntity(new CategorieClient(libelle));
    }

    @Operation(summary = "Saves the specified  categorieClient")
    @PostMapping("")
    public ResponseEntity<CategorieClientDto> save(@RequestBody CategorieClientDto dto) throws Exception {
        return super.save(dto);
    }

    @Operation(summary = "Updates the specified  categorieClient")
    @PutMapping("")
    public ResponseEntity<CategorieClientDto> update(@RequestBody CategorieClientDto dto) throws Exception {
        return super.update(dto);
    }

    @Operation(summary = "Delete list of categorieClient")
    @PostMapping("multiple")
    public ResponseEntity<List<CategorieClientDto>> delete(@RequestBody List<CategorieClientDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @Operation(summary = "Delete the specified categorieClient")
    @DeleteMapping("")
    public ResponseEntity<CategorieClientDto> delete(@RequestBody CategorieClientDto dto) throws Exception {
            return super.delete(dto);
    }

    @Operation(summary = "Delete the specified categorieClient")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @Operation(summary = "Delete multiple categorieClients by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }



    @Operation(summary = "Finds a categorieClient and associated list by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CategorieClientDto> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Operation(summary = "Finds categorieClients by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CategorieClientDto>> findByCriteria(@RequestBody CategorieClientCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @Operation(summary = "Finds paginated categorieClients by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CategorieClientCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @Operation(summary = "Exports categorieClients by criteria")
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody CategorieClientCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @Operation(summary = "Gets categorieClient data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CategorieClientCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }



    public CategorieClientRestAdmin (CategorieClientAdminService service, CategorieClientConverter converter) {
        super(service, converter);
    }




}
