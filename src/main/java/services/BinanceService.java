package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import constant.Currency;
import dto.SimpleCoinPriceRepresentation;
import dto.SystemStatusResponse;
import org.apache.log4j.Logger;
import org.junit.gen5.api.Assertions;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BinanceService {

    private final static ObjectMapper objectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    private final static OkHttpClient client = new OkHttpClient();
    private final static Logger logger = Logger.getLogger(BinanceService.class);
    private final static String baseUrl = "https://rest.coinapi.io/v1";
    private final static String apiKeyPropName = "X-CoinAPI-Key";
    private final static String apiKeyValue = "E4B945BD-CDFE-483C-BF1E-57A365A996F9";

    public static void main(String[] args) {
        System.out.println(isSystemReady());
    }

    public static SimpleCoinPriceRepresentation getSimplePriceById() {
        Request request = new Request.Builder()
                .url(baseUrl + "assets/" + Currency.Bitcoin)
                .addHeader(apiKeyPropName, apiKeyValue)
                .build();

        SimpleCoinPriceRepresentation coin = null;
        try {
            Response response = client.newCall(request).execute();
            Assertions.assertEquals(200, response.code());

            coin = objectMapper.readValue(removeSquareBrackets(response.body().string()), SimpleCoinPriceRepresentation.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coin;
    }

    public List<SimpleCoinPriceRepresentation> getSimplePricesListByIds(List<String> ids) {
        Request request = new Request.Builder()
                .url(baseUrl + String.format("assets?filter_asset_id= %s", ids))
                .addHeader(apiKeyPropName, apiKeyValue)
                .build();

        List<SimpleCoinPriceRepresentation> result = new ArrayList<>();
        try {
            Response response = client.newCall(request).execute();
            Assertions.assertEquals(200, response.code());
            result = Arrays.asList(objectMapper.readValue(removeSquareBrackets(response.body().string()), SimpleCoinPriceRepresentation[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String removeSquareBrackets(String responseBody) {
        StringBuilder builder = new StringBuilder(responseBody);

        builder.deleteCharAt((builder.toString().length() - 1));
        builder.deleteCharAt(0);
        return builder.toString();
    }

    private static Boolean isSystemReady() {
        boolean readyForAnswer = false;
        Request request = new Request.Builder()
                .url(baseUrl + "/sapi/v1/system/status")
                .addHeader(apiKeyPropName, apiKeyValue)
                .get()
                .build();
        Response response;
        SystemStatusResponse result;
        try {
            response = client.newCall(request).execute();
            if (response.code() == 200) {
                result = objectMapper.readValue(removeSquareBrackets(response.body().string()), SystemStatusResponse.class);
                readyForAnswer = result.getStatus() == 0 && "normal".equals(result.getMsg());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return readyForAnswer;
    }
}
