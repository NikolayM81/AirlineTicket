package ru.netology;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AviaSoulsTest {
    AviaSouls aviaSouls = new AviaSouls();

    @Test
    public void testCompareTo() {
        Ticket t1 = new Ticket("Иркутск", "Красноярск", 8000, 10, 12);
        Ticket t2 = new Ticket("Иркутск", "Москва", 18000, 12, 19);

        aviaSouls.add(t1);
        aviaSouls.add(t2);

        Ticket[] expected = new Ticket[]{t1, t2};
        Ticket[] actual = aviaSouls.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchSortedByPrice() {
        aviaSouls.add(new Ticket("Иркутск", "Москва", 18000, 12, 19));
        aviaSouls.add(new Ticket("Иркутск", "Красноярск", 8000, 10, 12));

        Ticket[] actual = aviaSouls.search("Иркутск", "Москва");

        Ticket[] expected = {new Ticket("Иркутск", "Москва", 18000, 12, 19)};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testTicketTimeComparator() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("Иркутск", "Красноярск", 8000, 10, 12);
        Ticket t2 = new Ticket("Иркутск", "Москва", 18000, 12, 19);
        Ticket[] actual = new Ticket[]{t2, t1};

        Arrays.sort(actual, comparator);
        Ticket[] expected = {t1, t2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchAndSortByDuration() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("Иркутск", "Москва", 18000, 12, 19));
        manager.add(new Ticket("Иркутск", "Красноярск", 8000, 10, 12));

        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] results = manager.searchAndSortBy("Иркутск", "Москва", comparator);
        assertArrayEquals(new Ticket[]{
                new Ticket("Иркутск", "Москва", 18000, 12, 19)
        }, results);

        results = manager.searchAndSortBy("Иркутск", "Красноярск", comparator);
        assertArrayEquals(new Ticket[]{
                new Ticket("Иркутск", "Красноярск", 8000, 10, 12)
        }, results);
    }
}
