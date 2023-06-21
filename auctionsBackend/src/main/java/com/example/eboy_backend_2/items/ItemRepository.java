package com.example.eboy_backend_2.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value="SELECT i.category, count(*) FROM eboydb.item i WHERE (Select a.auction_ends from auction a where a.id = i.auction_id) = 0 GROUP BY i.category", nativeQuery=true)
    List<Object[]> findCategories();

    @Query(value="SELECT i.country, count(*) FROM eboydb.item i WHERE (Select a.auction_ends from auction a where a.id = i.auction_id) = 0 GROUP BY i.country", nativeQuery=true)
    List<Object[]> findCountries();

    @Query(value = "SELECT * FROM eboydb.item i WHERE i.auction_id = :auctionId GROUP BY i.name", nativeQuery=true)
    List<Item> findAuctionItems(@Param("auctionId") Integer auctionId);

    @Query(value="Select i.name , i.auction_id From eboydb.item i ", nativeQuery=true)
    List<Object[]> getItemsWithAuctionId();

    /* Get auction id from item */
    @Query(value="Select i.auction_id From eboydb.item i where i.id = :id", nativeQuery=true)
    Integer getAuctionIdFromItem(@Param("id") Integer id);

    @Query(value = "select * from eboydb.item i where (select a.auction_ends from eboydb.auction a where a.id = i.auction_id) = 0", nativeQuery = true)
    List<Item> findItemsOfRunningAuctions();
}
