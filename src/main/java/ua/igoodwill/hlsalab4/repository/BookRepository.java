package ua.igoodwill.hlsalab4.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.igoodwill.hlsalab4.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
