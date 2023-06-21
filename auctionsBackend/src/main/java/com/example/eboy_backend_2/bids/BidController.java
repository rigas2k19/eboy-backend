package com.example.eboy_backend_2.bids;

import com.example.eboy_backend_2.items.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BidController {
    BidService bidService;

    BidController(BidService bidService){ this.bidService = bidService; }

    //get all bids of item with id
    @CrossOrigin(origins = "*")
    @GetMapping("/auctions/{auctionId}/bids")
    List<Bid> getBids(@PathVariable Integer auctionId){ return this.bidService.getBids(auctionId); }

    // get last bid of item with id
    @CrossOrigin(origins = "*")
    @GetMapping("/users/{userId}/bought")
    List<Integer> getAllUsersLastBids(@PathVariable String userId){ return this.bidService.getAllUsersLastBids(userId); }

    //get all bids of user with id
    @CrossOrigin(origins = "*")
    @GetMapping("/users/{id}/bids")
    List<Bid> getUserBids(@PathVariable String id){ return this.bidService.getUserBids(id); }

    @CrossOrigin(origins = "*")
    @PostMapping("/auctions/{auctionId}/bids")
    Bid addBid(@RequestBody Bid newBid,@PathVariable(value = "auctionId") Integer auctionId){
        return this.bidService.addBid(auctionId, newBid);
    }
}
