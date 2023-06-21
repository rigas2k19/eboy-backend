package com.example.eboy_backend_2.auctions;

import com.example.eboy_backend_2.xml.ExportXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuctionController {

    @Autowired
    AuctionService auctionService;

    AuctionController(AuctionService auctionService){ this.auctionService = auctionService; }

    @CrossOrigin(origins = "*")
    @GetMapping("/auctions")
    List<Auction> getAuctions(){ return this.auctionService.getAuctions(); }

    @CrossOrigin(origins = "*")
    @GetMapping("/auctions/{id}")
    Auction getAuction(@PathVariable Integer id){ return this.auctionService.getAuction(id); }

    @CrossOrigin(origins = "*")
    @PostMapping("/auctions/add")
    Auction addAuction(@RequestBody Auction newAuction){ return this.auctionService.addAuction(newAuction);}

    @CrossOrigin(origins = "*")
    @DeleteMapping("/auctions/{id}")
    void deleteAuction(@PathVariable Integer id){ this.auctionService.deleteAuction(id); }

    @CrossOrigin(origins = "*")
    @PutMapping("/auctions/add/{id}")
    Auction updateAuction(@RequestBody Auction newAuction, @PathVariable Integer id){ return this.auctionService.updateAuction(newAuction, id); }

    @CrossOrigin(origins = "*")
    @GetMapping("/auctions/user/{username}")
    List<Auction> getBySellerUsername(@PathVariable String username){ return this.auctionService.getBySellerUsername(username); }

    @CrossOrigin(origins = "*")
    @GetMapping("/auctions/export/{auctionId}")
    ExportXml export(@PathVariable Integer auctionId){ return this.auctionService.export(auctionId); }

    @CrossOrigin(origins = "*")
    @GetMapping("/auctions/running")
    List<Auction> getRunningAuctions(){ return this.auctionService.getRunningAuctions(); }
}
