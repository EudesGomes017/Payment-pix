package br.com.LevelNetWork.Payment.Pix.pix;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PixService {

    private final JSONObject configuracoes;

    public static final int SECONDS_IN_EXPIRATION = 3600;
    public static final String CPF = "12345678909";
    public static final String NAME = "Eudes";

    public PixService(final PixConfig pixConfig) {
        this.configuracoes = new JSONObject();
        this.configuracoes.put("client_id", pixConfig.clientId());
        this.configuracoes.put("client_secret", pixConfig.clientSecret());
        this.configuracoes.put("certificate", pixConfig.certificatePath());
        this.configuracoes.put("sandbox", pixConfig.sandbox());
        this.configuracoes.put("debug", pixConfig.debug());
    }

    public JSONObject listarChavesPix() {
        return executarOperacao("pixListEvp", new HashMap<>());
    }

    public JSONObject criarChavePix() {
        return executarOperacao("pixCreateEvp", new HashMap<>());
    }

    public JSONObject deletarChavePix(final String chavePix) {
        Map<String, String> params = new HashMap<>();
        params.put("chave", chavePix);
        return executarOperacao("pixDeleteEvp", params);
    }

    public JSONObject criarQrCode(PixRequestPayload pixRequestPayload) {

        JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", SECONDS_IN_EXPIRATION));
        body.put("devedor", new JSONObject().put("cpf", CPF).put("nome", NAME));
        body.put("valor", new JSONObject().put("original", pixRequestPayload.valor()));
        body.put("chave", pixRequestPayload.chave());

        JSONArray infoAdicionais = new JSONArray();
        infoAdicionais.put(
                new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1"));
        infoAdicionais.put(
                new JSONObject().put("nome", "Campo 2").put("valor", "Informação Adicional2"));
        body.put("infoAdicionais", infoAdicionais);

        try {
            EfiPay efi = new EfiPay(configuracoes);
            return efi.call("pixCreateImmediateCharge", new HashMap<>(), body);
        } catch (EfiPayException e) {
            log.error("Erro da API {} {}", e.getErrorDescription(), e.getError());
        } catch (Exception e) {
            log.error("Erro genérico {}", e.getMessage());
        }
        return null;
    }

    private JSONObject executarOperacao(String operacao, Map<String, String> params) {
        final var retorno = new JSONObject();
        try {
            EfiPay efi = new EfiPay(configuracoes);
            JSONObject response = efi.call(operacao, params, new JSONObject());
            log.info("Resultado: {}", response);
            return response;
        } catch (EfiPayException e) {
            log.error(e.getError());
            retorno.put("erro", e.getErrorDescription());
        } catch (UnsupportedOperationException | JSONException operationException) {
            log.warn("Invalid JSON format {}", operationException.getMessage());
        } catch (Exception e) {
            retorno.put("erro", "Não foi possível completar a operação!");
        }
        return retorno;
    }
}