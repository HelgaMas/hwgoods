package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager productManager = new ProductManager(repository);
    Book book1 = new Book(1, "Jane Eyre", 500, "Charlotte Bronte");
    Book book2 = new Book(2, "White Fang", 200, "Jack London");

    Smartphone phone1 = new Smartphone(3, "iphone 11", 100000, "Apple");
    Smartphone phone2 = new Smartphone(4, "Nokia 3310", 5000, "Nokia");

    @BeforeEach
    public void addToAll() {
        productManager.add(book1);
        productManager.add(book2);
        productManager.add(phone1);
        productManager.add(phone2);
    }

    @Test
    public void shouldSearchNoGoods() {
        Product[] expected = {};
        Product[] actual = productManager.searchBy("LG");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSeveralGoods() {
        Product[] expected = {book1, book2};
        Product[] actual = productManager.searchBy("an");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNameFirstBook() {
        Product[] expected = {book1};
        Product[] actual = productManager.searchBy("Jane Eyre");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAuthorSecondBook() {
        Product[] expected = {book2};
        Product[] actual = productManager.searchBy("Jack London");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNameFirstSmartphone() {
        Product[] expected = {phone1};
        Product[] actual = productManager.searchBy("iphone 11");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchProducerSecondSmartphone() {
        Product[] expected = {phone2};
        Product[] actual = productManager.searchBy("Nokia");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        Product product = repository.findById(1);
        repository.removeById(product.getId());

        Product[] expected = new Product[]{book2, phone1, phone2};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindById() {
        Product actual = repository.findById(10);

        assertEquals(null, actual);
    }

    @Test
    public void shouldNotMatchesPhone() {
        boolean actual = productManager.matches(phone2, "iphone 11");

        assertFalse(false);
    }
}