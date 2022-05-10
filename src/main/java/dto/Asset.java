package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="assets")
public class Asset implements Serializable {
    //TODO: property naming strategy; Realize behavior via interface;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ASSET_ID")
    private String assetId;
    private String name;
    @Column(name = "PRICE_USD")
    private Double priceUsd;
    @Column(name = "TYPE_IS_CRYPTO")
    private Integer typeIsCrypto;
    @Column(name = "VOLUME_HOUR_USD")
    @JsonProperty("volume_1hrs_usd")
    private String volumeHourUsd;
    @Column(name = "VOLUME_DAY_USD")
    @JsonProperty("volume_1day_usd")
    private String volumeDayUsd;
    @Column(name = "VOLUME_MTH_USD")
    @JsonProperty("volume_1mth_usd")
    private String volumeMthUsd;
}
