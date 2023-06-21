package com.example.eboy_backend_2.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ItemService itemService;

    ItemController(ItemService itemService){ this.itemService = itemService; }

    @CrossOrigin(origins = "*")
    @GetMapping("/items")
    List<Item> getItems(){ return this.itemService.getItems(); }

    @CrossOrigin(origins = "*")
    @GetMapping("/items/{id}")
    Item getItem(@PathVariable Integer id){ return this.itemService.getItem(id); }

    @CrossOrigin(origins = "*")
    @PostMapping("/auctions/{auctionId}/items/add")
    Item addItem(@RequestBody Item newItem, @PathVariable(value = "auctionId") Integer auctionId){ return this.itemService.addItem(auctionId, newItem);}

    @CrossOrigin(origins = "*")
    @DeleteMapping("/items/{id}")
    void deleteItem(@PathVariable Integer id){ this.itemService.deleteItem(id); }

    @CrossOrigin(origins = "*")
    @PutMapping("/auctions/{auctionId}/items/{id}")
    Item updateItem(@RequestBody Item newItem, @PathVariable(value = "id") Integer id , @PathVariable(value = "auctionId") Integer auctionId){ return this.itemService.updateItem(newItem, id, auctionId); }

    @CrossOrigin(origins = "*")
    @GetMapping("/auctions/{auctionId}/items")
    List<Item> getAuctionItems(@PathVariable Integer auctionId){ return this.itemService.getAuctionItems(auctionId); }

    @CrossOrigin(origins = "*")
    @GetMapping("/items-with-auction")
    List<Object[]> getItemsWithAuctionId(){ return this.itemService.getItemsWithAuctionId();}

    @CrossOrigin(origins = "*")
    @GetMapping("/items/categories")
    List<Object[]> findCategories(){
        return this.itemService.findCategories();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/items/countries")
    List<Object[]> findCountries(){
        return this.itemService.findCountries();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/items/{id}/get-auctionId")
    Integer getAuctionIdFromItem(@PathVariable("id") Integer id){
        return this.itemService.getAuctionIdFromItem(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/items/auctions/running")
    List<Item> getRunningAuctionsItems(){ return this.itemService.getRunningAuctionItems(); }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/auction/items/{id}")
    void deleteAuctionItems(@PathVariable Integer id){ this.itemService.deleteAuctionItems(id); }


}
