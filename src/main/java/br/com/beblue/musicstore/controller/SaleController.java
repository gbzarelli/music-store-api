package br.com.beblue.musicstore.controller;

import br.com.beblue.musicstore.dto.SaleRequestDTO;
import br.com.beblue.musicstore.dto.SaleResponseDTO;
import br.com.beblue.musicstore.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.beblue.musicstore.controller.SaleController.ROOT_PATH;

@RestController
@RequestMapping(ROOT_PATH)
public class SaleController {
    static final String ROOT_PATH = "/sale";

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping()
    public SaleResponseDTO postOrder(@RequestBody List<SaleRequestDTO> saleRequestDTOList) {
        return saleService.registerOrder(saleRequestDTOList);
    }
}
