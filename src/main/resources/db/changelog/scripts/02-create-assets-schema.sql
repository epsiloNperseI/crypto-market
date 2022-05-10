CREATE TABLE ASSETS
(
    ID              INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ASSET_ID        VARCHAR(100) NOT NULL,
    NAME            VARCHAR(256) NOT NULL,
    PRICE_USD       DECIMAL(30, 2),
    VOLUME_HOUR_USD DECIMAL(40, 2),
    VOLUME_DAY_USD  DECIMAL(40, 2),
    VOLUME_MTH_USD  DECIMAL(40, 2),
    TYPE_IS_CRYPTO  BIT
);