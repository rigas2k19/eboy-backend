package com.example.eboy_backend_2.items;

import com.example.eboy_backend_2.auctions.AuctionNotFoundException;
import com.example.eboy_backend_2.auctions.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private final ItemRepository itemRepo;
    @Autowired
    private AuctionRepository auctionRepository;

    ItemService(ItemRepository itemRepo){ this.itemRepo = itemRepo; }

    List<Item> getItems() {return this.itemRepo.findAll();}

    Item getItem(@PathVariable Integer id){return this.itemRepo.findById(id).orElseThrow(() -> new ItemNotFoundException(id));}

    Item addItem(@PathVariable(value = "auctionId") Integer auctionId, @RequestBody Item newItem){
        return auctionRepository.findById(auctionId).map(auction -> {
            newItem.setAuction(auction);
            return this.itemRepo.save(newItem);
        }).orElseThrow();
    }

    void deleteItem(@PathVariable Integer itemId) { this.itemRepo.deleteById(itemId); }

    Item updateItem(@RequestBody Item newItem, @PathVariable(value="id") Integer id, @PathVariable(value = "auctionId") Integer auctionId){
        if(!auctionRepository.existsById(auctionId)) {
            throw new AuctionNotFoundException(auctionId);
        }

        return itemRepo.findById(id).map(item->{
            item.setName(newItem.getName());
            item.setLocation(newItem.getLocation());
            item.setCategory(newItem.getCategory());
            item.setDescription(newItem.getDescription());
            item.setSellerUsername(newItem.getSellerUsername());
            item.setCountry(newItem.getCountry());

            return itemRepo.save(item);
        }).orElseThrow();

    }

    List<Item> getAuctionItems(@PathVariable Integer auctionId){ return this.itemRepo.findAuctionItems(auctionId); }

    List<Object[]> getItemsWithAuctionId(){ return this.itemRepo.getItemsWithAuctionId(); }


    List<Object[]> findCategories(){
        return this.itemRepo.findCategories();
    }

    List<Object[]> findCountries(){
        return this.itemRepo.findCountries();
    }

    Integer getAuctionIdFromItem(@PathVariable Integer id){
        return this.itemRepo.getAuctionIdFromItem(id);
    }

    List<Item> getRunningAuctionItems(){ return this.itemRepo.findItemsOfRunningAuctions(); }

    void deleteAuctionItems(@PathVariable Integer AuctionId){
        List<Item> items  = this.itemRepo.findAuctionItems(AuctionId);
        for (Item item : items) {
            //delete every item
            deleteItem(item.getId());
        }
    }

}