package services;

import dto.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storages.AssetsStorage;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetsStorage assetsStorage;

    public List<Asset> getAssets() {
        return assetsStorage.getAll();
    }

}
