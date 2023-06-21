package com.example.eboy_backend_2.auctions;

import com.example.eboy_backend_2.bids.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {
    @Query(value = "SELECT * from auction where auction.seller = :seller", nativeQuery=true)
    List<Auction> findBySellerUsername(@Param("seller") String seller);

    @Query(value = "SELECT * FROM auction WHERE auction.id = :auction_id", nativeQuery=true)
    Auction SelectAuction(@Param("auction_id") Integer auction_id);

    @Query(value = "select * from auction where auction.auction_ends = 0 ", nativeQuery = true)
    List<Auction> findRunningAuctions();
}
