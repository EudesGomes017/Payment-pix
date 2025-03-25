package br.com.LevelNetWork.Payment.Pix.pix;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/pix", produces = MediaType.APPLICATION_JSON_VALUE)
public record PixController(PixService pixService) {


    @GetMapping("/listar")
    public ResponseEntity<String> listarChavesPix(){
        var response = this.pixService.listarChavesPix();
        return ResponseEntity.ok().body(response.toString());
    }

    @GetMapping
    public ResponseEntity<String> criarChavePix(){
        var response = this.pixService.criarChavePix();
        return ResponseEntity.ok().body(response.toString());
    }

    @PostMapping
    public ResponseEntity<String> criarQrCode(@RequestBody PixRequestPayload pixRequestPayload){
        var response = this.pixService.criarQrCode(pixRequestPayload);
        return  ResponseEntity.ok().body(response.toString());
    }

    @DeleteMapping
    public ResponseEntity<String> deletarChavePix(@RequestParam("chavePix") String chavePix) {
        var response = this.pixService.deletarChavePix(chavePix);
        return ResponseEntity.ok()
                .body(response.toString());
    }


}