package com.criptomarket.prototype.Controllers;

import com.criptomarket.prototype.dto.Asset;
import com.criptomarket.prototype.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AssetsController {

    @Autowired
    private AssetService assetService;

    @GetMapping(value = "/all", produces = "application/json")
    public List<Asset> getAll() {
        return assetService.getAssets();
    }
}
