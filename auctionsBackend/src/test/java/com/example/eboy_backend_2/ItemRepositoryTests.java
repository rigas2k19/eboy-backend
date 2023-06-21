package com.example.eboy_backend_2;

import com.example.eboy_backend_2.auctions.Auction;
import com.example.eboy_backend_2.auctions.AuctionRepository;
import com.example.eboy_backend_2.bids.Bid;
import com.example.eboy_backend_2.bids.BidRepository;
import com.example.eboy_backend_2.items.Item;
import com.example.eboy_backend_2.items.ItemRepository;
import com.example.eboy_backend_2.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ItemRepositoryTests {
    @Autowired
    private ItemRepository itemRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BidRepository bidRepo;
    @Autowired
    private AuctionRepository auctionRepo;

    @Test
    public void testAddItem(){


       LocalDateTime start =
                LocalDateTime.of(2019, 3, 28, 14, 33, 48, 640000);
       LocalDateTime end =
                LocalDateTime.of(2019, 3, 28, 16, 33, 48, 640000);


       Auction auction = new Auction(0,0f, 1000f, 100f, 0, start, end, true, false, "Xenu");

        Auction savedauc = auctionRepo.save(auction);
        assertThat(savedauc).isNotNull();

        //2 items
        Item item1 = new Item("snitsel", "xelona", auction, "37, 37", "Mango", "glukouli","Greece");
        Item item2 = new Item("pretsel", "xelona", auction, "37, 37", "Mango", "glukouli kai auto","Greece");

        Item savedItem = itemRepo.save(item1);
        assertThat(savedItem).isNotNull();

        Item savedItem2 = itemRepo.save(item2);
        assertThat(savedItem2).isNotNull();
        /*

        //2 bids
        Bid bid1 = new Bid("Xenu",start, 30000f, auction);
        Bid bid2 = new Bid("Xenu", start, 10f, auction);
        Bid savedBid = bidRepo.save(bid1);
        Bid savedBid2 = bidRepo.save(bid2);

        assertThat(savedBid).isNotNull();
        assertThat(savedBid2).isNotNull();

 */
    }

}
