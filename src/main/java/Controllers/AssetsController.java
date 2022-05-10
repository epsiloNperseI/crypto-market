package Controllers;

import dto.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.AssetService;

import java.util.List;

@RestController
public class AssetsController {

    @Autowired
    private AssetService assetService;

    @RequestMapping(value = "/all", produces = "application/json")
    public List<Asset> getAll() {
        return assetService.getAssets();
    }
}
