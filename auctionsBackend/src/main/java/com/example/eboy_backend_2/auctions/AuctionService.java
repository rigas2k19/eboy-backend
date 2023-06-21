package com.example.eboy_backend_2.auctions;

import com.example.eboy_backend_2.bids.Bid;
import com.example.eboy_backend_2.bids.BidRepository;
import com.example.eboy_backend_2.items.Item;
import com.example.eboy_backend_2.items.ItemRepository;
import com.example.eboy_backend_2.xml.ExportXml;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AuctionService {
    private final AuctionRepository auctionRepo;
    private final ItemRepository itemRepo;
    private final BidRepository bidRepo;

    AuctionService(AuctionRepository auctionRepo, ItemRepository itemRepo, BidRepository bidRepo){
        this.auctionRepo = auctionRepo;
        this.bidRepo = bidRepo;
        this.itemRepo = itemRepo;
    }

    List<Auction> getAuctions() { return this.auctionRepo.findAll(); }

    Auction getAuction(@PathVariable Integer id){ return this.auctionRepo.findById(id).orElseThrow(() -> new AuctionNotFoundException(id)); }

    Auction addAuction(@RequestBody Auction newAuction){
        return this.auctionRepo.save(newAuction);
    }

    void deleteAuction(@PathVariable Integer auctionId) { this.auctionRepo.deleteById(auctionId); }

    Auction updateAuction(@RequestBody Auction newAuction, @PathVariable Integer id){
        return this.auctionRepo.findById(id)
                .map(auction -> {
                    auction.setId(newAuction.getId());
                    auction.setAuctionEnds(newAuction.getAuctionEnds());
                    auction.setAuctionStarted(newAuction.getAuctionStarted());
                    auction.setBuy_price(newAuction.getBuy_price());
                    auction.setCurrently(newAuction.getCurrently());
                    auction.setEnds(newAuction.getEnds());
                    auction.setFirst_bid(newAuction.getFirst_bid());
                    auction.setStarted(newAuction.getStarted());
                    auction.setNumber_of_bids(newAuction.getNumber_of_bids());
                    auction.setSeller(newAuction.getSeller());
                    return auctionRepo.save(newAuction);
                })
                .orElseGet(() -> {
                    newAuction.setId(id);
                    return this.auctionRepo.save(newAuction);
                });
    }

    List<Auction> getBySellerUsername(@PathVariable String seller){ return this.auctionRepo.findBySellerUsername(seller); }

    ExportXml export(@PathVariable Integer auctionId){

        Auction auction;
        auction = auctionRepo.SelectAuction(auctionId);

        ExportXml exportxml;
        List<Item> items;
        List<Bid> bids;

        //query to get all items.
        items = itemRepo.findAuctionItems(auctionId);
        for (Item item : items) {
            item.setAuction(null);
        }

        //query to get all bids.
        bids = bidRepo.findByAuction(auctionId);

        exportxml = new ExportXml(auction.getCurrently(), auction.getBuy_price(), auction.getFirst_bid(), auction.getNumber_of_bids(), auction.getStarted(),auction.getEnds(), auction.getAuctionStarted(), auction.getAuctionEnds(), auction.getSeller(), items, bids);

        return exportxml;

    }

    List<Auction> getRunningAuctions(){ return this.auctionRepo.findRunningAuctions(); }
}
