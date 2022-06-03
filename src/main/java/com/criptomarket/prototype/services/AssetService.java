package com.criptomarket.prototype.services;

import com.criptomarket.prototype.dto.Asset;
import com.criptomarket.prototype.storages.AssetsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetsStorage assetsStorage;

    @Transactional
    public List<Asset> getAssets() {
        return assetsStorage.getAll();
    }

}
