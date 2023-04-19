package com.isthifa.serialization_deserialization.rest;

import com.isthifa.serialization_deserialization.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    List<Book> books = new ArrayList<>();
    @GetMapping("/serialize")
    public byte[] serialize() throws Exception {
        books.add(new Book(1, "java"));
        books.add(new Book(2, "c"));
        books.add(new Book(3, "python"));
        books.add(new Book(4, "c++"));
        books.add(new Book(5, "javascript"));
            ByteArrayOutputStream by = new ByteArrayOutputStream();
            ObjectOutputStream ob = new ObjectOutputStream(by);
            ob.writeObject(books);
            byte[] serialize = by.toByteArray();
            FileOutputStream fis = new FileOutputStream("mytext.txt");
            fis.write(serialize);
            fis.close();
            return by.toByteArray();
    }
    @GetMapping("/deserialize")
    public HashMap<Integer,String> list()
    {
        HashMap<Integer,String> map=new HashMap<>();
        try
        {
            ObjectInputStream ob=new ObjectInputStream(new FileInputStream("mytext.txt"));
            List<Book> books1=(List<Book>) ob.readObject();
            for(Book book:books1)
            {
              map.put(book.getBcode(),book.getBname());
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return map;
    }
    @GetMapping("/list")
    public List<Book> getall()
    {
        return books;
    }
    @GetMapping("/{id}")
    public Book getdata(@PathVariable int id)
    {
        if(id>books.size()|| id<0)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id not found at "+id);
        }
        else
        {
            return books.get(id);
        }
    }
    @PostMapping("/add")
    public String add(@RequestBody Book book) {
        books.add(book);
        return "Added";
    }


}
