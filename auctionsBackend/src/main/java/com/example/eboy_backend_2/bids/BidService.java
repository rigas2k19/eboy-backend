package com.example.eboy_backend_2.bids;

import com.example.eboy_backend_2.auctions.AuctionRepository;
import com.example.eboy_backend_2.items.Item;
import com.example.eboy_backend_2.items.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.Inet4Address;
import java.util.List;

@Service
public class BidService {
    @Autowired
    private final BidRepository bidRepo;

    @Autowired
    private AuctionRepository auctionRepository;

    BidService(BidRepository bidRepo){ this.bidRepo = bidRepo; }

    List<Bid> getBids(Integer auctionId) {return this.bidRepo.findByAuctionId(auctionId);}

    List<Bid> getUserBids(String userId) { return this.bidRepo.findByBidderUsername(userId); }

    List<Integer> getAllUsersLastBids(String userId){ return this.bidRepo.getAllUsersLastBids(userId); }

    Bid getBid(@PathVariable Integer id){ return this.bidRepo.findById(id).orElseThrow(() -> new BidNotFoundException(id)); }

    Bid addBid(@PathVariable(value = "auctionId") Integer auctionId, @RequestBody Bid newBid){
        return auctionRepository.findById(auctionId).map(auction -> {
            newBid.setAuction(auction);
            return this.bidRepo.save(newBid);
        }).orElseThrow();

    }

    void deleteBid(@PathVariable Integer bidId) { this.bidRepo.deleteById(bidId); }
}
