package com.example.eboy_backend_2.bids;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Integer> {

    List<Bid> findByAuctionId(Integer auctionId);

    @Query(value="SELECT  * from bid b WHERE b.auction_id = :auctionId", nativeQuery=true)
    List<Bid> findByAuction(@Param("auctionId") Integer auctionId);

    @Query(value = "SELECT * from bid b where b.bidder = :bidderId", nativeQuery=true)
    List<Bid> findByBidderUsername(@Param("bidderId") String bidderId);

    /* return last bid of auction with id */
    @Query(value = "SELECT * from bid b where b.auction_id = :auctionId order by b.amount desc limit 1", nativeQuery=true)
    Bid getLastBidOfAuction(@Param("auctionId") Integer auctionId);

    /* return all user's last bids */
    @Query(value = "SELECT b.auction_id from bid b where b.bidder = :username and b.amount = (SELECT max(bid.amount) from bid where bid.auction_id= b.auction_id) group by b.auction_id ", nativeQuery=true)
    List<Integer> getAllUsersLastBids(@Param("username") String username);
}
