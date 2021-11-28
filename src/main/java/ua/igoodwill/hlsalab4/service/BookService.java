package ua.igoodwill.hlsalab4.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ua.igoodwill.hlsalab4.model.Book;
import ua.igoodwill.hlsalab4.repository.BookRepository;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.time.Duration;
import java.util.UUID;

@Service
public class BookService implements MessageListener {

    private final BookRepository bookRepository;
    private final JmsTemplate jmsTemplate;

    @Value("${active-mq.queue}")
    private String queue;

    public BookService(BookRepository bookRepository, JmsTemplate jmsTemplate) {
        this.bookRepository = bookRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @Cacheable(value = "bookCache")
    public Book getBook(UUID id) {
        return bookRepository.findById(id.toString()).orElse(null);
    }

    public UUID createBook(Book book) {
        UUID id = UUID.randomUUID();
        book.setId(id.toString());
        jmsTemplate.convertAndSend(queue, book);
        return id;
    }

    @JmsListener(destination = "${active-mq.queue}")
    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        boolean processed = false;
        long sleepTime = Duration.ofSeconds(1).toMillis();
        while (!processed) {
            try {
                Book book = (Book) objectMessage.getObject();
                try {
                    bookRepository.save(book);
                } catch (DataIntegrityViolationException ignored) {
                }

                message.acknowledge();
                processed = true;
            } catch (Exception e) {
                try {
                    Thread.sleep(sleepTime);
                    sleepTime = Math.min(sleepTime * 2, Duration.ofMinutes(1).toMillis());
                } catch (InterruptedException ie) {
                    break;
                }
            }
        }
    }
}
