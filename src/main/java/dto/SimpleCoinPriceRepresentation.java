package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
@NoArgsConstructor //
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleCoinPriceRepresentation {

    private String assetId;
    private String name;
    private String typeIsCrypto;
    private String dataQuoteStart;
    private String dataQuoteEnd;
    private String dataOrderbookStart;
    private String dataOrderbookEnd;
    private String dataTradeStart;
    private String dataTradeEnd;
    private String dataSymbolsCount;
    private String priceUsd;
    private String idIcon;
    private String dataStart;
    private String dataEnd;
    @JsonProperty("volume_1hrs_usd")
    private String volume1HrsUsd;
    @JsonProperty("volume_1day_usd")
    private String volumeDayUsd;
    @JsonProperty("volume_1mth_usd")
    private String volumeMthUsd;
}
