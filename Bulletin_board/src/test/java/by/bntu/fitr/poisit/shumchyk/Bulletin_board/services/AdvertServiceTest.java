package by.bntu.fitr.poisit.shumchyk.Bulletin_board.services;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Advert;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Tag;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdvertServiceTest {
    @Autowired
    private AdvertService advertService;

    @Test
    void getAdvertsByRightTags() {


        List<String> rightTags = EnumSet.allOf(Tag.class).stream().map(e -> e.name()).collect(Collectors.toList());

        for (int i = 0; i < rightTags.size() - 1; i++) {
            //assertNotNull(advertService.getAdvertsByTags(rightTags.get(i)));
            List<Advert> adverts =  advertService.getAdvertsByTags(rightTags.get(i));
            for (Advert adv : adverts) {
                assertEquals(true,adv.getTags().contains(Tag.valueOf(rightTags.get(i))));
            }
        }

    }
    @Test
    void getAdvertsByWrongTags() {
        List<String> wrongTags = new ArrayList<String>() {{
            add("1");
            add(" ");
            add("");
            add("_");
        }};
        for (int i = 0; i < wrongTags.size() - 1; i++) {
            //assertNotNull(advertService.getAdvertsByTags(rightTags.get(i)));
            List<Advert> adverts =  advertService.getAdvertsByTags(wrongTags.get(i));
            for (Advert adv : adverts) {
                //assertDoesNotThrow(() -> adv.getTags().contains("_"));
                assertEquals(false,adv.getTags().contains(wrongTags.get(i)));
            }
        }
    }
}